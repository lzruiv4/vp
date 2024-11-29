package de.scopevisio.vp.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum KilometerleistungFaktorType {

    /**
     * enum with kilometerleistungFaktor
     */
    HALF(BigDecimal.valueOf(0.5)),
    ONE(BigDecimal.ONE),
    ONEANDAHALF(BigDecimal.valueOf(1.5)),
    TWO(BigDecimal.valueOf(2));

    private final BigDecimal kilometerleistungFaktor;
}
