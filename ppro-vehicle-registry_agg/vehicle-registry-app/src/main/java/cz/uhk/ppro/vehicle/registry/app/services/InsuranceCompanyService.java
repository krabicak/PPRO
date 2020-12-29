package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InsuranceCompanyService {

    @Autowired
    private VehicleRegistry vehicleRegistry;

    public List<InsuranceCompany> getAllInsuranceCompanies() {
        return vehicleRegistry.getAllInsuranceCompanies();
    }
}
