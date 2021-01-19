package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.UserException;
import cz.uhk.ppro.vehicle.registry.common.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonValidator extends Validator {

    @Autowired
    private PersonRepo personRepo;

    public void validate(Person person) throws PersonException {
        if (person == null) throw new PersonException("Údaje o osobě nejsou vyplěny!");
        if (personRepo.findByBornNum(person.getBornNum()) != null) {
            if (person.getIdPerson() == null)
                throw new PersonException("Rodné číslo " + person.getBornNum() + " již v systému existuje!");
        }
        if (isNullOrEmpty(person.getFirstName()))
            throw new PersonException("Není vyplněno křestní jméno!");
        if (person.getFirstName().length() > 45) throw new PersonException("Křestní jméno je příliš dlouhé!");
        if (isNullOrEmpty(person.getLastName()))
            throw new PersonException("Není vyplněno příjmení!");
        if (person.getLastName().length() > 45) throw new PersonException("Příjmení je příliš dlouhé!");
        if (isNullOrEmpty(person.getBornNum()))
            throw new PersonException("Není vyplněno rodné číslo!");
        if (person.getLastName().length() > 25) throw new PersonException("Rodné číslo je příliš dlouhé!");

        if (person.getIdPerson() != null) {
            Person oldperson = personRepo.getOne(person.getIdPerson());
            if (!oldperson.getBornNum().equals(person.getBornNum())
                    && personRepo.getPersonByBornNumAndIdPersonIsNot(person.getBornNum(), person.getIdPerson()) != null)
                throw new PersonException("Rodné číslo " + person.getBornNum() + " již v systému existuje!");
        }
    }
}
