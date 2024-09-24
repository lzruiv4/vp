package de.scopevisio.vp.backend.service;

import de.scopevisio.vp.backend.data.enums.CarType;
import de.scopevisio.vp.backend.data.enums.KilometerleistungFaktorType;
import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.data.model.regionstrategy.*;
import de.scopevisio.vp.backend.data.store.CarStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class VersicherungspraemieBerechnenService {

    private final CarStore carStore;

    public BigDecimal berechneVersicherungspraemie(final Car car) {
        BigDecimal versicherungspraemie = berechneKilometerleistungFaktor(car.getMilesPerYear())
                .multiply(berechneFahrzeugTypFaktor(car.getCarType()))
                .multiply(berechneRegionFaktor(car)).setScale(3, RoundingMode.HALF_UP);

        car.setVersicherungspraemie(versicherungspraemie);
        carStore.updateCar(car);
        return versicherungspraemie;
    }

    public BigDecimal berechneKilometerleistungFaktor(final BigDecimal milesPerYear) {
        if (milesPerYear.compareTo(BigDecimal.valueOf(0)) >= 0 &&
                milesPerYear.compareTo(BigDecimal.valueOf(5000)) <= 0) {
            return KilometerleistungFaktorType.HALF.getKilometerleistungFaktor();
        } else if (milesPerYear.compareTo(BigDecimal.valueOf(5001)) >= 0
                && milesPerYear.compareTo(BigDecimal.valueOf(10000)) <= 0) {
            return KilometerleistungFaktorType.ONE.getKilometerleistungFaktor();
        } else if (milesPerYear.compareTo(BigDecimal.valueOf(10001)) >= 0
                && milesPerYear.compareTo(BigDecimal.valueOf(20000)) <= 0) {
            return KilometerleistungFaktorType.ONEANDAHALF.getKilometerleistungFaktor();
        } else if (milesPerYear.compareTo(BigDecimal.valueOf(20000)) > 0) {
            return KilometerleistungFaktorType.TWO.getKilometerleistungFaktor();
        } else {
            throw new IllegalArgumentException("Something is going wrong, please check your input");
        }
    }

    public BigDecimal berechneFahrzeugTypFaktor(final CarType carType) {
        return carType.equals(CarType.LKW) ? CarType.LKW.getFahrzeugTypeFaktor() : CarType.PKW.getFahrzeugTypeFaktor();
    }

    public void berechneRegionType(final Car car) {
        RegionStrategy regionStrategy;
        if (car.getRegisteredPostalCode().compareTo("00000") >= 0 && car.getRegisteredPostalCode().compareTo("19999") <= 0) {
            regionStrategy = new ARegion();
        } else if (car.getRegisteredPostalCode().compareTo("20000") >= 0 && car.getRegisteredPostalCode().compareTo("39999") <= 0) {
            regionStrategy = new BRegion();
        } else if (car.getRegisteredPostalCode().compareTo("40000") >= 0 && car.getRegisteredPostalCode().compareTo("59999") <= 0) {
            regionStrategy = new CRegion();
        } else if (car.getRegisteredPostalCode().compareTo("60000") >= 0 && car.getRegisteredPostalCode().compareTo("79999") <= 0) {
            regionStrategy = new DRegion();
        } else {
            regionStrategy = new ERegion();
        }
        car.setRegionType(regionStrategy.getRegionType());
        carStore.updateCar(car);
    }

    public BigDecimal berechneRegionFaktor(final Car car) {
        berechneRegionType(car);
        return car.getRegionType().getRegionFaktor();
    }

}
