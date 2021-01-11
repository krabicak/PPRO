package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.Vehicle;
import cz.uhk.ppro.vehicle.registry.common.exceptions.DocumentException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.SpzException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.VinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleValidator extends Validator {

    @Autowired
    private VinValidator vinValidator;

    @Autowired
    private SpzValidator spzValidator;

    @Autowired
    private DocumentValidator documentValidator;

    @Autowired
    private PersonValidator personValidator;

    public void validate(Vehicle vehicle)
            throws VinException, SpzException, DocumentException, PersonException {
        if (vehicle == null) throw new DocumentException("Vozidlo není vyplněno!");
        vinValidator.validate(vehicle.getVin());
        spzValidator.validate(vehicle.getSpz());
        documentValidator.validate(vehicle.getbTechnicalCert());
        documentValidator.validate(vehicle.getsTechnicalCert());
        personValidator.validate(vehicle.getOwner());
    }
}
