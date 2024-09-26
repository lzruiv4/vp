package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Client;
import org.linkki.framework.ui.dialogs.OkCancelDialog;
import org.linkki.framework.ui.dialogs.PmoBasedDialogFactory;
import org.linkki.util.handler.Handler;

public class ClientDialog {

    private ClientDialog() {

    }

    public static void addOrUpdateClient(String title, Handler addOrUpdateClientHandler, Client client) {
        PmoBasedDialogFactory newClientDialog = new PmoBasedDialogFactory();
        ClientPmo clientPmo = new ClientPmo(client, true);
        OkCancelDialog dialog = newClientDialog.newOkCancelDialog(title, addOrUpdateClientHandler, clientPmo);

        dialog.setWidth("40em");
        dialog.open();
    }

}
