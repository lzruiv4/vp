package de.scopevisio.vp.ui;

import org.linkki.framework.ui.application.ApplicationConfig;
import org.linkki.framework.ui.application.menu.ApplicationMenuItemDefinition;
import org.linkki.util.Sequence;

public class VPConfig implements ApplicationConfig {

    @Override
    public VPInfo getApplicationInfo() {
        return new VPInfo();
    }

    @Override
    public Sequence<ApplicationMenuItemDefinition> getMenuItemDefinitions() {
        return Sequence.empty();
    }

}