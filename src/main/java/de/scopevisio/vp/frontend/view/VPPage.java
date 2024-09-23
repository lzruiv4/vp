package de.scopevisio.vp.frontend.view;

import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.data.store.CarStore;
import de.scopevisio.vp.backend.data.store.ClientStore;
import de.scopevisio.vp.backend.service.ClientService;
import de.scopevisio.vp.frontend.pmo.ClientDialog;
import de.scopevisio.vp.frontend.pmo.ClientTablePmo;
import org.linkki.core.binding.manager.BindingManager;
import org.linkki.core.binding.manager.DefaultBindingManager;
import org.linkki.core.ui.creation.VaadinUiCreator;
import org.linkki.core.vaadin.component.page.AbstractPage;

import java.io.Serial;
import java.util.List;

public class VPPage extends AbstractPage {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ClientService clientService;
    private List<Client> clients;

    private final BindingManager bindingManager;

    public VPPage(CarStore carStore, ClientStore clientStore) {
        this.clientService = new ClientService(clientStore, carStore);

        this.clients = clientService.getAllClients();
        this.bindingManager = new DefaultBindingManager();

        createContent();
    }

    private void addNewClient() {
        var newClient = new Client();
        ClientDialog.addNewClient(
                "Neu Client",
                () -> {  // Handler
                    clientService.createClient(newClient);
                    getBindingContext().updateUi();
                },
                newClient
        );
    }

    private void getClients() {
        add(VaadinUiCreator
                .createComponent(new ClientTablePmo(
                                () -> clients,
                                this::addNewClient
                        ), getBindingContext()
                )
        );
    }

    @Override
    public void createContent() {
        getClients();
    }

    @Override
    protected BindingManager getBindingManager() {
        return bindingManager;
    }

}
