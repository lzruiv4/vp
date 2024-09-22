package de.scopevisio.vp.backend.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum RegionType {

    A(BigDecimal.valueOf(1.2)),  // PLZ from 00000 to 19999
    B(BigDecimal.valueOf(1.4)),  // PLZ from 20000 to 39999
    C(BigDecimal.valueOf(1.6)),  // PLZ from 40000 to 59999
    D(BigDecimal.valueOf(1.8)),  // PLZ from 60000 to 79999
    E(BigDecimal.valueOf(2));    // PLZ from 80000 to 99999

    private final BigDecimal regionFaktor;

}
