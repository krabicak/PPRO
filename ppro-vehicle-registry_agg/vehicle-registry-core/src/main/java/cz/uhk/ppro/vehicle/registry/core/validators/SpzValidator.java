package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.Spz;
import cz.uhk.ppro.vehicle.registry.common.exceptions.SpzException;
import org.springframework.stereotype.Service;

@Service
public class SpzValidator extends Validator {

    public void validate(Spz spz) throws SpzException {
        if (spz == null) throw new SpzException("SPZ není vyplněna!");
        if (isNullOrEmpty(spz.getSpz()))
            throw new SpzException("Není vyplněna SPZ!");
    }
}
