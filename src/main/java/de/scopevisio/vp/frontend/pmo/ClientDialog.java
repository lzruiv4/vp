package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Client;
import org.linkki.framework.ui.dialogs.OkCancelDialog;
import org.linkki.framework.ui.dialogs.PmoBasedDialogFactory;
import org.linkki.util.handler.Handler;

public class ClientDialog {

    private ClientDialog() {

    }

    public static void addNewClient(String title, Handler addClientHandler, Client client) {
        PmoBasedDialogFactory newClientDialog = new PmoBasedDialogFactory();
        OkCancelDialog dialog = newClientDialog.newOkCancelDialog(title, addClientHandler, new ClientPmo(client));

        dialog.setWidth("40em");
        dialog.open();
    }
}
