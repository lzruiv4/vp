package de.scopevisio.vp.backend.service;

import de.scopevisio.vp.backend.data.enums.CarType;
import de.scopevisio.vp.backend.data.enums.RegionType;
import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.data.store.CarStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarStore carStore;

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
     */
    public Car addCar(final CarType carType,
                      final BigDecimal milesPerYear,
                      final RegionType regionType,
                      final BigDecimal versicherungspraemie,
                      final String registeredPostalCode,
                      final Long clientId
    ) {
        return carStore.addCar(carType, milesPerYear, regionType, versicherungspraemie, registeredPostalCode, clientId);
    }

    /**
     * Get all cars with client id.
     *
     * @param clientId is the id of client.
     * @return car as list
     */
    public List<Car> getCarsByClientId(final Long clientId) {
        return carStore.getCarsByClientId(clientId);
    }

    /**
     * Update a car.
     *
     * @param newCar is a car with all new information.
     * @return car, which up to date
     */
    public Car updateCar(final Car newCar) {
        return carStore.updateCar(newCar);
    }
}
