package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.Vin;
import cz.uhk.ppro.vehicle.registry.common.exceptions.SpzException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.VinException;
import cz.uhk.ppro.vehicle.registry.common.repositories.VinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VinValidator extends Validator{

    @Autowired
    private VinRepo vinRepo;

    public void validate(Vin vin) throws VinException {
        if (vin==null) throw new VinException("VIN není vyplněno!");
        if (isNullOrEmpty(vin.getVin()))
            throw new VinException("Není vyplněno číslo VIN!");
        if (vin.getVin().length() > 40) throw new VinException("Číslo VIN je příliš dlouhé!");
        if (vin.getIdvin() == null) {
            if (vinRepo.getVinByVin(vin.getVin()) != null){
                throw new VinException("Zadáno již existující VIN!");
            }
        }
    }
}
