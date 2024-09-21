package de.scopevisio.vp.ui;

import java.time.Year;

import org.linkki.framework.ui.application.ApplicationInfo;

public class VPInfo implements ApplicationInfo {

    public static final String APPLICATION_NAME = "de/scopevisio/vp";

    @Override
    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    @Override
    public String getApplicationVersion() {
        return "1.0";
    }

    @Override
    public String getApplicationDescription() {
        return "de/scopevisio/vp";
    }

    @Override
    public String getCopyright() {
        return "© Faktor Zehn " + Year.now();
    }
}