package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.Vin;
import cz.uhk.ppro.vehicle.registry.common.exceptions.VinException;
import org.springframework.stereotype.Service;

@Service
public class VinValidator extends Validator{

    public void validate(Vin vin) throws VinException {
        if (isNullOrEmpty(vin.getVin()))
            throw new VinException("Není vyplněno číslo VIN");
    }
}
