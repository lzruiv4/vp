package de.scopevisio.vp.service;

import de.scopevisio.vp.data.enums.CarType;
import de.scopevisio.vp.data.model.Car;
import de.scopevisio.vp.data.model.factories.KilometerleistungFaktorFactory;
import de.scopevisio.vp.data.model.factories.RegionsFactory;
import de.scopevisio.vp.data.store.CarStore;

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
     * @param car, which to be calculated
     */
    public void berechneRegionType(final Car car) {
        car.setRegionType(new RegionsFactory().setRegion(car.getRegisteredPostalCode()));
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
