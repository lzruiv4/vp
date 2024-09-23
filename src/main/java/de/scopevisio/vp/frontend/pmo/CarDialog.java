package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Car;
import org.linkki.framework.ui.dialogs.OkCancelDialog;
import org.linkki.framework.ui.dialogs.PmoBasedDialogFactory;
import org.linkki.util.handler.Handler;

public class CarDialog {

    public CarDialog() {

    }

    public static void addNewCar(String title, Handler addCarHandler, Car car) {
        PmoBasedDialogFactory newCarDialog = new PmoBasedDialogFactory();
        OkCancelDialog dialog = newCarDialog.newOkCancelDialog(title, addCarHandler, new CarPmo(car));

        dialog.setWidth("40em");
        dialog.open();
    }
}
