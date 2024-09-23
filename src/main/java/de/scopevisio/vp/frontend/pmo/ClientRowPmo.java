package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Client;
import org.linkki.core.ui.element.annotation.UILabel;
import org.linkki.core.ui.element.annotation.UILink;
import org.linkki.core.ui.element.annotation.UITextField;

public class ClientRowPmo extends ClientPmo {

    public ClientRowPmo(Client client) {
        super(client);
    }

    @UILabel(position = 10, label = "Client ID", modelAttribute = "clientId")
    public void clientId() {
        // model binding
    }

    @UITextField(position = 70, label = "City", modelAttribute = "city")
    public void city(String city) {
        // model binding
    }

    @UILink(position = 100, caption = "Cars")
    public String getCars() {
        return "detail/" + getClient().getClientId();
        //return "detail/" + 1;
    }

}
