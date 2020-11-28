package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ClickableRenderer;
import com.vaadin.flow.dom.DomEvent;
import com.vaadin.flow.dom.DomEventListener;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.MouseEvent;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    @Id("password")
    private PasswordField password;
    @Id("username")
    private TextField username;
    @Id("login")
    private Button login;
    @Id("vaadinVerticalLayout")
    private Element vaadinVerticalLayout;

    private String sUsername;
    private String sPassword;
    /**
     * Creates a new LoginForm.
     */
    public LoginForm() {
        login.getElement().addEventListener("click", getOnClickLoginListener());


    }

    private DomEventListener getOnClickLoginListener() {
        return (DomEventListener) e -> {

            sUsername = username.getValue();
            sPassword = password.getValue();

            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            }
            byte[] hash = digest.digest(sPassword.getBytes(StandardCharsets.UTF_8));
            //System.out.println("hash: " + hash);

            try {
                loginService.login(sUsername, sPassword);
            } catch (FaultLoginException faultLoginException) {
                faultLoginException.printStackTrace();
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
