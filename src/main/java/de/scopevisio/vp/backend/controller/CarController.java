package de.scopevisio.vp.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.service.CarService;
import de.scopevisio.vp.backend.service.VersicherungspraemieBerechnenService;
import lombok.RequiredArgsConstructor;

/**
 * This controller handles all operations related to managing Car.
 * It provides endpoints for creating and updating car data.
 */
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
// @CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarService carService;
    private final VersicherungspraemieBerechnenService versicherungspraemieBerechnenService;

    /**
     * Create a new car.
     *
     * @param car with all information.
     * @return a ResponseEntity containing car object.
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Car> addCar(@RequestParam Long clientId, @RequestBody Car car) {
        Car carToBeSave = carService.addCar(
                car.getCarType(),
                car.getMilesPerYear(),
                car.getRegionType(),
                car.getVersicherungspraemie(),
                car.getRegisteredPostalCode(),
                clientId);

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
