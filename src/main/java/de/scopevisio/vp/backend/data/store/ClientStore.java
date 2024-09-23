package de.scopevisio.vp.backend.data.store;

import de.scopevisio.vp.backend.data.entity.ClientEntity;
import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.data.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientStore {

    private final ClientRepository clientRepository;


    public Client createClient(final Client client){
        return clientRepository.save(client.modelToEntity()).entityToModel();
    }

    public Client getClient(final Long clientId) {
        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(clientId);
        return clientEntityOptional
                .map(ClientEntity::entityToModel)
                .orElseThrow(() -> new NoSuchElementException("Client not found"));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll().stream().map(ClientEntity::entityToModel).toList();
    }

    public Client updateClient(final Client newClient) {
        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(newClient.getClientId());
        ClientEntity clientEntity = newClient.modelToEntity();
        if(clientEntityOptional.isPresent()) {
            return clientRepository.saveAndFlush(clientEntity).entityToModel();
        } else {
            throw new NoSuchElementException("Client not found");
        }
    }
}
