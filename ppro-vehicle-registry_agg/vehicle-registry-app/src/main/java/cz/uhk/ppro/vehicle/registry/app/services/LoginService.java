package cz.uhk.ppro.vehicle.registry.app.services;

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

    @Autowired
    private SessionService sessionService;

    public User login(String userName, String password) throws FaultLoginException {
        User user = vehicleRegistry.loginUser(userName, DigestUtils.sha256Hex(password));
        if (user != null) sessionService.saveLogin(userName);
        return user;
    }

    public void logout() {
        sessionService.clearLogin();
    }


    public boolean isLoggedUserAdmin() {
        User user = vehicleRegistry.getUserByLogin(sessionService.getLogin());
        return user.getRole() == User.UserRole.ADMIN;
    }

    public boolean isLoggedUserClerk() {
        User user = vehicleRegistry.getUserByLogin(sessionService.getLogin());
        return user.getRole() == User.UserRole.CLERK;
    }

    public boolean isLoggedUserInsurer() {
        User user = vehicleRegistry.getUserByLogin(sessionService.getLogin());
        return user.getRole() == User.UserRole.INSURER;
    }

}
