package cz.uhk.ppro.vehicle.registry.core.validators;

import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.UserException;
import cz.uhk.ppro.vehicle.registry.common.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator extends Validator {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PersonValidator personValidator;

    public void validate(User user) throws UserException, PersonException {
        if (user == null) throw new UserException("Uživatel není vyplněn!");
        if (isNullOrEmpty(user.getLogin()))
            throw new UserException("Není vyplněno přihlašovací jméno!");
        if (user.getLogin().length() > 45)
            throw new UserException("Přihlašovací jméno je příliš dlouhé!");
        if (userRepo.getUserByLogin(user.getLogin()) != null) {
            if (user.getIdUser() == null)
                throw new UserException("Vyplněno již existující přihlašovací jméno!");
        }
        if (user.getRole() == null)
            throw new UserException("Není vyplněna role uživatele!");
        if (user.getLogin().length() > 512)
            throw new UserException("Heslo je příliš dlouhé!");
        if (isNullOrEmpty(user.getPassword())) {
            if (user.getIdUser() == null)
                throw new UserException("Není vyplněno heslo!");
        }

        if (user.getIdUser() != null) {
            User oldUser = userRepo.getUserByIdUser(user.getIdUser());
            if (!oldUser.getLogin().equals(user.getLogin())
                    && userRepo.getUserByLoginAndIdUserIsNot(user.getLogin(), user.getIdUser()) != null)
                throw new UserException("Vyplněno již existující přihlašovací jméno!");
        }
        personValidator.validate(user.getPerson());
    }
}
