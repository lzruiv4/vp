package de.scopevisio.vp.backend.data.model.regionstrategy;

import de.scopevisio.vp.backend.data.enums.RegionType;

public class ERegion implements RegionStrategy{

    @Override
    public RegionType getRegionType(String plz) {
        return RegionType.E;
    }
    
}
