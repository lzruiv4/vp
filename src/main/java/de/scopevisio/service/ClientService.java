package de.scopevisio.service;

import de.scopevisio.data.model.Client;
import de.scopevisio.data.store.CarStore;
import de.scopevisio.data.store.ClientStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientStore clientStore;

    @Autowired
    private CarStore carStore;

    @Autowired
    private RegionalService regionalService;

    /**
     * Add a new client. Automatically get the city based on the specified postal code.
     *
     * @param client is a client.
     * @return client
     */
    public Client createClient(final Client client) {
        automaticSetCity(client);
        return clientStore.createClient(client);
    }

    /**
     * Automatically get the city based on the specified postal code from client.
     * If no corresponding city can be found for the given postal code, the city will be empty.
     * @param client is a client.
     */
    public void automaticSetCity(final Client client) {
        client.setCity(regionalService.getPlzOrts().getOrDefault(client.getPostCode(), ""));
    }

    /**
     * Get a client with all information, include all cars.
     * @param clientId is the id of client.
     *
     * @return client
     */
    public Client getClient(final Long clientId) {
        Client client = clientStore.getClient(clientId);
        client.setCars(carStore.getCarsByClientId(clientId));
        return client;
    }

    /**
     * Get all clients.
     *
     * @return client as list
     */
    public List<Client> getAllClients() {
        return clientStore.getAllClients();
    }

    /**
     * Update the client by new client information.
     * @param newClient is the client with new information.
     *
     * @return client
     */
    public Client updateClient(final Client newClient) {
        newClient.getCars().forEach(carStore::updateCar);
        automaticSetCity(newClient);
        Client result = clientStore.updateClient(newClient);
        result.getCars().addAll(carStore.getCarsByClientId(result.getClientId()));
        return result;
    }
}

