package cz.uhk.ppro.vehicle.registry.app.views;

import com.vaadin.flow.router.Route;
import cz.uhk.ppro.vehicle.registry.app.components.UsersForm;
import cz.uhk.ppro.vehicle.registry.app.layouts.InternalLayout;
import cz.uhk.ppro.vehicle.registry.app.layouts.MainLayout;

import javax.annotation.PostConstruct;

@Route(value = "users-view", layout = InternalLayout.class)
public class UsersView extends UsersForm {
    @PostConstruct
    public void init() {
    }
}
