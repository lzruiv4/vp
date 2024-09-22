package de.scopevisio.vp.backend.data.model.regionstrategy;

import de.scopevisio.vp.backend.data.enums.RegionType;

import java.math.BigDecimal;

public interface RegionStrategy {

    RegionType getRegionType();

    public default BigDecimal getFaktor() {
        return getRegionType().getRegionFaktor();
    }
}
