package de.scopevisio.vp.frontend.pmo;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import de.scopevisio.vp.backend.data.model.Car;
import org.linkki.core.defaults.columnbased.pmo.SimpleTablePmo;
import org.linkki.core.ui.element.annotation.UIButton;
import org.linkki.core.ui.element.annotation.UILink;
import org.linkki.core.ui.layout.annotation.SectionHeader;
import org.linkki.core.ui.layout.annotation.UISection;
import org.linkki.util.handler.Handler;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@UISection(caption = "Client List")
public class CarTablePmo extends SimpleTablePmo<Car, CarRowPmo> {

    private final Handler addCarHandler;
    private final Consumer<Car> updateCar;

    public CarTablePmo(
            Supplier<List<? extends Car>> addCarSupplier,
            Handler addCarHandler,
            Consumer<Car> updateCar
    ) {
        super(addCarSupplier.get());
        this.addCarHandler = addCarHandler;
        this.updateCar = updateCar;
    }

    @SectionHeader
    @UIButton(
            position = 10,
            caption = "",
            showIcon = true,
            icon = VaadinIcon.PLUS,
            variants = ButtonVariant.LUMO_TERTIARY_INLINE
    )
    public void addNewCar() {
        addCarHandler.apply();
    }

    @Override
    protected CarRowPmo createRow(Car car) {
        return new CarRowPmo(car, updateCar);
    }

    @SectionHeader
    @UILink(position = 30, caption = "Back")
    public String getBack() {
        return "clients";
    }
}
