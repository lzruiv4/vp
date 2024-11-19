package de.scopevisio.vp.backend.data.store;

import de.scopevisio.vp.backend.data.entity.CarEntity;
import de.scopevisio.vp.backend.data.entity.ClientEntity;
import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.data.repository.CarRepository;
import de.scopevisio.vp.backend.data.repository.ClientRepository;

import de.scopevisio.vp.backend.data.store.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CarStore {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Add a new car.
     *
     * @param car
     * @param clientId             is the client's id.
     * @return car
     * @throws NotFoundException if client not found
     */
    public Car addCar(final Car car, final Long clientId) {
        CarEntity carEntityToBeSave = car.modelToEntity();

        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(clientId);
        clientEntityOptional.ifPresentOrElse(
                carEntityToBeSave::setClientEntity,
                () -> {
                    throw new NotFoundException("Client not found");
                });

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
     * @throws NotFoundException if car not found
     */
    public Car updateCar(final Car newCar) {
        Optional<CarEntity> carEntityOptional = carRepository.findById(newCar.getCarId());
        if (carEntityOptional.isEmpty())
            throw new NotFoundException("Car not found");
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
