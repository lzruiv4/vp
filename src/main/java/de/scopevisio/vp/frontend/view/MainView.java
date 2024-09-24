package de.scopevisio.vp.frontend.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends Div {

    public MainView() {

        UI.getCurrent().getPage().setLocation("clients");

    }
}
