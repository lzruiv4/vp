package de.scopevisio.vp.frontend.view;

import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.data.model.RegionalFromCSV;
import de.scopevisio.vp.backend.data.repository.ClientRepository;
import de.scopevisio.vp.backend.data.store.CarStore;
import de.scopevisio.vp.backend.data.store.ClientStore;
import de.scopevisio.vp.backend.service.CarService;
import de.scopevisio.vp.backend.service.ClientService;
import de.scopevisio.vp.frontend.pmo.ClientPmo;
import de.scopevisio.vp.frontend.pmo.ClientTablePmo;
import de.scopevisio.vp.frontend.pmo.RegionalTablePmo;
import org.linkki.core.binding.dispatcher.behavior.PropertyBehavior;
import org.linkki.core.binding.manager.BindingManager;
import org.linkki.core.binding.manager.DefaultBindingManager;
import org.linkki.core.binding.validation.ValidationService;
import org.linkki.core.ui.creation.VaadinUiCreator;
import org.linkki.core.vaadin.component.page.AbstractPage;
import org.linkki.framework.ui.dialogs.ConfirmationDialog;
import org.linkki.util.handler.Handler;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class VPPage extends AbstractPage {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ClientService clientService;
    private List<Client> clients;

    //private final ClientTablePmo clientTablePmo;

    private final BindingManager bindingManager;

    public VPPage(CarStore carStore, ClientStore clientStore) {
        this.clientService = new ClientService(clientStore, carStore);

        this.clients = clientService.getAllClients();
        this.bindingManager = new DefaultBindingManager();
        //this.clientTablePmo = new ClientTablePmo();
        createContent();
    }

    private void addNewClient() {
        var newClient = new Client();
//        AngebotDialogFactory.neuAngebotHinzufuegen(
//                "Neu Angebot",
//                () -> {  // Handler
//                    angebotRepository.addAngebot(angebot);
//                    search();
//                    getBindingContext().updateUi();
//                },
//                angebot
//        );
    }

    private void addSearchResultSection() {
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
        addSearchResultSection();
    }

    @Override
    protected BindingManager getBindingManager() {
        return bindingManager;
    }

}
