package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private VehicleRegistry vehicleRegistry;

    public User getUserByLogin(String login){
        return vehicleRegistry.getUserByLogin(login);
    }
}
