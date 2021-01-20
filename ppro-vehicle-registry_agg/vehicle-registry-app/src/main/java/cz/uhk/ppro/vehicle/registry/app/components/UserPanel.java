package cz.uhk.ppro.vehicle.registry.app.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import cz.uhk.ppro.vehicle.registry.common.entities.User;

@Tag("user-panel")
@JsModule("./src/views/user-panel.js")
public class UserPanel extends PolymerTemplate<UserPanel.UserPanelModel> {

    @Id("userLabel")
    private Paragraph userLabel;
    @Id("logoutButton")
    private Button logoutButton;
    private final LoginService loginService;
    private final NavigatorService navigatorService;
    private final SessionService sessionService;
    private final UserService userService;

    public UserPanel(LoginService loginService, NavigatorService navigatorService, SessionService sessionService, UserService userService) {
        this.loginService = loginService;
        this.navigatorService = navigatorService;
        this.sessionService = sessionService;
        this.userService = userService;
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

    public interface UserPanelModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
