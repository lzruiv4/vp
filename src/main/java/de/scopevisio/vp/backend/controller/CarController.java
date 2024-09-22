package de.scopevisio.vp.backend.controller;

import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping(value = "/create")
    public ResponseEntity<Car> addCar(@RequestParam Long clientId, @RequestBody Car car) {
        Car carToBeSave = carService.addCar(
                car.carType(),
                car.milesPerYear(),
                car.regionType(),
                car.versicherungspraemie(),
                car.registeredPostalCode(),
                clientId
        );
        return new ResponseEntity<>(carToBeSave, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        return new ResponseEntity<>(carService.updateCar(car), HttpStatus.OK);
    }

}
