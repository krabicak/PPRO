package cz.uhk.ppro.vehicle.registry.core;

import cz.uhk.ppro.vehicle.registry.common.entities.*;
import cz.uhk.ppro.vehicle.registry.common.exceptions.*;
import cz.uhk.ppro.vehicle.registry.common.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration("classpath:/spring-vehicle-registry-core-test-AplicationContext.xml")
public class VehicleRegistryImplTest {

    private static final Logger logger = LoggerFactory.getLogger(VehicleRegistryImplTest.class);

    @InjectMocks
    @Autowired
    private VehicleRegistryImpl vehicleRegistry;

    @Mock
    private UserRepo userRepo;
    @Mock
    private PersonRepo personRepo;
    @Mock
    private VehicleRepo vehicleRepo;
    @Mock
    private InsuranceCompanyRepo insuranceCompanyRepo;
    @Mock
    private DocumentRepo documentRepo;
    @Mock
    private SpzRepo spzRepo;
    @Mock
    private VinRepo vinRepo;
    @Mock
    private InsuranceEmployeeRepo insuranceEmployeeRepo;
    @Mock
    private InsuranceRepo insuranceRepo;

    private User userMock;
    private Person personMock;
    private Vin vinMock;
    private Spz spzMock;
    private Document documentMock;
    private Vehicle vehicleMock;
    private Insurance insuranceMock;
    private InsuranceCompany insuranceCompanyMock;
    private InsuranceEmployee insuranceEmployeeMock;

    @BeforeEach
    public void init() {
        userMock = new User();
        userMock.setRole(User.UserRole.ADMIN);
        userMock.setPassword("94EE059335E587E501CC4BF90613E0814F00A7B08BC7C648FD865A2AF6A22CC2");
        userMock.setEnable(true);
        userMock.setLogin("TESTLOGIN");
        userMock.setIdUser(1L);

        personMock = new Person();
        personMock.setIdPerson(1L);
        personMock.setBornNum("123456789");
        personMock.setLastName("Lopata");
        personMock.setFirstName("František");
        userMock.setPerson(personMock);

        //hotovo
        vinMock = new Vin();
        vinMock.setIdvin(1L);
        vinMock.setVin("TMBLJ7NS0J8086958");

        //hotovo
        spzMock = new Spz();
        spzMock.setSpz("9A99999");
        spzMock.setIdSpz(1L);

        //hotovo
        insuranceCompanyMock = new InsuranceCompany();
        insuranceCompanyMock.setCompanyName("Kooperativa");

        //hotovo
        documentMock = new Document();
        documentMock.setDocumentNumber("UAC123456");
        documentMock.setToDate(new Timestamp(System.currentTimeMillis()));
        documentMock.setIdDocument(1L);

        //hotovo
        vehicleMock = new Vehicle();
        vehicleMock.setSpz(spzMock);
        vehicleMock.setVin(vinMock);
        vehicleMock.setbTechnicalCert(documentMock);
        vehicleMock.setsTechnicalCert(documentMock);
        vehicleMock.setActive(true);
        vehicleMock.setOwner(personMock);

        //hotovo
        insuranceMock = new Insurance();
        insuranceMock.setInsuranceCompany(insuranceCompanyMock);
        insuranceMock.setVehicle(vehicleMock);
        insuranceMock.setToDate(new Timestamp(System.currentTimeMillis()));
        insuranceMock.setFromDate(new Timestamp(System.currentTimeMillis()));
        insuranceMock.setPerson(personMock);
        insuranceMock.setInsurancer(userMock);

        //hotovo
        insuranceEmployeeMock = new InsuranceEmployee();
        insuranceEmployeeMock.setUser(userMock);
        insuranceEmployeeMock.setInsuranceCompany(insuranceCompanyMock);
        insuranceEmployeeMock.setIdInsuranceCompany(1L);

    }

    @Test
    public void getUserByLogin() {
        when(userRepo.getUserByLogin("TESTLOGIN")).thenReturn(userMock);
        User usr = vehicleRegistry.getUserByLogin("TESTLOGIN");
        assertEquals(userMock, usr);
    }

