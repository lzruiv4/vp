package de.scopevisio.vp.frontend.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.scopevisio.vp.backend.data.repository.CarRepository;
import de.scopevisio.vp.backend.data.repository.ClientRepository;
import de.scopevisio.vp.backend.data.store.CarStore;
import de.scopevisio.vp.backend.data.store.ClientStore;
import de.scopevisio.vp.frontend.ui.VPLayout;
import org.linkki.framework.ui.component.Headline;

@PageTitle("de/scopevisio/vp")
@Route(value = "clients", layout = VPLayout.class)
public class VPView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    public VPView(CarRepository carRepository, ClientRepository clientRepository) {
        add(new Headline("Clients Übersicht"));
        setSizeFull();
        VPPage page = new VPPage(new CarStore(carRepository, clientRepository), new ClientStore(clientRepository));
        add(page);
    }
}