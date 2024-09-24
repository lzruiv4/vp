package de.scopevisio.vp.frontend.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.scopevisio.vp.backend.service.ClientService;
import de.scopevisio.vp.frontend.ui.VPLayout;
import org.linkki.framework.ui.component.Headline;

@PageTitle("VP System")
@Route(value = "/clients", layout = VPLayout.class)
public class ClientView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    public ClientView(ClientService clientService) {
        add(new Headline("Clients overview"));
        setSizeFull();
        ClientPage page = new ClientPage(clientService);
        add(page);
        UI.getCurrent().navigate(ClientView.class);
    }
}