    @Test
    public void getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(userMock);
        when(userRepo.findAllByOrderByPerson()).thenReturn(allUsers);
        List<User> allUsers1 = vehicleRegistry.getAllUsers();
        assertEquals(allUsers, allUsers1);
    }

    @Test
    public void addOrUpdateUser() {
        UserException exception = assertThrows(UserException.class, () -> {
            vehicleRegistry.addOrUpdateUser(new User());
        });

        assertEquals((new UserException("Není vyplněno přihlašovací jméno!")).getMessage(), exception.getMessage());

        PersonException exception1 = assertThrows(PersonException.class, () -> {
            userMock.setPerson(null);
            vehicleRegistry.addOrUpdateUser(userMock);
        });

        assertEquals((new PersonException("Údaje o osobě nejsou vyplěny!")).getMessage(), exception1.getMessage());
    }

    @Test
    public void removeUser() throws PersonException {
        vehicleRegistry.removeUser(userMock);
    }

    @Test
    public void getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicleMock);
        when(vehicleRepo.findAll()).thenReturn(vehicles);
        assertEquals(vehicles,vehicleRegistry.getAllVehicles());
    }

    @Test
    public void getAllInsuranceCompanies() {
        List<InsuranceCompany> insuranceCompanies = new ArrayList<>();
        insuranceCompanies.add(insuranceCompanyMock);
        when(insuranceCompanyRepo.findAll()).thenReturn(insuranceCompanies);
        assertEquals(insuranceCompanies,vehicleRegistry.getAllInsuranceCompanies());
    }

    @Test
    public void removeInsuranceCompany() throws InsuranceCompanyException {
        vehicleRegistry.removeInsuranceCompany(insuranceCompanyMock);
    }

    @Test
    public void removeInsuranceEmployee() throws UserException {
        vehicleRegistry.removeInsuranceEmployee(insuranceEmployeeMock);
    }

    @Test
    public void getAllInsuranceEmployees() {
        List<InsuranceEmployee> insuranceEmployees = new ArrayList<>();
        when(insuranceEmployeeRepo.findAll()).thenReturn(insuranceEmployees);
        assertEquals(insuranceEmployees,vehicleRegistry.getAllInsuranceEmployees());
    }

    @Test
    public void getAllInsurancies() {
        List<Insurance> insurances = new ArrayList<>();
        when(insuranceRepo.findAll()).thenReturn(insurances);
        assertEquals(insurances,vehicleRegistry.getAllInsurancies());
    }

    @Test
    public void removeInsurance() {
        vehicleRegistry.removeInsurance(insuranceMock);
    }

    @Test
    public void findVehiclesByKeyWord() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicleMock);
        when(vehicleRepo.findVehiclesByKeyWord("A")).thenReturn(vehicles);
        assertEquals(vehicles,vehicleRegistry.findVehiclesByKeyWord("A"));
    }

    @Test
    public void findUsersByKeyWord() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(userMock);
        when(userRepo.findUsersByKeyword("A")).thenReturn(allUsers);
        List<User> allUsers1 = vehicleRegistry.findUsersByKeyWord("A");
        assertEquals(allUsers, allUsers1);
    }

    @Test
    public void findInsuranceCompaniesByKeyWord() {
        List<InsuranceCompany> insuranceCompanies = new ArrayList<>();
        insuranceCompanies.add(insuranceCompanyMock);
        when(insuranceCompanyRepo.findInsuranceCompaniesByKeyword("A")).thenReturn(insuranceCompanies);
        assertEquals(insuranceCompanies,vehicleRegistry.findInsuranceCompaniesByKeyWord("A"));
    }

    @Test
    public void findInsurancisByKeyWord() {
        List<Insurance> insurances = new ArrayList<>();
        when(insuranceRepo.findInsurancesByKeyword("A")).thenReturn(insurances);
        assertEquals(insurances,vehicleRegistry.findInsurancisByKeyWord("A"));
    }

    @Test
    @Disabled
    public void getAllUsersTest() {
        final List<User> users = vehicleRegistry.getAllUsers();
        users.forEach(user -> {
            logger.info(user.toString());
        });
    }

    @Test
    @Disabled
    public void addUserTest() throws PersonException, UserException {
        User user = new User();
        user.setEnable(true);
        user.setLogin("test");
        user.setPassword("test");
        user.setRole(User.UserRole.ADMIN);

        Person person = new Person();
        person.setFirstName("test");
        person.setLastName("test");
        user.setPerson(person);

        //vehicleRegistry.addOrUpdateUser(user);
    }

    @Test
    @Disabled
    public void updateLoginTest() throws PersonException, UserException {
        User user = vehicleRegistry.getUserByLogin("test-edit1");
        User user1 = new User();
        user1.setLogin("test-edit");
        user1.setRole(User.UserRole.ADMIN);
        user1.setPassword("neco");
        user1.setEnable(true);

        Person person = new Person();
        person.setFirstName("test");
        person.setLastName("test");
        user1.setPerson(person);
        vehicleRegistry.addOrUpdateUser(user1);

        logger.info(user.toString());
        logger.info(user.getPassword());
        user.setPassword("heslo-edit1");
        user.setLogin("test-edit");
        user.setRole(User.UserRole.CLERK);
        vehicleRegistry.addOrUpdateUser(user);
    }

    @Test
    @Disabled
    public void getDocumentTest() {
        documentRepo.getDocumentByDocumentNumber(null);
    }

    @Test
    @Disabled
    public void removeUserTest() throws PersonException {
        User user = vehicleRegistry.getUserByLogin("dsadsa");
        vehicleRegistry.removeUser(user);
    }

    @Test
    @Disabled
    public void searchVehicleTest() {
        List<Vehicle> vehicles = vehicleRegistry.findVehiclesByKeyWord("dsadsa");
        vehicles.forEach(vehicle -> logger.info(vehicle.toString()));
    }

    @Test
    @Disabled
    public void searchUsersTest() {
        List<User> vehicles = vehicleRegistry.findUsersByKeyWord("dfdsfdsfs");
        vehicles.forEach(vehicle -> logger.info(vehicle.toString()));
    }


    @Test
    @Disabled
    public void loginUser() throws FaultLoginException {
        User userMock = new User();
        userMock.setLogin("hotov");
        userMock.setEnable(true);
        userMock.setPassword("94EE059335E587E501CC4BF90613E0814F00A7B08BC7C648FD865A2AF6A22CC2");
        userMock.setRole(User.UserRole.ADMIN);
        Person person = new Person();
        person.setFirstName("Petr");
        person.setLastName("Hotovec");
        person.setBornNum("rodnecislo");
        userMock.setPerson(person);
        when(vehicleRegistry.loginUser("hotov", "94EE059335E587E501CC4BF90613E0814F00A7B08BC7C648FD865A2AF6A22CC2")).thenReturn(userMock);


        User user = vehicleRegistry.loginUser("hotov", "94EE059335E587E501CC4BF90613E0814F00A7B08BC7C648FD865A2AF6A22CC2");
        assertEquals(user, userMock);
    }
}