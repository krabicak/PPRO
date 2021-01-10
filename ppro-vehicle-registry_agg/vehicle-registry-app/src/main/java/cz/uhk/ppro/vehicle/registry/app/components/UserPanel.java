package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * A Designer generated component for the user-panel template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("user-panel")
@JsModule("./src/views/user-panel.js")
public class UserPanel extends PolymerTemplate<UserPanel.UserPanelModel> {

    @Id("userLabel")
    private Paragraph userLabel;
    @Id("logoutButton")
    private Button logoutButton;

    private LoginService loginService;
    private NavigatorService navigatorService;
    private SessionService sessionService;
    private UserService userService;

    /**
     * Creates a new UserPanel.
     */
    public UserPanel(LoginService loginService, NavigatorService navigatorService, SessionService sessionService, UserService userService) {
        this.loginService=loginService;
        this.navigatorService=navigatorService;
        this.sessionService=sessionService;
        this.userService=userService;
        logoutButton.addClickListener(getOnClickLogoutListener());
        String login = sessionService.getLogin();
        User user = userService.getUserByLogin(login);
        Person person = user.getPerson();
        userLabel.setText(person.getFirstName() + " " + person.getLastName() + " (" + user.getRole().toString() + " - " + login + ")");
    }

    private ComponentEventListener<ClickEvent<Button>> getOnClickLogoutListener() {
        return e -> {
            loginService.logout();
            navigatorService.navigateToLogin();
        };
    }

    /**
     * This model binds properties between UserPanel and user-panel
     */
    public interface UserPanelModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
