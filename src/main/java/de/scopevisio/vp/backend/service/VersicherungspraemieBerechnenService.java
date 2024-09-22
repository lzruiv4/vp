package de.scopevisio.vp.backend.service;

import de.scopevisio.vp.backend.data.enums.CarType;
import de.scopevisio.vp.backend.data.enums.KilometerleistungFaktorType;
import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.data.model.regionstrategy.*;
import de.scopevisio.vp.backend.data.store.CarStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VersicherungspraemieBerechnenService {

    private final CarStore carStore;

    public BigDecimal berechneVersicherungspraemie(final UUID carId) {
        Car car = carStore.getCarByCarId(carId);
        BigDecimal versicherungspraemie = berechneKilometerleistungFaktor(car.milesPerYear())
                .multiply(berechneFahrzeugTypFakor(car.carType()))
                .multiply(berechneRegionFaktor(car.registeredPostalCode()));

        carStore.updateCar(
                new Car(carId,
                        car.carType(),
                        car.milesPerYear(),
                        car.regionType(),
                        versicherungspraemie,
                        car.registeredPostalCode())
        );
        return versicherungspraemie;
    }

    public BigDecimal berechneKilometerleistungFaktor(final BigDecimal milesPerYear) {
        if(milesPerYear.compareTo(BigDecimal.valueOf(0)) >= 0 &&
                milesPerYear.compareTo(BigDecimal.valueOf(5000)) <= 0) {
            return KilometerleistungFaktorType.HALF.getKilometerleitsungFaktor();
        } else if (milesPerYear.compareTo(BigDecimal.valueOf(5001)) >= 0
                && milesPerYear.compareTo(BigDecimal.valueOf(10000)) <= 0) {
            return KilometerleistungFaktorType.ONE.getKilometerleitsungFaktor();
        } else if (milesPerYear.compareTo(BigDecimal.valueOf(10001)) >= 0
                && milesPerYear.compareTo(BigDecimal.valueOf(20000)) <= 0) {
            return KilometerleistungFaktorType.ONEANDAHALF.getKilometerleitsungFaktor();
        } else if(milesPerYear.compareTo(BigDecimal.valueOf(20000)) > 0) {
            return KilometerleistungFaktorType.TWO.getKilometerleitsungFaktor();
        } else {
            throw new IllegalArgumentException("Something is going wrong, please check your input");
        }
    }

    public BigDecimal berechneFahrzeugTypFakor(final CarType carType) {
        return carType.equals(CarType.LKW) ? CarType.LKW.getFahrzeugTypeFaktor() : CarType.PKW.getFahrzeugTypeFaktor();
    }

    public BigDecimal berechneRegionFaktor(final String postleitzahl) {
        RegionStrategy regionStrategy;
        if (postleitzahl.compareTo("00000") >= 0 && postleitzahl.compareTo("19999") <= 0) {
            regionStrategy = new ARegion();
        } else if (postleitzahl.compareTo("20000") >= 0 && postleitzahl.compareTo("39999") <= 0) {
            regionStrategy = new BRegion();
        } else if (postleitzahl.compareTo("40000") >= 0 && postleitzahl.compareTo("59999") <= 0) {
            regionStrategy = new CRegion();
        } else if (postleitzahl.compareTo("60000") >= 0 && postleitzahl.compareTo("79999") <= 0) {
            regionStrategy = new DRegion();
        } else if (postleitzahl.compareTo("80000") >= 0 && postleitzahl.compareTo("99999") <= 0) {
            regionStrategy = new ERegion();
        } else {
            throw new NumberFormatException("Input is wrong");
        }
        return regionStrategy.getFaktor();
    }

}
