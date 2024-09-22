package de.scopevisio.vp.backend.data.entity;

import de.scopevisio.vp.backend.data.model.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long addressId;

    private String street;

    private String hNumber;

    private String postCode;

    private String city;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private ClientEntity clientEntity;

    public Address entityToModel(){
        return new Address(street, hNumber, postCode, city);
    }

}
