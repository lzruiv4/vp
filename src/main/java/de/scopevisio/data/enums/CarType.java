package de.scopevisio.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum CarType {

    /**
     * enum with fahrzeugTypeFaktor
     */
    LKW(BigDecimal.TEN),
    PKW(BigDecimal.ONE);

    private final BigDecimal fahrzeugTypeFaktor;

}
