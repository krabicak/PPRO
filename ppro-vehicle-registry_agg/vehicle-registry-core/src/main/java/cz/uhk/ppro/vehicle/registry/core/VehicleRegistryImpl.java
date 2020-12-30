package cz.uhk.ppro.vehicle.registry.core;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.*;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VehicleRegistryImpl implements VehicleRegistry {

    private static final Logger logger = LoggerFactory.getLogger(VehicleRegistryImpl.class);

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private InsuranceCompanyRepo insuranceCompanyRepo;
    @Autowired
    private DocumentRepo documentRepo;
    @Autowired
    private SpzRepo spzRepo;
    @Autowired
    private VinRepo vinRepo;
    @Autowired
    private InsuranceEmployeeRepo insuranceEmployeeRepo;

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

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public void addOrUpdateVehicle(Vehicle vehicle) {
        Document documentb = vehicle.getbTechnicalCert();
        if (documentb.getIdDocument() != null
                && documentRepo.getDocumentByDocumentNumber(documentb.getDocumentNumber()) == null)
            documentb.setIdDocument(null);
        documentRepo.save(documentb);
        Document documents = vehicle.getsTechnicalCert();
        if (documents.getIdDocument() != null
                && documentRepo.getDocumentByDocumentNumber(documents.getDocumentNumber()) == null)
            documents.setIdDocument(null);
        documentRepo.save(documents);
        Person person = vehicle.getOwner();
        if (person.getIdPerson() != null
                && !personRepo.findPersonByIdPerson(person.getIdPerson()).getBornNum().equals(person.getBornNum()))
            person.setIdPerson(null);
        personRepo.save(person);
        Spz spz = vehicle.getSpz();
        if (spz.getIdSpz() != null
                && spzRepo.findBySpz(spz.getSpz()) == null)
            spz.setIdSpz(null);
        spzRepo.save(spz);
        Vin vin = vehicle.getVin();
        if (vin.getIdvin() != null && vinRepo.findByVin(vin.getVin()) == null)
            vin.setIdvin(null);
        vinRepo.save(vin);
        vehicleRepo.save(vehicle);
    }

    @Override
    public List<InsuranceCompany> getAllInsuranceCompanies() {
        return insuranceCompanyRepo.findAll();
    }

    @Override
    public void addOrUpdateInsuranceCompany(InsuranceCompany insuranceCompany) {
        insuranceCompanyRepo.save(insuranceCompany);
    }

    @Override
    public void removeInsuranceCompany(InsuranceCompany insuranceCompany) {
        insuranceCompanyRepo.delete(insuranceCompany);
    }

    @Override
    public void addOrUpdateInsuranceEmployee(InsuranceEmployee insuranceEmployee) {
        insuranceEmployeeRepo.save(insuranceEmployee);
    }

}
