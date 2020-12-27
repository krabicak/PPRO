package cz.uhk.ppro.vehicle.registry.core;


import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.repositories.PersonRepo;
import cz.uhk.ppro.vehicle.registry.common.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleRegistryImpl implements VehicleRegistry {

    private static final Logger logger = LoggerFactory.getLogger(VehicleRegistryImpl.class);

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PersonRepo personRepo;

    public User loginUser(String login, String password) throws FaultLoginException {
        User user = userRepo.getUserByLoginAndPassword(login, password);
        if (user == null) throw new FaultLoginException("Neplatný login nebo heslo!");
        logger.debug("Získán uživatel: ");
        logger.debug(user.toString());
        if (!user.isEnable()) throw new FaultLoginException("Tento účet byl deaktivován.");
        return user;
    }

    public User getUserByLogin(String login) {
        return userRepo.getUserByLogin(login);
    }

    public List<User> getAllUsers() {
        return userRepo.findAllByOrderByPerson();
    }

    public void addOrUpdateUser(User user) throws PersonException {
        if (user.getPerson() == null) throw new PersonException("Neexistují údaje o osobě");

        // dělám insert tedy nemůžu dát tma kde je unique
        if (user.getIdUser() == null && getUserByLogin(user.getLogin()) != null)
            throw new PersonException("Login \"" + user.getLogin() + "\" již v systému existuje");
        //dělám update, kontroluji jestli se měnil login, pokud ano tak kontroluji unique
        else if (user.getIdUser() != null) {
            User oldUser = userRepo.getUserByIdUser(user.getIdUser());
            if (!oldUser.getLogin().equals(user.getLogin())
                    && userRepo.getUserByLoginAndIdUserIsNot(user.getLogin(), user.getIdUser()) != null)
                throw new PersonException("Login \"" + user.getLogin() + "\" již v systému existuje");
        }

        personRepo.saveAndFlush(user.getPerson());
        if (user.getIdUser() == null) {
            user.setIdUser(user.getPerson().getIdPerson());
        }


        userRepo.saveAndFlush(user);
    }

    public void removeUser(User user) throws PersonException {
        if (user == null) throw new PersonException("Neexistující uživatel");
        if (user.getIdUser() == null) throw new PersonException("Zadaný uživatel, nebyl dosud zapsán do databáze");
        userRepo.delete(user);
    }

}
