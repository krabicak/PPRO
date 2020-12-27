package cz.uhk.ppro.vehicle.registry.common;

import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.entities.Vehicle;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;

import java.util.List;

public interface VehicleRegistry {

    User loginUser(String login, String password) throws FaultLoginException;

    User getUserByLogin(String login);

    List<User> getAllUsers();

    void addOrUpdateUser(User user) throws PersonException;

    void removeUser(User user) throws PersonException;

    List<Vehicle> getAllVehicles();

    void addOrUpdateVehicle(Vehicle vehicle);

}
