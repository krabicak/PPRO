package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the login-page template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("login-page")
@JsModule("./src/views/login-page.js")
@Route("")
public class LoginPage extends PolymerTemplate<LoginPage.LoginPageModel> {

    @Id("loginForm")
    private LoginForm loginForm;
    @Id("vaadinVerticalLayout")
    private Element vaadinVerticalLayout;

    /**
     * Creates a new LoginPage.
     */
    public LoginPage() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between LoginPage and login-page
     */
    public interface LoginPageModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
