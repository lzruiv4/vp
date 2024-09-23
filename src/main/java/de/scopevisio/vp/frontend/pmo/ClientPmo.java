package de.scopevisio.vp.frontend.pmo;

import de.scopevisio.vp.backend.data.model.Client;
import lombok.AllArgsConstructor;
import org.linkki.core.ui.element.annotation.UILabel;
import org.linkki.core.ui.element.annotation.UITextField;

@AllArgsConstructor
public class ClientPmo {

    private final Client client;


    @UILabel(position = 10, label = "Client ID")
    public Long getClientId() {
        return client.getClientId();
    }

    public void setClientId(Long clientId) {
        client.setClientId(clientId);
    }

    @UITextField(position = 20, label = "Firstname")
    public String getFirstname() {
        return client.getFirstname();
    }

    public void setFirstname(String firstname) {
        client.setFirstname(firstname);
    }

    @UITextField(position = 30, label = "Lastname")
    public String getLastname() {
        return client.getLastname();
    }

    public void setLastname(String lastname) {
        client.setLastname(lastname);
    }

    @UITextField(position = 40, label = "Street")
    public String getStreet() {
        return client.getStreet();
    }

    public void setStreet(String street) {
        client.setStreet(street);
    }

    @UITextField(position = 50, label = "House Number")
    public String getHouseNumber() {
        return client.getHouseNumber();
    }

    public void setHouseNumber(String houseNumber) {
        client.setHouseNumber(houseNumber);
    }

    @UITextField(position = 60, label = "Post Code")
    public String getPostCode() {
        return client.getPostCode();
    }

    public void setPostCode(String postCode) {
        client.setPostCode(postCode);
    }

    @UITextField(position = 70, label = "City")
    public String getCity() {
        return client.getCity();
    }

    public void setCity(String city) {
        client.setCity(city);
    }

//    @UILabel(position = 80, label = "Client ID")
//    public void clientId() {
//        // model binding
//    }
}
