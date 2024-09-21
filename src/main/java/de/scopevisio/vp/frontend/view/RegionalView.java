package de.scopevisio.vp.frontend.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.scopevisio.vp.frontend.ui.VPLayout;
import org.linkki.framework.ui.component.Headline;

@PageTitle("RegionsRecord")
@Route(value = "re", layout = VPLayout.class)
public class RegionalView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    public RegionalView() {
        add(new Headline("RegionsRecord"));
        setSizeFull();
        RegionalPage regionalPage = new RegionalPage();
        add(regionalPage);
    }
}
