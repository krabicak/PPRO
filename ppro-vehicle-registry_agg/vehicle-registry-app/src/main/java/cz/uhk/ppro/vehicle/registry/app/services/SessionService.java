package cz.uhk.ppro.vehicle.registry.app.services;

import com.vaadin.flow.server.VaadinSession;
import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private static final String ATTRIBUTE_USERNAME = "login";

    @Autowired
    private VehicleRegistry vehicleRegistry;

    public boolean isLogged() {
        VaadinSession vaadinSession = VaadinSession.getCurrent();
        String username = (String) vaadinSession.getAttribute(ATTRIBUTE_USERNAME);
        return username != null;
    }

    public String getLogin() {
        VaadinSession vaadinSession = VaadinSession.getCurrent();
        String username = (String) vaadinSession.getAttribute(ATTRIBUTE_USERNAME);
        return username;
    }

    public User getLoggedUser(){
        return vehicleRegistry.getUserByLogin(getLogin());
    }

    public void saveLogin(String login){
        VaadinSession.getCurrent().setAttribute(ATTRIBUTE_USERNAME, login);
    }

    public void clearLogin(){
        saveLogin(null);
    }
}
