package cz.uhk.ppro.vehicle.registry.app.layouts;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import cz.uhk.ppro.vehicle.registry.app.services.LoginService;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MainLayout
        extends VerticalLayout implements RouterLayout, BeforeEnterObserver {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private NavigatorService navigatorService;

    private H2 loggedUser;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!sessionService.isLogged()) {
            navigatorService.rerouteToLogin(event);
        }
    }

    @PostConstruct
    public void init(){
        this.setWidthFull();
        add(new H1("Registr Vozidel"));
        add(new H2("Uzivatel: "));
        loggedUser = new H2();
loggedUser.setText(sessionService.getLogin());
        add(loggedUser);
    }

    public MainLayout() {

    }
}