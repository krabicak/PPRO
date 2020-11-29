package cz.uhk.ppro.vehicle.registry.app.layouts;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import cz.uhk.ppro.vehicle.registry.app.services.NavigatorService;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

public class MainLayout
        extends VerticalLayout implements RouterLayout, BeforeEnterObserver {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private NavigatorService navigatorService;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!sessionService.isLogged()) navigatorService.rerouteToLogin(event);
    }

    public MainLayout() {
        add(new H1("Registr Vozidel"));
    }
}