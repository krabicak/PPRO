package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import cz.uhk.ppro.vehicle.registry.app.layouts.MainLayout;
import cz.uhk.ppro.vehicle.registry.app.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "main", layout = MainLayout.class)
@RouteAlias(value = "main-page", layout = MainLayout.class)
public class MainView extends Div {

    @Autowired
    private SessionService sessionService;

    @PostConstruct
    public void init() {
        add(new H4("Vítej " + sessionService.getLogin() + "!"));
    }
}