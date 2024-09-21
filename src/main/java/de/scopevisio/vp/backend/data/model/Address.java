package de.scopevisio.vp.backend.data.model;

public record Address(
        String street,
        String hNumber,
        String postCode,
        String city
) {
}
