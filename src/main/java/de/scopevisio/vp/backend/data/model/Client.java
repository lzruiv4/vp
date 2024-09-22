package de.scopevisio.vp.backend.data.model;

import java.util.List;

public record Client(
        Long clientId,
        String firstname,
        String lastname,
        List<Car> cars
) {
}
