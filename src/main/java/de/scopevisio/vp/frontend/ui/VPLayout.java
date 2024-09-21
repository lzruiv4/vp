package de.scopevisio.vp.frontend.ui;

import org.linkki.framework.ui.application.ApplicationLayout;

import java.io.Serial;

public class VPLayout extends ApplicationLayout {

    @Serial
    private static final long serialVersionUID = 1L;

    public VPLayout() {
        super(new VPConfig());
    }
}

