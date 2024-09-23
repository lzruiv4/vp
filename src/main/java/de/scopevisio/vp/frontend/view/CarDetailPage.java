package de.scopevisio.vp.frontend.view;

import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.service.CarService;
import de.scopevisio.vp.backend.service.VersicherungspraemieBerechnenService;
import de.scopevisio.vp.frontend.pmo.CarDialog;
import de.scopevisio.vp.frontend.pmo.CarTablePmo;
import org.linkki.core.binding.manager.BindingManager;
import org.linkki.core.binding.manager.DefaultBindingManager;
import org.linkki.core.ui.creation.VaadinUiCreator;
import org.linkki.core.ui.element.annotation.UILink;
import org.linkki.core.vaadin.component.page.AbstractPage;

import java.io.Serial;

public class CarDetailPage extends AbstractPage {

    @Serial
    private static final long serialVersionUID = 1L;
    private final BindingManager bindingManager;

    private final CarService carService;
    private final VersicherungspraemieBerechnenService versicherungspraemieBerechnenService;
    private Client client;

    public CarDetailPage(Client client, CarService carService, VersicherungspraemieBerechnenService versicherungspraemieBerechnenService) {
        this.carService = carService;
        this.versicherungspraemieBerechnenService = versicherungspraemieBerechnenService;
        this.client = client;

        this.bindingManager = new DefaultBindingManager();

        createContent();
    }

    private void addNewCar() {
        var newCar = new Car();
        CarDialog.addNewCar(
                "Neu Car",
                () -> {  // Handler
                    carService.addCar(
                            newCar.getCarType(),
                            newCar.getMilesPerYear(),
                            newCar.getRegionType(),
                            newCar.getVersicherungspraemie(),
                            newCar.getRegisteredPostalCode(),
                            client.getClientId());
                    getBindingContext().updateUi();
                },
                newCar
        );
    }

    private void getResult() {
        updateCarVersicherungspraemie();
        add(VaadinUiCreator
                .createComponent(new CarTablePmo(
                                () -> carService.getCarsByClientId(client.getClientId()),
                                this::addNewCar
                        ), getBindingContext()
                )
        );
    }

    private void updateCarVersicherungspraemie() {
        client.getCars().forEach(car -> {
            if(car.getVersicherungspraemie() == null)  {
                versicherungspraemieBerechnenService.berechneVersicherungspraemie(car.getCarId());
            }
        });
    }

    @Override
    protected BindingManager getBindingManager() {
        return bindingManager;
    }

    @Override
    public void createContent() {
        getResult();
    }
}
