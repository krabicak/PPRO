package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.Document;
import cz.uhk.ppro.vehicle.registry.common.exceptions.DocumentException;
import cz.uhk.ppro.vehicle.registry.common.repositories.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentValidator extends Validator {

    @Autowired
    private DocumentRepo documentRepo;

    public void validate(Document document) throws DocumentException {
        if (document == null) throw new DocumentException("Doklad není vyplněn!");
        if (isNullOrEmpty(document.getDocumentNumber()))
            throw new DocumentException("Není vyplněno číslo dokladu!");
        if (document.getDocumentNumber().length() > 45) throw new DocumentException("Číslo dokladu je příliš dlouhé!");
        if (document.getIdDocument() == null) {
            if (documentRepo.getDocumentByDocumentNumber(document.getDocumentNumber()) != null) {
                throw new DocumentException("Zadáno již existující číslo dokladu!");
            }
        }
    }
}
