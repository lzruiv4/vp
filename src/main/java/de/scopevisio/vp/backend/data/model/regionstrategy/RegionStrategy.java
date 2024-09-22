package de.scopevisio.vp.backend.data.model.regionstrategy;

import de.scopevisio.vp.backend.data.enums.RegionType;

import java.math.BigDecimal;

public interface RegionStrategy {

    RegionType getRegionType(String plz);

    public default BigDecimal getRegionFakor(String plz) {
        return getRegionType(plz).getRegionFaktor();
    }
}
