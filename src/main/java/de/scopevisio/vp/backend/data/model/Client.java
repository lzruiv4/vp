package de.scopevisio.vp.backend.data.model;

import de.scopevisio.vp.backend.data.entity.ClientEntity;

import java.util.List;

public record Client(
        Long clientId,
        String firstname,
        String lastname,
        String street,
        String houseNumber,
        String postCode,
        String city,
        List<Car> cars
) {

    public ClientEntity modelToEntity() {
        return new ClientEntity(
                clientId,
                firstname,
                lastname,
                street,
                houseNumber,
                postCode,
                city);
    }
}
