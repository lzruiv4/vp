package de.scopevisio.vp.backend.data.entity;

import de.scopevisio.vp.backend.data.model.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long clientId;

    private String firstname;

    private String lastname;

    private String street;

    private String houseNumber;

    private String postCode;

    private String city;

    /**
     * Convert entity to model
     */
    public Client entityToModel() {
        return new Client(
                clientId,
                firstname,
                lastname,
                street,
                houseNumber,
                postCode,
                city,
                new ArrayList<>());
    }

}
