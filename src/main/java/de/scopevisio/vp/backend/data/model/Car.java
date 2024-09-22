package de.scopevisio.vp.backend.data.model;

import de.scopevisio.vp.backend.data.entity.CarEntity;
import de.scopevisio.vp.backend.data.enums.CarType;
import de.scopevisio.vp.backend.data.enums.RegionType;

import java.math.BigDecimal;
import java.util.UUID;

public record Car(
        UUID carId,
        CarType carType,
        BigDecimal milesPerYear,
        RegionType regionType,
        BigDecimal versicherungspraemie,
        String registeredPostalCode
) {
    public CarEntity modelToEntity() {
        return new CarEntity(
                carId,
                carType,
                milesPerYear,
                regionType,
                versicherungspraemie,
                registeredPostalCode,
                null
        );
    }
}
