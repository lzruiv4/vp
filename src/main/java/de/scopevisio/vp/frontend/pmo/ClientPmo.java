package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Client;
import lombok.AllArgsConstructor;
import org.linkki.core.defaults.ui.aspects.types.EnabledType;
import org.linkki.core.pmo.ModelObject;
import org.linkki.core.ui.element.annotation.UITextField;
import org.linkki.core.ui.layout.annotation.UIVerticalLayout;

@UIVerticalLayout
@AllArgsConstructor
public class ClientPmo {

    private final Client client;
    private boolean isInDialog = false;

    @ModelObject
    public Client getClient() {
        return client;
    }

    @UITextField(position = 20, label = "Firstname", modelAttribute = "firstname", enabled = EnabledType.DYNAMIC)
    public void firstname(String firstname) {
        // model binding
    }

    public boolean isFirstnameEnabled() {
        return isInDialog;
    }

    @UITextField(position = 30, label = "Lastname", modelAttribute = "lastname", enabled = EnabledType.DYNAMIC)
    public void lastname(String lastname) {
        // model binding
    }

    public boolean isLastnameEnabled() {
        return isInDialog;
    }

    @UITextField(position = 40, label = "Street", modelAttribute = "street", enabled = EnabledType.DYNAMIC)
    public void street(String street) {
        // model binding
    }

    public boolean isStreetEnabled() {
        return isInDialog;
    }

    @UITextField(position = 50, label = "House Number", modelAttribute = "houseNumber", enabled = EnabledType.DYNAMIC)
    public void houseNumber(String houseNumber) {
        // model binding
    }

    public boolean isHouseNumberEnabled() {
        return isInDialog;
    }

    @UITextField(position = 60, label = "Post Code", modelAttribute = "postCode", enabled = EnabledType.DYNAMIC)
    public void postCode(String postCode) {
        // model binding
    }

    public boolean isPostCodeEnabled() {
        return isInDialog;
    }
}
