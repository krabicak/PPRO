package cz.uhk.ppro.vehicle.registry.core;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.*;
import cz.uhk.ppro.vehicle.registry.common.exceptions.*;
import cz.uhk.ppro.vehicle.registry.common.repositories.*;
import cz.uhk.ppro.vehicle.registry.core.validators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
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
    @Autowired
    private InsuranceRepo insuranceRepo;

    @Autowired
    private VehicleValidator vehicleValidator;
    @Autowired
    private InsuranceCompanyValidator insuranceCompanyValidator;
    @Autowired
    private InsuranceEmployeeValidator insuranceEmployeeValidator;
    @Autowired
    private InsuranceValidator insuranceValidator;
    @Autowired
    private UserValidator userValidator;

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

    public void addOrUpdateUser(User user) throws PersonException, UserException {
        userValidator.validate(user);
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

        if (user.getRole() != User.UserRole.INSURER) {
            InsuranceEmployee employee = getInsuranceEmployee(user);
            if (employee != null)
                insuranceEmployeeRepo.delete(employee);
        }

        personRepo.save(user.getPerson());
        if (user.getIdUser() == null) {
            user.setIdUser(user.getPerson().getIdPerson());
        }


        userRepo.save(user);
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
    public void addOrUpdateVehicle(Vehicle vehicle)
            throws SpzException, PersonException, VinException, DocumentException {
        vehicleValidator.validate(vehicle);
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
                && !personRepo.findByIdPerson(person.getIdPerson()).getBornNum().equals(person.getBornNum()))
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
    public void addOrUpdateInsuranceCompany(InsuranceCompany insuranceCompany)
            throws InsuranceCompanyException {
        insuranceCompanyValidator.validate(insuranceCompany);
        insuranceCompanyRepo.save(insuranceCompany);
    }

    @Override
    public void removeInsuranceCompany(InsuranceCompany insuranceCompany) {
        insuranceCompanyRepo.delete(insuranceCompany);
    }

    @Override
    public void addOrUpdateInsuranceEmployee(InsuranceEmployee insuranceEmployee)
            throws PersonException, InsuranceCompanyException, UserException {
        insuranceEmployeeValidator.validate(insuranceEmployee);
        addOrUpdateUser(insuranceEmployee.getUser());
        if (insuranceEmployee.getIdUser() == null) insuranceEmployee.setIdUser(insuranceEmployee.getUser().getIdUser());
        if (insuranceEmployee.getIdInsuranceCompany() == null)
            insuranceEmployee.setIdInsuranceCompany(insuranceEmployee.getInsuranceCompany().getIdInsuranceCompany());
        insuranceEmployeeRepo.save(insuranceEmployee);
    }

    @Override
    public void removeInsuranceEmployee(InsuranceEmployee insuranceEmployee) {
        insuranceEmployeeRepo.delete(insuranceEmployee);
    }


    @Override
    public void addOrUpdateInsurance(Insurance insurance)
            throws VinException, SpzException, InsuranceCompanyException, UserException, PersonException, InsuranceException, DocumentException {
        insuranceValidator.validate(insurance);
        personRepo.save(insurance.getPerson());
        insuranceRepo.save(insurance);
    }

    @Override
    public InsuranceEmployee getInsuranceEmployee(User user) {
        return insuranceEmployeeRepo.getByUser(user);
    }

    @Override
    public List<InsuranceEmployee> getAllInsuranceEmployees() {
        return insuranceEmployeeRepo.findAll();
    }

    @Override
    public List<Insurance> getAllInsurancies() {
        return insuranceRepo.findAll();
    }

    @Override
    public void removeInsurance(Insurance insurance) {
        insuranceRepo.delete(insurance);
    }

    @Override
    public List<Vehicle> findVehiclesByKeyWord(String keyword) {
        return vehicleRepo.findVehiclesByKeyWord(keyword);
    }

    @Override
    public List<Vehicle> getUnsiredVehicles() {
        List<Vehicle> vehicles = vehicleRepo.findVehiclesWithoutInsurance(true);
        vehicles.addAll(vehicleRepo.findVehiclesWithFailedInsurance(true,
                new Timestamp(System.currentTimeMillis())));
        return vehicles;
    }

    @Override
    public List<User> findUsersByKeyWord(String keyword) {
        return userRepo.findUsersByKeyword(keyword);
    }

    @Override
    public List<InsuranceCompany> findInsuranceCompaniesByKeyWord(String keyword) {
        return insuranceCompanyRepo.findInsuranceCompaniesByKeyword(keyword);
    }

    @Override
    public List<Insurance> findInsurancisByKeyWord(String keyword) {
        return insuranceRepo.findInsurancesByKeyword(keyword);
    }


}
