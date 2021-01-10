package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private VehicleRegistry vehicleRegistry;

    public User getUserByLogin(String login) {
        return vehicleRegistry.getUserByLogin(login);
    }

    public void addOrUpdateUser(User user) throws PersonException {
        vehicleRegistry.addOrUpdateUser(user);
    }

    public void removeUser(User user) throws PersonException {
        vehicleRegistry.removeUser(user);
    }

    public List<User> getAllUsers() {
        return vehicleRegistry.getAllUsers();
    }

    public List<User> findUsersByKeyWord(String keyword) {
        return vehicleRegistry.findUsersByKeyWord(keyword);
    }
}
