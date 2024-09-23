package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Car;
import lombok.AllArgsConstructor;
import org.linkki.core.pmo.ModelObject;
import org.linkki.core.ui.element.annotation.UIComboBox;
import org.linkki.core.ui.element.annotation.UITextField;
import org.linkki.core.ui.layout.annotation.UIVerticalLayout;

@UIVerticalLayout
@AllArgsConstructor
public class CarPmo {

    private final Car car;

    @ModelObject
    public Car getCar() {
        return car;
    }

    @UIComboBox(position = 20, label = "Car Type", modelAttribute = "carType")
    public void carType() {
        // model binding
    }

    @UITextField(position = 30, label = "Miles per year", modelAttribute = "milesPerYear")
    public void milesPerYear() {
        // model binding
    }

    @UITextField(position = 60, label = "Registered postal code", modelAttribute = "registeredPostalCode")
    public void registeredPostalCode() {
        // model binding
    }
}
