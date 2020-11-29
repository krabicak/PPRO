package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.AllowClientUpdates;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.components.LoginForm;
import cz.uhk.ppro.vehicle.registry.app.layouts.MainLayout;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the login-page template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route(value = "login", layout = MainLayout.class)
@RouteAlias(value = "login-page", layout = MainLayout.class)
public class LoginView extends LoginForm {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private NavigatorService navigatorService;

    /**
     * Creates a new LoginPage.
     */
    @PostConstruct
    public void init() {
        addOnLoginListener(componentEvent -> {
            getUI().ifPresent(ui -> {
                if (sessionService.isLogged()) {
                    navigatorService.navigateToMain(ui);
                }
            });
        });
    }

    /**
     * This model binds properties between LoginPage and login-page
     */
    public interface LoginPageModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
