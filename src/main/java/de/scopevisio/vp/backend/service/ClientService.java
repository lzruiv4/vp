package de.scopevisio.vp.backend.service;

import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.data.store.CarStore;
import de.scopevisio.vp.backend.data.store.ClientStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientStore clientStore;
    private final CarStore carStore;

    public Client createClient(final Client client){
        return clientStore.createClient(client);
    }

    public Client getClient(final Long clientId){
        Client client = clientStore.getClient(clientId);
        if(client == null) throw new RuntimeException("Client not found");
        else {
            return new Client(
                    clientId,
                    client.getFirstname(),
                    client.getLastname(),
                    client.getStreet(),
                    client.getHouseNumber(),
                    client.getPostCode(),
                    client.getCity(),
                    carStore.getCarsByClientId(clientId));
        }
    }

    public List<Client> getAllClients() {
        return clientStore.getAllClients();
    }

    public Client updateClient(final Client newClient) {
        newClient.getCars().forEach(carStore::updateCar);
        Client result = clientStore.updateClient(newClient);
        result.getCars().addAll(carStore.getCarsByClientId(result.getClientId()));
        return result;
    }
}
