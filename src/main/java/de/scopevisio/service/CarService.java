package de.scopevisio.service;

import de.scopevisio.data.model.Car;
import de.scopevisio.data.store.CarStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarStore carStore;

    /**
     * Add a new car.
     *
     * @param car                  is the new car
     * @param clientId             is the client's id.
     * @return car
     */
    public Car addCar(final Car car, final Long clientId) {
        return carStore.addCar(car, clientId);
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

