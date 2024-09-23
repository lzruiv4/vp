package de.scopevisio.vp.frontend.pmo;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import de.scopevisio.vp.backend.data.model.Client;
import org.linkki.core.defaults.columnbased.pmo.SimpleTablePmo;
import org.linkki.core.ui.element.annotation.UIButton;
import org.linkki.core.ui.layout.annotation.SectionHeader;
import org.linkki.core.ui.layout.annotation.UISection;
import org.linkki.util.handler.Handler;

import java.util.List;
import java.util.function.Supplier;

@UISection(caption = "Client List")
public class ClientTablePmo extends SimpleTablePmo<Client, ClientRowPmo> {

    private final Handler addClientHandler;

    public ClientTablePmo(
            Supplier<List<? extends Client>> addClientSupplier,
            Handler addClientHandler
    ) {
        super(addClientSupplier);
        this.addClientHandler = addClientHandler;
    }

    @SectionHeader
    @UIButton(
            position = 10,
            caption = "",
            showIcon = true,
            icon = VaadinIcon.PLUS,
            variants = ButtonVariant.LUMO_TERTIARY_INLINE
    )
    public void addNeuAngebot() {
        addClientHandler.apply();
    }

    @Override
    protected ClientRowPmo createRow(Client client) {
        return new ClientRowPmo(client);
    }

    @Override
    public int getPageLength() {
        // Max. 10 Clients werden angezeigt.
        return 10;
    }
}
