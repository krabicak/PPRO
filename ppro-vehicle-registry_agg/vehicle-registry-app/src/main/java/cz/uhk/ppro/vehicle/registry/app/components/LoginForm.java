package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.services.DialogService;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Designer generated component for the login-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("login-form")
@JsModule("./src/views/login-form.js")
public class LoginForm extends PolymerTemplate<LoginForm.LoginFormModel> {

    @Autowired
    private LoginService loginService;
    @Autowired
    private DialogService dialogService;

    @Id("password")
    private PasswordField password;
    @Id("username")
    private TextField username;
    @Id("login")
    private Button login;
    @Id("vaadinVerticalLayout")
    private Element vaadinVerticalLayout;

    /**
     * Creates a new LoginForm.
     */
    public LoginForm() {
        login.addClickListener(getOnClickLoginListener());
        login.addClickShortcut(Key.ENTER);
    }

    public void addOnLoginListener(ComponentEventListener listener) {
        login.addClickListener(listener);
    }

    private ComponentEventListener<ClickEvent<Button>> getOnClickLoginListener() {
        return e -> {
            try {
                loginService.login(username.getValue(), password.getValue());
            } catch (FaultLoginException faultLoginException) {
                dialogService.showErrorDialog(faultLoginException);
            }
        };
    }

    /**
     * This model binds properties between LoginForm and login-form
     */
    public interface LoginFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
