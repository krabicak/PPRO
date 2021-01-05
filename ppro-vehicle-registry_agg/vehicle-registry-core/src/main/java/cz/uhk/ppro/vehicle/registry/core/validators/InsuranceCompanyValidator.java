package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import cz.uhk.ppro.vehicle.registry.common.exceptions.InsuranceCompanyException;
import org.springframework.stereotype.Service;

@Service
public class InsuranceCompanyValidator extends Validator{

    public void validate(InsuranceCompany insuranceCompany) throws InsuranceCompanyException {
        if (insuranceCompany.getCompanyName()==null)
            throw new InsuranceCompanyException("Není vyplněn název pojišťovny!");
    }
}
