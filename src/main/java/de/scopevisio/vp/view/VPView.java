package de.scopevisio.vp.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.scopevisio.vp.ui.VPLayout;
import org.linkki.framework.ui.component.Headline;

@PageTitle("de/scopevisio/vp")
@Route(value = "", layout = VPLayout.class)
public class VPView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    public VPView() {
        add(new Headline("de/scopevisio/vp"));
        setSizeFull();
        VPPage page = new VPPage();
        page.init();
        add(page);
    }
}