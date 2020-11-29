package cz.uhk.ppro.vehicle.registry.app.services;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import cz.uhk.ppro.vehicle.registry.app.views.LoginView;
import cz.uhk.ppro.vehicle.registry.app.views.MainView;
import org.springframework.stereotype.Service;

@Service
public class NavigatorService {

    public void navigateToLogin() {
        UI.getCurrent().navigate(LoginView.class);
    }

    public void rerouteToLogin(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.rerouteTo(LoginView.class);
    }

    public void navigateToMain() {
        UI.getCurrent().navigate(MainView.class);
    }

    public void rerouteToMain(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.rerouteTo(MainView.class);
    }


}
