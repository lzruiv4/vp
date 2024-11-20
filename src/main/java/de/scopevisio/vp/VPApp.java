package de.scopevisio.vp;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serial;

@Theme("linkki")
@SpringBootApplication
public class VPApp implements AppShellConfigurator {

    @Serial
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        SpringApplication.run(VPApp.class, args);
    }

}
