package de.scopevisio.vp.backend.controller;

import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.service.CarService;
import de.scopevisio.vp.backend.service.VersicherungspraemieBerechnenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final VersicherungspraemieBerechnenService versicherungspraemieBerechnenService;

    @PostMapping(value = "/create")
    public ResponseEntity<Car> addCar(@RequestParam Long clientId, @RequestBody Car car) {
        Car carToBeSave = carService.addCar(
                car.getCarType(),
                car.getMilesPerYear(),
                car.getRegionType(),
                car.getVersicherungspraemie(),
                car.getRegisteredPostalCode(),
                clientId
        );

        versicherungspraemieBerechnenService.berechneRegionType(carToBeSave);

        return new ResponseEntity<>(new Car(
                carToBeSave.getCarId(),
                carToBeSave.getCarType(),
                carToBeSave.getMilesPerYear(),
                carToBeSave.getRegionType(),
                versicherungspraemieBerechnenService.berechneVersicherungspraemie(carToBeSave.getCarId()),
                carToBeSave.getRegisteredPostalCode()
        ), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        return new ResponseEntity<>(carService.updateCar(car), HttpStatus.OK);
    }

}
