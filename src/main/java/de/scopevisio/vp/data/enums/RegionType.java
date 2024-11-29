package de.scopevisio.vp.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum RegionType {

    /**
     * enum with regionFaktor
     * RegionType A is the postal code from 00000 to 19999, and its regionFaktor is 1.2
     * RegionType B is the postal code from 20000 to 39999, and its regionFaktor is 1.4
     * RegionType C is the postal code from 40000 to 59999, and its regionFaktor is 1.6
     * RegionType D is the postal code from 60000 to 79999, and its regionFaktor is 1.8
     * RegionType E is the postal code from 80000 to 99999, and its regionFaktor is 2.0
     * */
    A(BigDecimal.valueOf(1.2)),  // PLZ from 00000 to 19999
    B(BigDecimal.valueOf(1.4)),  // PLZ from 20000 to 39999
    C(BigDecimal.valueOf(1.6)),  // PLZ from 40000 to 59999
    D(BigDecimal.valueOf(1.8)),  // PLZ from 60000 to 79999
    E(BigDecimal.valueOf(2.0));  // PLZ from 80000 to 99999

    private final BigDecimal regionFaktor;

}
