package cz.uhk.ppro.vehicle.registry.app.services;

import com.vaadin.flow.server.VaadinSession;
import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private VehicleRegistry vehicleRegistry;

    public User login(String userName, String password) throws FaultLoginException {
        User user = vehicleRegistry.loginUser(userName, DigestUtils.sha256Hex(password));
        return user;
    }

    public void logout(){
        VaadinSession.getCurrent().setAttribute("username", null);
    }

}
