package de.scopevisio.vp.backend.data.model;

import de.scopevisio.vp.backend.data.enums.CarBrand;
import de.scopevisio.vp.backend.data.enums.CarType;

import java.math.BigDecimal;

public record Car(
        String carId,
        CarBrand carBrand,
        CarType carType,
        BigDecimal milesPerYear,
        String registeredPostalCode
) {
}
