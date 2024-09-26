package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Car;
import org.linkki.framework.ui.dialogs.OkCancelDialog;
import org.linkki.framework.ui.dialogs.PmoBasedDialogFactory;
import org.linkki.util.handler.Handler;

/**
 * Dialog, opens a dialog when adding or updating Car.
 * */
public class CarDialog {

    public CarDialog() {

    }

    public static void addOrUpdateCar(String title, Handler addOrUpdateCarHandler, Car car) {
        PmoBasedDialogFactory newCarDialog = new PmoBasedDialogFactory();
        OkCancelDialog dialog = newCarDialog.newOkCancelDialog(title, addOrUpdateCarHandler, new CarPmo(car, true));

        dialog.setWidth("40em");
        dialog.open();
    }

}
