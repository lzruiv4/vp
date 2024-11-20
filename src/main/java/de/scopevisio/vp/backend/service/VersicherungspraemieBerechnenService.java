package de.scopevisio.vp.backend.service;

import de.scopevisio.vp.backend.data.enums.CarType;
import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.data.model.KilometerleistungFaktorFactory;
import de.scopevisio.vp.backend.data.model.regionstrategy.*;
import de.scopevisio.vp.backend.data.store.CarStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class VersicherungspraemieBerechnenService {

    @Autowired
    private CarStore carStore;

    /**
     * Calculate the car versicherungspraemie.
     *
     * @param car , which to be calculated.
     */
    public void berechneVersicherungspraemie(final Car car) {
        BigDecimal versicherungspraemie = berechneKilometerleistungFaktor(car.getMilesPerYear())
                .multiply(berechneFahrzeugTypFaktor(car.getCarType()))
                .multiply(berechneRegionFaktor(car)).setScale(3, RoundingMode.HALF_UP);

        car.setVersicherungspraemie(versicherungspraemie);
        carStore.updateCar(car);
    }

    /**
     * Calculate KilometerleistungFaktor.
     * If milesPerYear is between 0 and 5000, it will return 0.5
     * If milesPerYear is between 5001 and 10000, it will return 1.0
     * If milesPerYear is between 10001 and 20000, it will return 1.5
     * If milesPerYear is bigger than 20000, it will return 2.0
     *
     * @param milesPerYear is the average mileage per year.
     * @return KilometerleistungFaktor
     */
    public BigDecimal berechneKilometerleistungFaktor(final BigDecimal milesPerYear) {
        // if (milesPerYear.compareTo(BigDecimal.valueOf(0)) >= 0 &&
        //         milesPerYear.compareTo(BigDecimal.valueOf(5000)) <= 0) {
        //     return KilometerleistungFaktorType.HALF.getKilometerleistungFaktor();
        // } else if (milesPerYear.compareTo(BigDecimal.valueOf(5001)) >= 0
        //         && milesPerYear.compareTo(BigDecimal.valueOf(10000)) <= 0) {
        //     return KilometerleistungFaktorType.ONE.getKilometerleistungFaktor();
        // } else if (milesPerYear.compareTo(BigDecimal.valueOf(10001)) >= 0
        //         && milesPerYear.compareTo(BigDecimal.valueOf(20000)) <= 0) {
        //     return KilometerleistungFaktorType.ONEANDAHALF.getKilometerleistungFaktor();
        // } else if (milesPerYear.compareTo(BigDecimal.valueOf(20000)) > 0) {
        //     return KilometerleistungFaktorType.TWO.getKilometerleistungFaktor();
        // } else {
        //     throw new IllegalArgumentException("Something is going wrong, please check your input");
        // }
        return new KilometerleistungFaktorFactory().setFaktor(milesPerYear).getKilometerleistungFaktor();
    }

    /**
     * Calculate the FahrzeugTypFaktor, based on car type.
     *
     * @param carType is a car type. CarType.LKW or CarType.PKW
     * @return FahrzeugTypFaktor
     */
    public BigDecimal berechneFahrzeugTypFaktor(final CarType carType) {
        return carType.equals(CarType.LKW) ? CarType.LKW.getFahrzeugTypeFaktor() : CarType.PKW.getFahrzeugTypeFaktor();
    }

    /**
     * Calculate RegionType, based on postal code.
     * If postal code is between 00000 and 19999, it will return ARegion.
     * If postal code is between 20000 and 39999, it will return BRegion.
     * If postal code is between 40000 and 59999, it will return CRegion.
     * If postal code is between 60000 and 79999, it will return DRegion.
     * If postal code is between 80000 and 99999, it will return ERegion.
     *
     * @param car, which to be calculated
     */
    public void berechneRegionType(final Car car) {
        RegionStrategy regionStrategy;
        if (car.getRegisteredPostalCode().compareTo("00000") >= 0 
        && car.getRegisteredPostalCode().compareTo("19999") <= 0) {
            regionStrategy = new ARegion();
        } else if (car.getRegisteredPostalCode().compareTo("20000") >= 0 
                && car.getRegisteredPostalCode().compareTo("39999") <= 0) {
            regionStrategy = new BRegion();
        } else if (car.getRegisteredPostalCode().compareTo("40000") >= 0 
                && car.getRegisteredPostalCode().compareTo("59999") <= 0) {
            regionStrategy = new CRegion();
        } else if (car.getRegisteredPostalCode().compareTo("60000") >= 0 
                && car.getRegisteredPostalCode().compareTo("79999") <= 0) {
            regionStrategy = new DRegion();
        } else {
            regionStrategy = new ERegion();
        }
        car.setRegionType(regionStrategy.getRegionType());
        carStore.updateCar(car);
    }

    /**
     * Calculate the RegionFaktor, based on region type.
     *
     * @param car is the car
     * @return RegionFaktor
     */
    public BigDecimal berechneRegionFaktor(final Car car) {
        berechneRegionType(car);
        return car.getRegionType().getRegionFaktor();
    }

}
