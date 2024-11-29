package de.scopevisio.vp.data.model.factories;

import de.scopevisio.vp.data.enums.RegionType;
import de.scopevisio.vp.data.store.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class RegionsFactory {

    private final static Map<RegionsRange, RegionType> regionsFactory = new HashMap<>();

    static {
        regionsFactory.put(new RegionsRange("00000", "19999"), RegionType.A);
        regionsFactory.put(new RegionsRange("20000", "39999"), RegionType.B);
        regionsFactory.put(new RegionsRange("40000", "59999"), RegionType.C);
        regionsFactory.put(new RegionsRange("60000", "79999"), RegionType.D);
        regionsFactory.put(new RegionsRange("80000", "99999"), RegionType.E);
    }

    public RegionType setRegion(final String postalCode) {
        return regionsFactory.get(
                regionsFactory.keySet().stream()
                        .filter(range -> range.isRange(postalCode))
                        .findFirst()
                        .orElseThrow(
                                () -> new NotFoundException("Something went wrong")));
    }

    static class RegionsRange extends Range<String> {

        public RegionsRange(String lower, String upper) {
            super(lower, upper);
        }

        @Override
        public boolean isRange(String postalCode) {
            return postalCode.compareTo(lower) >= 0 && postalCode.compareTo(upper) <= 0;
        }

    }

}
