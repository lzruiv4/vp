package de.scopevisio.vp.backend.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum KilometerleistungFaktorType {

    HALF(BigDecimal.valueOf(0.5)),
    ONE(BigDecimal.ONE),
    ONEANDAHALF(BigDecimal.valueOf(1.5)),
    TWO(BigDecimal.valueOf(2));

    private final BigDecimal kilometerleitsungFaktor;
}
