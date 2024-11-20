package de.scopevisio.vp.backend.data.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import de.scopevisio.vp.backend.data.enums.KilometerleistungFaktorType;
import de.scopevisio.vp.backend.data.store.exception.NotFoundException;
import lombok.AllArgsConstructor;

public class KilometerleistungFaktorFactory {

    private final static Map<KilometerleitsungsRange, KilometerleistungFaktorType> kilometerleistungFaktorFactory = new HashMap<>();

    static {
        kilometerleistungFaktorFactory.put(
                new KilometerleitsungsRange(BigDecimal.ZERO, BigDecimal.valueOf(5000)),
                KilometerleistungFaktorType.HALF);
        kilometerleistungFaktorFactory.put(
                new KilometerleitsungsRange(BigDecimal.valueOf(5001), BigDecimal.valueOf(10000)),
                KilometerleistungFaktorType.ONE);
        kilometerleistungFaktorFactory.put(
                new KilometerleitsungsRange(BigDecimal.valueOf(10001), BigDecimal.valueOf(20000)),
                KilometerleistungFaktorType.ONEANDAHALF);
        kilometerleistungFaktorFactory.put(
                new KilometerleitsungsRange(BigDecimal.valueOf(20001), null),
                KilometerleistungFaktorType.TWO);
    }

    public KilometerleistungFaktorType setFaktor(final BigDecimal milesPerYear) {
        return kilometerleistungFaktorFactory.get(
                kilometerleistungFaktorFactory.keySet().stream()
                        .filter(range -> range.isInRange(milesPerYear))
                        .findFirst()
                        .orElseThrow(
                                () -> new NotFoundException("Something went wrong")
                        )
        );
    }

    @AllArgsConstructor
    static class KilometerleitsungsRange {

        private final BigDecimal lower;
        private final BigDecimal upper;

        boolean isInRange(BigDecimal milesPerYear) {
            return upper == null && milesPerYear.compareTo(lower) > 0
                    ? true
                    : milesPerYear.compareTo(lower) >= 0 && milesPerYear.compareTo(upper) <= 0;
        }

    }
}
