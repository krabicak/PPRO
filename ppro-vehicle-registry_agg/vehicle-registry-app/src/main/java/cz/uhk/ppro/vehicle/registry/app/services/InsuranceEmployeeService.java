package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceEmployee;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.InsuranceCompanyException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceEmployeeService {
    @Autowired
    private VehicleRegistry vehicleRegistry;

    public void addOrUpdateInsuranceEmployee(InsuranceEmployee insuranceEmployee) throws PersonException, InsuranceCompanyException, UserException {
        vehicleRegistry.addOrUpdateInsuranceEmployee(insuranceEmployee);
    }

    public List<InsuranceEmployee> getAllInsuranceEmployee(){
        return vehicleRegistry.getAllInsuranceEmployees();
    }

    public void removeInsuranceEmployee(User user) {
        InsuranceEmployee employee = vehicleRegistry.getInsuranceEmployee(user);
        vehicleRegistry.removeInsuranceEmployee(employee);
    }
}
