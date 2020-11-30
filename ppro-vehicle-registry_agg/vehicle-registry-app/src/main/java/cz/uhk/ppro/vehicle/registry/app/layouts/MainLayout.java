package cz.uhk.ppro.vehicle.registry.app.layouts;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import cz.uhk.ppro.vehicle.registry.app.services.UserService;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@CssImport("./styles/moje.css")
public class MainLayout
        extends VerticalLayout implements RouterLayout, BeforeEnterObserver {
    // Import a style sheet into the global scope

    @Autowired
    private SessionService sessionService;

    @Autowired
    private NavigatorService navigatorService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    private Paragraph userName;
    private Button buttonLogout;
    private Div divUserComplete;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (sessionService.isLogged()) {
            String login = sessionService.getLogin();
            Person person = userService.getUserByLogin(login).getPerson();
            userName.setText(person.getFirstName() + " " + person.getLastName() + " (" + login + ")");
            divUserComplete.setVisible(true);
        }
        if (!sessionService.isLogged()) {
            navigatorService.rerouteToLogin(event);
            divUserComplete.setVisible(false);
        }
    }

    @PostConstruct
    public void init() {
        this.setWidthFull();
        add(new H1("Registr Vozidel"));

        Div divUser = new Div();
        divUser.setClassName("divUser");

        Paragraph userLabel = new Paragraph("Uživatel: ");
        divUser.add(userLabel);

        userName = new Paragraph();
        userName.setText(sessionService.getLogin());
        userName.setId("userName");
        divUser.add(userName);

        buttonLogout = new Button();
        buttonLogout.setText("Odhlásit se");
        buttonLogout.addClickListener(getOnClickLogoutListener());



        divUserComplete = new Div();
        divUserComplete.setClassName("divUserComplete");
        divUserComplete.add(divUser);
        divUserComplete.add(buttonLogout);
        add(divUserComplete);

    }

    private ComponentEventListener<ClickEvent<Button>> getOnClickLogoutListener() {
        return e -> {
            loginService.logout();
            navigatorService.navigateToLogin();
        };
    }

}