package cz.uhk.ppro.vehicle.registry.app.services;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import cz.uhk.ppro.vehicle.registry.app.views.LoginView;
import cz.uhk.ppro.vehicle.registry.app.views.MainView;
import org.springframework.stereotype.Service;

@Service
public class NavigatorService {

    public void navigateToLogin(UI ui) {
        ui.addBeforeEnterListener((BeforeEnterListener) beforeEnterEvent -> {
            beforeEnterEvent.rerouteTo(LoginView.class);
        });
    }

    public void navigateToLogin(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.rerouteTo(LoginView.class);
    }

    public void navigateToMain(UI ui) {
        ui.addBeforeEnterListener((BeforeEnterListener) beforeEnterEvent -> {
            beforeEnterEvent.rerouteTo(MainView.class);
        });
    }

    public void navigateToMain(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.rerouteTo(MainView.class);
    }


}
