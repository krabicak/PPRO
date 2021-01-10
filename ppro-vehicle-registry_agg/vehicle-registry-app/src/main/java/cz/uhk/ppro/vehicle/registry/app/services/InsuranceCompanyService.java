package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceEmployee;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.InsuranceCompanyException;
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

    public List<InsuranceCompany> findInsuranceCompaniesByKeyWord(String keyword) {
        return vehicleRegistry.findInsuranceCompaniesByKeyWord(keyword);
    }

    public void addOrUpdateInsuranceCompany(InsuranceCompany insuranceCompany) throws InsuranceCompanyException {
        vehicleRegistry.addOrUpdateInsuranceCompany(insuranceCompany);
    }

    public void removeInsuranceCompany(InsuranceCompany insuranceCompany) {
        vehicleRegistry.removeInsuranceCompany(insuranceCompany);
    }

    public InsuranceCompany getInsuranceCompany(User user) {
        InsuranceEmployee employee = vehicleRegistry.getInsuranceEmployee(user);
        if (employee == null) return null;
        return employee.getInsuranceCompany();
    }

}
