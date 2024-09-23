package de.scopevisio.vp.frontend.pmo;


import de.scopevisio.vp.backend.data.model.Car;
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

    @UITextField(position = 50, label = "Versicherungspraemie", modelAttribute = "versicherungspraemie")
    public void versicherungspraemie() {
        // model binding
    }

}
