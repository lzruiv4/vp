package de.scopevisio.vp.backend.data.model.regionstrategy;

import de.scopevisio.vp.backend.data.enums.RegionType;

public class ARegion implements RegionStrategy {

    @Override
    public RegionType getRegionType() {
        return RegionType.A;
    }

}
