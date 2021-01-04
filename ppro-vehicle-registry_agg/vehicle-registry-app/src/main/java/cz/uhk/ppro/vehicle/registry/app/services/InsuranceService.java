package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {
    @Autowired
    private VehicleRegistry vehicleRegistry;

    public void addOrUpdateInsurance(Insurance insurance){
        vehicleRegistry.addOrUpdateInsurance(insurance);
    }
    public void getAllInsurance(){
       // vehicleRegistry.getAll
    }
}
