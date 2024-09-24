package de.scopevisio.vp.frontend.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.service.CarService;
import de.scopevisio.vp.backend.service.ClientService;
import de.scopevisio.vp.backend.service.VersicherungspraemieBerechnenService;
import de.scopevisio.vp.frontend.ui.VPLayout;
import lombok.AllArgsConstructor;
import org.linkki.framework.ui.component.Headline;

import java.util.Optional;


@PageTitle("Cars")
@Route(value = "detail", layout = VPLayout.class)
@AllArgsConstructor
public class CarDetailView extends VerticalLayout implements HasUrlParameter<String> {

    private static final long serialVersionUID = 1L;

    private final ClientService clientService;
    private final CarService carService;
    private final VersicherungspraemieBerechnenService versicherungspraemieBerechnenService;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String clientId) {
        Optional<Client> clientOptional = Optional.ofNullable(clientId).map(client -> clientService.getClient(Long.valueOf(clientId)));
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            add(new Headline(client.getFirstname() + " " + client.getLastname() + " 's Cars"));
            add(new CarDetailPage(client, carService, versicherungspraemieBerechnenService));
        }
    }
}
