package de.scopevisio.data.model;

import de.scopevisio.data.entity.CarEntity;
import de.scopevisio.data.enums.CarType;
import de.scopevisio.data.enums.RegionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {

    private UUID carId;
    private CarType carType;
    private BigDecimal milesPerYear;
    private RegionType regionType;
    private BigDecimal versicherungspraemie;
    private String registeredPostalCode;

    /**
     * Convert model to entity
     */
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

