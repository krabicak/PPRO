package cz.uhk.ppro.vehicle.registry.app.services;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import cz.uhk.ppro.vehicle.registry.app.views.LoginView;
import cz.uhk.ppro.vehicle.registry.app.views.MainView;
import org.springframework.stereotype.Service;

@Service
public class NavigatorService {

    public void navigateToLogin() {
        UI.getCurrent().navigate(LoginView.class);
    }

    public void reroutoToLogin(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.rerouteTo(LoginView.class);
    }

    public void reroutoToMain() {
        UI.getCurrent().navigate(MainView.class);
    }

    public void reroutoToMain(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.rerouteTo(MainView.class);
    }


}
