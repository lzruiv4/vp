package de.scopevisio.vp.frontend.pmo;


import de.scopevisio.vp.backend.data.model.Car;
import org.linkki.core.ui.element.annotation.UIComboBox;
import org.linkki.core.ui.element.annotation.UILabel;
import org.linkki.core.ui.element.annotation.UITextField;

public class CarRowPmo extends CarPmo {

     public CarRowPmo(Car car) {
         super(car);
     }

    @UILabel(position = 10, label = "Car ID", modelAttribute = "carId")
    public void carId() {
        // model binding
    }

    @UIComboBox(position = 50, label = "Region Type", modelAttribute = "regionType")
    public void regionType() {
        // model binding
    }

    @UITextField(position = 60, label = "Versicherungspraemie", modelAttribute = "versicherungspraemie")
    public void versicherungspraemie() {
        // model binding
    }

}
