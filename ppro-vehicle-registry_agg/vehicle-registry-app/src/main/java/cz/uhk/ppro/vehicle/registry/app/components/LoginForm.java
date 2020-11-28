package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ClickableRenderer;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

import java.awt.event.MouseEvent;

/**
 * A Designer generated component for the login-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("login-form")
@JsModule("./src/views/login-form.js")
public class LoginForm extends PolymerTemplate<LoginForm.LoginFormModel> {


    @Id("password")
    private PasswordField password;
    @Id("username")
    private TextField username;
    @Id("login")
    private Button login;

    /**
     * Creates a new LoginForm.
     */
    public LoginForm() {


        login.getElement().addEventListener("click", e -> {
            login.getElement().setText("KLIK");
        });


    }

    /**
     * This model binds properties between LoginForm and login-form
     */
    public interface LoginFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
