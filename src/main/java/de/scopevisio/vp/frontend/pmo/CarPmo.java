package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Car;
import lombok.AllArgsConstructor;
import org.linkki.core.defaults.ui.aspects.types.EnabledType;
import org.linkki.core.pmo.ModelObject;
import org.linkki.core.ui.element.annotation.UIComboBox;
import org.linkki.core.ui.element.annotation.UITextField;
import org.linkki.core.ui.layout.annotation.UIVerticalLayout;

@UIVerticalLayout
@AllArgsConstructor
public class CarPmo {

    private final Car car;
    private boolean isInDialog = false;

    @ModelObject
    public Car getCar() {
        return car;
    }

    @UIComboBox(position = 20, label = "Car Type", modelAttribute = "carType", enabled = EnabledType.DYNAMIC)
    public void carType() {
        // model binding
    }

    public boolean isCarTypeEnabled() {
        return isInDialog;
    }

    @UITextField(position = 30, label = "Miles per year", modelAttribute = "milesPerYear", enabled = EnabledType.DYNAMIC)
    public void milesPerYear() {
        // model binding
    }

    public boolean isMilesPerYearEnabled() {
        return isInDialog;
    }

    @UITextField(position = 40, label = "Registered postal code", modelAttribute = "registeredPostalCode", enabled = EnabledType.DYNAMIC)
    public void registeredPostalCode() {
        // model binding
    }

    public boolean isRegisteredPostalCodeEnabled() {
        return isInDialog;
    }
}
