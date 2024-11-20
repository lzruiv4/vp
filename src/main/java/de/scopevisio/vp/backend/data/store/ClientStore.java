package de.scopevisio.vp.backend.data.store;

import de.scopevisio.vp.backend.data.entity.ClientEntity;
import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.data.repository.ClientRepository;

import de.scopevisio.vp.backend.data.store.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ClientStore {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Add a new client.
     *
     * @param client is a client.
     * @return client
     */
    public Client createClient(final Client client) {
        return clientRepository.save(client.modelToEntity()).entityToModel();
    }

    /**
     * Get the client by client id.
     *
     * @param clientId is the id of client.
     * @return client
     * @throws NotFoundException if client not found.
     */
    public Client getClient(final Long clientId) {
        return clientRepository.findById(clientId)
                .map(ClientEntity::entityToModel)
                .orElseThrow(() -> new NotFoundException("Client not found"));
    }

    /**
     * Get all clients.
     *
     * @return client as list
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll().stream().map(ClientEntity::entityToModel).toList();
    }

    /**
     * Update the client by new client information.
     *
     * @param newClient is the client with new information.
     * @return client
     * @throws NotFoundException if client not found.
     */
    public Client updateClient(final Client newClient) {
        ClientEntity clientEntity = clientRepository.findById(newClient.getClientId())
                .orElseThrow(() -> new NotFoundException("Client not found"));
        return clientRepository.saveAndFlush(mapTheNewClient(clientEntity, newClient.modelToEntity())).entityToModel();
    }

    /**
     * Update the information with new client information.
     *
     * @param oldClient is an old clientEntity from database.
     * @param newClient is a new clientEntity.
     * @return clientEntity
     */
    private ClientEntity mapTheNewClient(ClientEntity oldClient, ClientEntity newClient) {
        if (!Objects.equals(oldClient.getFirstname(), newClient.getFirstname()) && newClient.getFirstname() != null) {
            oldClient.setFirstname(newClient.getFirstname());
        }

        if (!Objects.equals(oldClient.getLastname(), newClient.getLastname()) && newClient.getLastname() != null) {
            oldClient.setLastname(newClient.getLastname());
        }

        if (!Objects.equals(oldClient.getStreet(), newClient.getStreet()) && newClient.getStreet() != null) {
            oldClient.setStreet(newClient.getStreet());
        }

        if (!Objects.equals(oldClient.getHouseNumber(), newClient.getHouseNumber())
                && newClient.getHouseNumber() != null) {
            oldClient.setHouseNumber(newClient.getHouseNumber());
        }

        if (!Objects.equals(oldClient.getPostCode(), newClient.getPostCode()) && newClient.getPostCode() != null) {
            oldClient.setPostCode(newClient.getPostCode());
        }

        if (!Objects.equals(oldClient.getCity(), newClient.getCity()) && newClient.getCity() != null) {
            oldClient.setCity(newClient.getCity());
        }

        return oldClient;
    }
}
