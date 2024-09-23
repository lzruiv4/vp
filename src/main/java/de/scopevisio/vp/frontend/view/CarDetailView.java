package de.scopevisio.vp.frontend.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.service.CarService;
import de.scopevisio.vp.backend.service.ClientService;
import de.scopevisio.vp.backend.service.VersicherungspraemieBerechnenService;
import de.scopevisio.vp.frontend.ui.VPLayout;
import org.linkki.framework.ui.component.Headline;

import java.util.Optional;


@PageTitle("Cars")
@Route(value = "detail", layout = VPLayout.class)
public class CarDetailView extends VerticalLayout implements HasUrlParameter<String> {

    private static final long serialVersionUID = 1L;

    private final ClientService clientService;
    private final CarService carService;
    private final VersicherungspraemieBerechnenService versicherungspraemieBerechnenService;

    private Optional<Client> clientOptional;

    public CarDetailView(
            ClientService clientService,
            CarService carService,
            VersicherungspraemieBerechnenService versicherungspraemieBerechnenService
    ) {
        this.clientService = clientService;
        this.carService = carService;
        this.versicherungspraemieBerechnenService = versicherungspraemieBerechnenService;
        add(new Headline("Cars"));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String clientId) {
        clientOptional = Optional.ofNullable(clientId).map(client -> clientService.getClient(Long.valueOf(clientId)));
        CarDetailPage carDetailPage = new CarDetailPage(clientOptional.get(), carService, versicherungspraemieBerechnenService);
        add(carDetailPage);
    }
}
