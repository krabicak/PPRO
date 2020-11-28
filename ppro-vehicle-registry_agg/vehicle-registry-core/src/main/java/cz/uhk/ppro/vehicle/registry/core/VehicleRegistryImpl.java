package cz.uhk.ppro.vehicle.registry.core;


import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import cz.uhk.ppro.vehicle.registry.common.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleRegistryImpl implements VehicleRegistry {

    @Autowired
    private UserRepo userRepo;

    public User loginUser(String login,String password) throws FaultLoginException {
        User user = userRepo.getUserByLoginAndPassword(login,password);
        if (user==null) throw new FaultLoginException("Neplatný login nebo heslo!");
        else return user;
    }
}
