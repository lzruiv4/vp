package de.scopevisio.vp.backend.service;

import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.data.store.CarStore;
import de.scopevisio.vp.backend.data.store.ClientStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientStore clientStore;

    private final CarStore carStore;

    public Client createClient(final String firstname, final String lastname){
        return clientStore.createClient(firstname, lastname);
    }

    public Client getClient(final Long clientId){
        Client client = clientStore.getClient(clientId);
        if(client == null) throw new RuntimeException("Client not found");
        else {
            // List<RealEstate> realEstates = realEstateStore.getRealEstateByClientId(clientId);
            return new Client(clientId, client.firstname(), client.lastname(), carStore.getCarsByClientId(clientId));
        }
    }
}
