package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceEmployee;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceEmployeeService {
    @Autowired
    private VehicleRegistry vehicleRegistry;

    public void addOrUpdateInsuranceEmployee(InsuranceEmployee insuranceEmployee) throws PersonException {
       vehicleRegistry.addOrUpdateInsuranceEmployee(insuranceEmployee);
    }
}
