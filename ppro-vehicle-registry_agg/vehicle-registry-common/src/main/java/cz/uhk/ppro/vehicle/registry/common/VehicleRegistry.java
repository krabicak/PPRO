package cz.uhk.ppro.vehicle.registry.common;

import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;

public interface VehicleRegistry {
    User loginUser(String login, String password) throws FaultLoginException;
}
