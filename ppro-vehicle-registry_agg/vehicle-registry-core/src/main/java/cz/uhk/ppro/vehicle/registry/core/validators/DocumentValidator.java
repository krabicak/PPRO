package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.Document;
import cz.uhk.ppro.vehicle.registry.common.exceptions.DocumentException;
import org.springframework.stereotype.Service;

@Service
public class DocumentValidator extends Validator{

    public void validate(Document document) throws DocumentException {
        if (isNullOrEmpty(document.getDocumentNumber()))
            throw new DocumentException("Není vyplněno číslo dokladu!");
    }
}
