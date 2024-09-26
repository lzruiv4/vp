package de.scopevisio.vp.backend.data.store;

import de.scopevisio.vp.backend.data.entity.CarEntity;
import de.scopevisio.vp.backend.data.entity.ClientEntity;
import de.scopevisio.vp.backend.data.enums.CarType;
import de.scopevisio.vp.backend.data.enums.RegionType;
import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.data.repository.CarRepository;
import de.scopevisio.vp.backend.data.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CarStore {

    private final CarRepository carRepository;

    private final ClientRepository clientRepository;

    /**
     * Add a new car.
     *
     * @param carType              is a car type. PKW or LKW.
     * @param milesPerYear         is the average mileage per year.
     * @param regionType           is determined by the car's registration location.
     * @param versicherungspraemie is calculated based on the car's KilometerleistungFaktor, FahrzeugtypFaktor and RegionFaktor.
     * @param registeredPostalCode is the postal code, where the car registered is.
     * @param clientId             is the client's id.
     * @return car
     * @throws NoSuchElementException if client not found
     */
    public Car addCar(final CarType carType,
                      final BigDecimal milesPerYear,
                      final RegionType regionType,
                      final BigDecimal versicherungspraemie,
                      final String registeredPostalCode,
                      final Long clientId
    ) {
        CarEntity carEntityToBeSave = new CarEntity();
        carEntityToBeSave.setCarType(carType);
        carEntityToBeSave.setMilesPerYear(milesPerYear);
        carEntityToBeSave.setRegionType(regionType);
        carEntityToBeSave.setRegisteredPostalCode(registeredPostalCode);

        if (versicherungspraemie != null) {
            carEntityToBeSave.setVersicherungspraemie(versicherungspraemie);
        }

        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(clientId);
        clientEntityOptional.ifPresentOrElse(
                carEntityToBeSave::setClientEntity,
                () -> {
                    throw new NoSuchElementException("Client not found");
                }
        );

        return carRepository.save(carEntityToBeSave).entityToModel();
    }

    /**
     * Find all cars by client id.
     *
     * @param clientId is the client's id.
     * @return car as list
     */
    public List<Car> getCarsByClientId(final Long clientId) {
        return carRepository.findAll().stream()
                .filter(carEntity -> Objects.equals(carEntity.getClientEntity().getClientId(), clientId))
                .map(CarEntity::entityToModel)
                .toList();
    }

    /**
     * Update a car.
     *
     * @param newCar is a car with all new information.
     * @return car, which up to date
     * @throws NoSuchElementException if car not found
     */
    public Car updateCar(final Car newCar) {
        Optional<CarEntity> carEntityOptional = carRepository.findById(newCar.getCarId());
        if (carEntityOptional.isEmpty()) throw new NoSuchElementException("Car not found");
        CarEntity carEntity = mapTheNewCar(carEntityOptional.get(), newCar.modelToEntity());
        return carRepository.saveAndFlush(carEntity).entityToModel();
    }

    /**
     * Update the information with new car information.
     *
     * @param oldCarEntity is an old carEntity from database.
     * @param newCarEntity is a new carEntity.
     * @return carEntity
     */
    private CarEntity mapTheNewCar(CarEntity oldCarEntity, CarEntity newCarEntity) {
        if (oldCarEntity.getCarType() != newCarEntity.getCarType() && newCarEntity.getCarType() != null)
            oldCarEntity.setCarType(newCarEntity.getCarType());
        if (!Objects.equals(oldCarEntity.getMilesPerYear(), newCarEntity.getMilesPerYear())
                && newCarEntity.getMilesPerYear() != null)
            oldCarEntity.setMilesPerYear(newCarEntity.getMilesPerYear());
        if (oldCarEntity.getRegionType() != newCarEntity.getRegionType() && newCarEntity.getRegionType() != null)
            oldCarEntity.setRegionType(newCarEntity.getRegionType());
        if (!Objects.equals(oldCarEntity.getVersicherungspraemie(), newCarEntity.getVersicherungspraemie())
                && newCarEntity.getVersicherungspraemie() != null)
            oldCarEntity.setVersicherungspraemie(newCarEntity.getVersicherungspraemie());
        if (!Objects.equals(oldCarEntity.getRegisteredPostalCode(), newCarEntity.getRegisteredPostalCode())
                && newCarEntity.getRegisteredPostalCode() != null)
            oldCarEntity.setRegisteredPostalCode(newCarEntity.getRegisteredPostalCode());

        return oldCarEntity;
    }

}
