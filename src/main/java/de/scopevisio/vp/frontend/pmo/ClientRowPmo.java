package de.scopevisio.vp.frontend.pmo;

import com.vaadin.flow.component.icon.VaadinIcon;
import de.scopevisio.vp.backend.data.model.Client;
import org.linkki.core.defaults.ui.aspects.types.EnabledType;
import org.linkki.core.ui.element.annotation.UIButton;
import org.linkki.core.ui.element.annotation.UILabel;
import org.linkki.core.ui.element.annotation.UILink;
import org.linkki.core.ui.element.annotation.UITextField;

import java.util.function.Consumer;

public class ClientRowPmo extends ClientPmo {

    private final Consumer<Client> updateClient;

    public ClientRowPmo(Client client, Consumer<Client> updateClient) {
        super(client, false);
        this.updateClient = updateClient;
    }

    @UILabel(position = 10, label = "Client ID", modelAttribute = "clientId")
    public void clientId() {
        // model binding
    }

    @UITextField(position = 70, label = "City", modelAttribute = "city", enabled = EnabledType.DISABLED)
    public void city(String city) {
        // model binding
    }

    @UILink(position = 80, caption = "Cars")
    public String getCars() {
        return "detail/" + getClient().getClientId();
    }

    @UIButton(position = 90, caption = "", showIcon = true, icon = VaadinIcon.PENCIL)
    public void updateClient() {
        updateClient.accept(getClient());
    }

}
