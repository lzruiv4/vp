package de.scopevisio.controller;

import de.scopevisio.data.model.Car;
import de.scopevisio.service.CarService;
import de.scopevisio.service.VersicherungspraemieBerechnenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles all operations related to managing Car.
 * It provides endpoints for creating and updating car data.
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private VersicherungspraemieBerechnenService versicherungspraemieBerechnenService;

    /**
     * Create a new car.
     *
     * @param car with all information.
     * @return a ResponseEntity containing car object.
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Car> addCar(@RequestParam Long clientId, @RequestBody Car car) {
        Car carToBeSave = carService.addCar(car, clientId);
        versicherungspraemieBerechnenService.berechneVersicherungspraemie(carToBeSave);
        return new ResponseEntity<>(carToBeSave, HttpStatus.CREATED);
    }

    /**
     * Update a car.
     *
     * @param car with all new information.
     * @return a ResponseEntity containing car object.
     */
    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        versicherungspraemieBerechnenService.berechneVersicherungspraemie(car);
        return new ResponseEntity<>(carService.updateCar(car), HttpStatus.OK);
    }

}
