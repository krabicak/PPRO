package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InsuranceCompanyService {

    @Autowired
    private VehicleRegistry vehicleRegistry;

    public List<InsuranceCompany> getAllInsuranceCompanies() {
        return vehicleRegistry.getAllInsuranceCompanies();
    }
}
