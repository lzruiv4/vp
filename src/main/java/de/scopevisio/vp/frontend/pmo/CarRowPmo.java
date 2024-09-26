package de.scopevisio.vp.frontend.pmo;

import com.vaadin.flow.component.icon.VaadinIcon;
import de.scopevisio.vp.backend.data.model.Car;
import org.linkki.core.defaults.ui.aspects.types.EnabledType;
import org.linkki.core.ui.element.annotation.UIButton;
import org.linkki.core.ui.element.annotation.UIComboBox;
import org.linkki.core.ui.element.annotation.UILabel;
import org.linkki.core.ui.element.annotation.UITextField;

import java.util.function.Consumer;

public class CarRowPmo extends CarPmo {

    private final Consumer<Car> updateCar;

    public CarRowPmo(Car car, Consumer<Car> updateCar) {
        super(car, false);
        this.updateCar = updateCar;
    }

    @UILabel(position = 10, label = "Car ID", modelAttribute = "carId")
    public void carId() {
        // model binding
    }

    @UIComboBox(position = 50, label = "Region Type", modelAttribute = "regionType", enabled = EnabledType.DISABLED)
    public void regionType() {
        // model binding
    }

    @UITextField(position = 60, label = "Versicherungspraemie", modelAttribute = "versicherungspraemie", enabled = EnabledType.DISABLED)
    public void versicherungspraemie() {
        // model binding
    }

    @UIButton(position = 90, caption = "", showIcon = true, icon = VaadinIcon.PENCIL)
    public void updateCar() {
        updateCar.accept(getCar());
    }

}
