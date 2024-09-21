package de.scopevisio.vp.frontend.view;

import de.scopevisio.vp.backend.data.model.RegionalFromCSV;
import de.scopevisio.vp.backend.service.RegionalService;
import de.scopevisio.vp.frontend.pmo.RegionalLookUpPmo;
import de.scopevisio.vp.frontend.pmo.RegionalRowPmo;
import de.scopevisio.vp.frontend.pmo.RegionalTablePmo;
import org.linkki.core.binding.dispatcher.behavior.PropertyBehavior;
import org.linkki.core.binding.manager.BindingManager;
import org.linkki.core.binding.manager.DefaultBindingManager;
import org.linkki.core.binding.validation.ValidationService;
import org.linkki.core.ui.creation.VaadinUiCreator;
import org.linkki.core.vaadin.component.page.AbstractPage;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class RegionalPage extends AbstractPage {

    @Serial
    private static final long serialVersionUID = 1L;

    private final BindingManager bindingManager;
    private final RegionalLookUpPmo regionalLookUpPmo;

    private final RegionalService regionalService;

    private final List<RegionalFromCSV> regionals;

    public RegionalPage() {
        this.bindingManager = new DefaultBindingManager(ValidationService.NOP_VALIDATION_SERVICE,
                () -> List.of(PropertyBehavior.readOnly((o, p) -> o instanceof RegionalRowPmo)));

        this.regionalLookUpPmo = new RegionalLookUpPmo(this::search);

        this.regionals = new ArrayList<>();
        this.regionalService = new RegionalService();

        createContent();
    }

    private void search() {
        regionals.clear();
        regionals.addAll(regionalService.readRegionalFromCsv("src/main/resources/static/postcodes.csv"));
    }

    @Override
    protected BindingManager getBindingManager() {
        return bindingManager;
    }

    @Override
    public void createContent() {
        add(VaadinUiCreator.createComponent(regionalLookUpPmo, getBindingContext()));
        add(VaadinUiCreator
                .createComponent(new RegionalTablePmo(
                                () -> regionals,
                        // TODO
                        null
                        ), getBindingContext()
                )
        );
    }
}
