package de.scopevisio.data.model;

import de.scopevisio.data.entity.ClientEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    private Long clientId;
    private String firstname;
    private String lastname;
    private String street;
    private String houseNumber;
    private String postCode;
    private String city;
    private List<Car> cars;

    /**
     * Convert model to entity
     */
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

