package de.scopevisio.vp.frontend.pmo;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.linkki.core.defaults.ui.aspects.annotations.BindTooltip;
import org.linkki.core.ui.element.annotation.UIButton;
import org.linkki.core.ui.element.annotation.UITextField;
import org.linkki.core.ui.layout.annotation.SectionLayout;
import org.linkki.core.ui.layout.annotation.UISection;
import org.linkki.core.vaadin.component.KeyCode;
import org.linkki.util.handler.Handler;

@UISection(caption = "Find region", layout = SectionLayout.HORIZONTAL)
public class RegionalLookUpPmo {

    private final Handler lookupHandler;

    private String bundesland;
    private String land;
    private String ort;
    private String postleitzahl;
    private String bezirk;

    public RegionalLookUpPmo(Handler lookupHandler) {
        this.lookupHandler = lookupHandler;
    }

    /**
     * Suche nach Angebotsnummer
     *
     * @return angebotsnummer
     * */
    @UITextField(position = 10, label = "Postleitzahl", width = "25em")
    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    @BindTooltip("Suche starten")
    @UIButton(
            position = 70,
            caption = "Suchen",
            variants = ButtonVariant.LUMO_PRIMARY,
            shortcutKeyCode = KeyCode.ENTER,
            showIcon = true,
            icon = VaadinIcon.ARROW_CIRCLE_RIGHT
    )
    public void findButton() { // Methodename wird angezeigt in Button, falls caption nicht angegeben ist
        lookupHandler.apply();
    }
}
