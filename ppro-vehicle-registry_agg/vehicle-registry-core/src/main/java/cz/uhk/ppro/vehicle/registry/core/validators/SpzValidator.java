package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.Spz;
import cz.uhk.ppro.vehicle.registry.common.exceptions.DocumentException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.SpzException;
import cz.uhk.ppro.vehicle.registry.common.repositories.SpzRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpzValidator extends Validator {

    @Autowired
    private SpzRepo spzRepo;

    public void validate(Spz spz) throws SpzException {
        if (spz == null) throw new SpzException("SPZ není vyplněna!");
        if (isNullOrEmpty(spz.getSpz()))
            throw new SpzException("Není vyplněna SPZ!");
        if (spz.getIdSpz() == null) {
            if (spzRepo.getSpzBySpz(spz.getSpz()) != null){
                throw new SpzException("Zadána již existující SPZ!");
            }
        }
    }
}
