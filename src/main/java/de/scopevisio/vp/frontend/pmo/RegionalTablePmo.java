package de.scopevisio.vp.frontend.pmo;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import de.scopevisio.vp.backend.data.model.RegionalFromCSV;
import org.linkki.core.defaults.columnbased.pmo.SimpleTablePmo;
import org.linkki.core.ui.aspects.annotation.BindPlaceholder;
import org.linkki.core.ui.element.annotation.UIButton;
import org.linkki.core.ui.layout.annotation.SectionHeader;
import org.linkki.core.ui.layout.annotation.UISection;
import org.linkki.util.handler.Handler;

import java.util.List;
import java.util.function.Supplier;


@BindPlaceholder("No result")
@UISection(caption = "Regionals")
public class RegionalTablePmo extends SimpleTablePmo<RegionalFromCSV, RegionalRowPmo> {

    private final Handler addRegionalHandler;

    public RegionalTablePmo(
            Supplier<List<? extends  RegionalFromCSV>> regionalSupplier,
            Handler addRegionalHandler
    ) {
        super(regionalSupplier.get());
        this.addRegionalHandler = addRegionalHandler;
    }

    @SectionHeader
    @UIButton(
            position = 10,
            caption = "",
            showIcon = true,
            icon = VaadinIcon.PLUS,
            variants = ButtonVariant.LUMO_TERTIARY_INLINE
    )
    public void addRegional() {
        addRegionalHandler.apply();
    }

    @Override
    protected RegionalRowPmo createRow(RegionalFromCSV regionalFromCSV) {
        return new RegionalRowPmo(
                regionalFromCSV.getBundesland(),
                regionalFromCSV.getLand(),
                regionalFromCSV.getOrt(),
                regionalFromCSV.getPostleitzahl(),
                regionalFromCSV.getBezirk()
        );
    }

    @Override
    public int getPageLength() {
        // Max. 10 Angebote werden angezeigt.
        return 10;
    }
}
