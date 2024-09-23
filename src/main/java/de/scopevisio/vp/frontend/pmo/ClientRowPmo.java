package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Client;
import org.linkki.core.ui.element.annotation.UILabel;
import org.linkki.core.ui.element.annotation.UILink;

public class ClientRowPmo extends ClientPmo {

    public ClientRowPmo(Client client) {
        super(client);
    }

    @UILabel(position = 10, label = "Client ID", modelAttribute = "clientId")
    public void clientId() {
        // model binding
    }

    @UILink(position = 100, caption = "Cars")
    public String getCars() {
        return "detail/" + getClient().getClientId();
        //return "detail/" + 1;
    }

}
