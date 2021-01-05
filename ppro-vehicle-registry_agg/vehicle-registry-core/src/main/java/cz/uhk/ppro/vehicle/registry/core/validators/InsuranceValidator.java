package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.Insurance;
import cz.uhk.ppro.vehicle.registry.common.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceValidator extends Validator {

    @Autowired
    private PersonValidator personValidator;

    @Autowired
    private VehicleValidator vehicleValidator;

    @Autowired
    private InsuranceCompanyValidator insuranceCompanyValidator;

    @Autowired
    private UserValidator userValidator;

    public void validate(Insurance insurance)
            throws PersonException, InsuranceException, InsuranceCompanyException, UserException, DocumentException, SpzException, VinException {
        personValidator.validate(insurance.getPerson());
        if (insurance.getFromDate() == null)
            throw new InsuranceException("Není vyplněn datum počátku pojištění!");
        vehicleValidator.validate(insurance.getVehicle());
        insuranceCompanyValidator.validate(insurance.getInsuranceCompany());
        userValidator.validate(insurance.getInsurancer());
    }
}
