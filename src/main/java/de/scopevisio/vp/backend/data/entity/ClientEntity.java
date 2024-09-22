package de.scopevisio.vp.backend.data.entity;

import de.scopevisio.vp.backend.data.model.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Client entityToModel(){
        return new Client(clientId, firstname, lastname);
    }

}
