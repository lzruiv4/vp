package de.scopevisio.vp.backend.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum CarType {

    LKW(BigDecimal.TEN),
    PKW(BigDecimal.ONE);

    private final BigDecimal fahrzeugTypeFaktor;

}
