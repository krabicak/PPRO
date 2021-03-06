package cz.uhk.ppro.vehicle.registry.common;

import cz.uhk.ppro.vehicle.registry.common.entities.*;
import cz.uhk.ppro.vehicle.registry.common.exceptions.*;

import java.util.List;

public interface VehicleRegistry {

    User loginUser(String login, String password) throws FaultLoginException;

    User getUserByLogin(String login);

    List<User> getAllUsers();

    void addOrUpdateUser(User user) throws PersonException, UserException;

    void removeUser(User user) throws PersonException;

    List<Vehicle> getAllVehicles();

    void addOrUpdateVehicle(Vehicle vehicle)
            throws SpzException, PersonException, VinException, DocumentException;

    List<InsuranceCompany> getAllInsuranceCompanies();

    void addOrUpdateInsuranceCompany(InsuranceCompany insuranceCompany)
            throws InsuranceCompanyException;

    void removeInsuranceCompany(InsuranceCompany insuranceCompany) throws InsuranceCompanyException;

    void addOrUpdateInsuranceEmployee(InsuranceEmployee insuranceEmployee)
            throws PersonException, InsuranceCompanyException, UserException;

    void removeInsuranceEmployee(InsuranceEmployee insuranceEmployee) throws UserException;

    void addOrUpdateInsurance(Insurance insurance)
            throws VinException, SpzException, InsuranceCompanyException, UserException, PersonException, InsuranceException, DocumentException;

    InsuranceEmployee getInsuranceEmployee(User user);

    List<InsuranceEmployee> getAllInsuranceEmployees();

    List<Insurance> getAllInsurancies();

    void removeInsurance(Insurance insurance);

    List<Vehicle> findVehiclesByKeyWord(String keyword);

    List<Vehicle> getUninsuredVehicles();

    List<User> findUsersByKeyWord(String keyword);

    List<InsuranceCompany> findInsuranceCompaniesByKeyWord(String keyword);

    List<Insurance> findInsurancisByKeyWord(String keyword);

    Person findPersonByBornNum(String bornNum);

}
