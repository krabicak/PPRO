package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceEmployee;
import cz.uhk.ppro.vehicle.registry.common.exceptions.InsuranceCompanyException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceEmployeeValidator extends Validator{

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private InsuranceCompanyValidator insuranceCompanyValidator;

    public void validate(InsuranceEmployee insuranceEmployee)
            throws PersonException, UserException, InsuranceCompanyException {
        if (insuranceEmployee==null) throw new UserException("Pojistitel není vyplněn");
        userValidator.validate(insuranceEmployee.getUser());
        insuranceCompanyValidator.validate(insuranceEmployee.getInsuranceCompany());
    }
}
