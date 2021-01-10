package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.entities.Vehicle;
import cz.uhk.ppro.vehicle.registry.common.exceptions.DocumentException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.SpzException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.VinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRegistry vehicleRegistry;

    public List<Vehicle> getAllVehicles() {
        return vehicleRegistry.getAllVehicles();
    }

    public void addOrUpdateVehicle(Vehicle vehicle)
            throws SpzException, PersonException, VinException, DocumentException {
        vehicleRegistry.addOrUpdateVehicle(vehicle);
    }

    public List<Vehicle> findVehiclesByKeyWord(String keyword) {
        return vehicleRegistry.findVehiclesByKeyWord(keyword);
    }

    public List<Vehicle> getUnsiredVehicles() {
        return vehicleRegistry.getUninsuredVehicles();
    }
}
