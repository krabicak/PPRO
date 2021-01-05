package cz.uhk.ppro.vehicle.registry.app.services;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.Insurance;
import cz.uhk.ppro.vehicle.registry.common.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {
    @Autowired
    private VehicleRegistry vehicleRegistry;

    public void addOrUpdateInsurance(Insurance insurance) throws PersonException, DocumentException, InsuranceCompanyException, SpzException, VinException, InsuranceException, UserException {
        vehicleRegistry.addOrUpdateInsurance(insurance);
    }
    public List<Insurance> getAllInsurance(){
       return vehicleRegistry.getAllInsurancies();
    }
    public void deleteInsurance(Insurance insurance){
        vehicleRegistry.removeInsurance(insurance);
    }
}
