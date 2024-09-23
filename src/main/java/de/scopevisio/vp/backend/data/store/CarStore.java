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

    public Car addCar(final CarType carType,
                      final BigDecimal milesPerYear,
                      final RegionType regionType,
                      final BigDecimal versicherungspraemie,
                      final String registeredPostalCode,
                      final Long clientId
    ){
        CarEntity carEntityToBeSave = new CarEntity();
        carEntityToBeSave.setCarType(carType);
        carEntityToBeSave.setMilesPerYear(milesPerYear);
        carEntityToBeSave.setRegionType(regionType);

        if(versicherungspraemie != null) {
            carEntityToBeSave.setVersicherungspraemie(versicherungspraemie);
        }

        carEntityToBeSave.setRegisteredPostalCode(registeredPostalCode);

        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(clientId);
        clientEntityOptional.ifPresentOrElse(
                carEntityToBeSave::setClientEntity,
                () -> {
                    throw new NoSuchElementException("Client not found");
                }
        );

        return carRepository.save(carEntityToBeSave).entityToModel();
    }

    public Car getCarByCarId(final UUID carId) {
        Optional<CarEntity> carEntityOptional = carRepository.findById(carId);
        if(carEntityOptional.isPresent()) {
            return carEntityOptional.get().entityToModel();
        } else {
            throw new NoSuchElementException("Car not found");
        }
    }

    public List<Car> getCarsByClientId(final Long clientId) {
        return carRepository.findAll().stream()
                .filter(carEntity -> Objects.equals(carEntity.getClientEntity().getClientId(), clientId))
                .map(CarEntity::entityToModel)
                .toList();
    }

    public Car updateCar(final Car newCar) {
        Optional<CarEntity> carEntityOptional = carRepository.findById(newCar.getCarId());
        CarEntity carEntity = newCar.modelToEntity();
        carEntityOptional.ifPresentOrElse(
                entity -> carEntity.setClientEntity(entity.getClientEntity()),
                () -> {
                    throw new NoSuchElementException("Car not found");
                }
        );
        return carRepository.saveAndFlush(carEntity).entityToModel();
    }

}
