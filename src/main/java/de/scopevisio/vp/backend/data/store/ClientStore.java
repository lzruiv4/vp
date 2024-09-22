package de.scopevisio.vp.backend.data.store;

import de.scopevisio.vp.backend.data.entity.ClientEntity;
import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.data.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientStore {

    private final ClientRepository clientRepository;

    public Client createClient(final String firstname, final String lastname){
        ClientEntity clientEntityToBeSave = new ClientEntity();
        clientEntityToBeSave.setFirstname(firstname);
        clientEntityToBeSave.setLastname(lastname);
        return clientRepository.save(clientEntityToBeSave).entityToModel();
    }

    public Client getClient(final Long clientId) {
        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(clientId);
        return clientEntityOptional.map(ClientEntity::entityToModel).orElse(null);
    }
}
