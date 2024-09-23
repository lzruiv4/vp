package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Client;
import lombok.AllArgsConstructor;
import org.linkki.core.pmo.ModelObject;
import org.linkki.core.ui.element.annotation.UITextField;
import org.linkki.core.ui.layout.annotation.UIVerticalLayout;

@UIVerticalLayout
@AllArgsConstructor
public class ClientPmo {

    private final Client client;

    @ModelObject
    public Client getClient() {
        return client;
    }

    @UITextField(position = 20, label = "Firstname", modelAttribute = "firstname")
    public void firstname(String firstname) {
        // model binding
    }

    @UITextField(position = 30, label = "Lastname", modelAttribute = "lastname")
    public void lastname(String lastname) {
        // model binding
    }
    @UITextField(position = 40, label = "Street", modelAttribute = "street")
    public void street(String street) {
        // model binding
    }

    @UITextField(position = 50, label = "House Number", modelAttribute = "houseNumber")
    public void houseNumber(String houseNumber) {
        // model binding
    }

    @UITextField(position = 60, label = "Post Code", modelAttribute = "postCode")
    public void postCode(String postCode) {
        // model binding
    }
}
