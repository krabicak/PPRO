package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceEmployee;
import org.springframework.beans.factory.annotation.Autowired;

public class InsuranceEmployeeService {
    @Autowired
    private VehicleRegistry vehicleRegistry;

    public void addOrUpdateInsuranceEmployee(InsuranceEmployee insuranceEmployee){
       vehicleRegistry.addOrUpdateInsuranceEmployee(insuranceEmployee);
    }
}
