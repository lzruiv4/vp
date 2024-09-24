package de.scopevisio.vp.backend.service;

import de.scopevisio.vp.backend.data.enums.CarType;
import de.scopevisio.vp.backend.data.enums.RegionType;
import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.data.store.CarStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarStore carStore;

    public Car addCar(final CarType carType,
                      final BigDecimal milesPerYear,
                      final RegionType regionType,
                      final BigDecimal versicherungspraemie,
                      final String registeredPostalCode,
                      final Long clientId
    ) {
        return carStore.addCar(carType, milesPerYear, regionType, versicherungspraemie, registeredPostalCode, clientId);
    }

    public List<Car> getCarsByClientId(final Long clientId) {
        return carStore.getCarsByClientId(clientId);
    }

    public Car updateCar(final Car newCar) {
        return carStore.updateCar(newCar);
    }
}
