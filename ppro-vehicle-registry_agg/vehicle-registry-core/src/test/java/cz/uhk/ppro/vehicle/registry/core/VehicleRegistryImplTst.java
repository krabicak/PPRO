package cz.uhk.ppro.vehicle.registry.core;

import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.entities.Vehicle;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.UserException;
import cz.uhk.ppro.vehicle.registry.common.repositories.*;
import cz.uhk.ppro.vehicle.registry.core.validators.*;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration("classpath:/spring-vehicle-registry-core-test-AplicationContext.xml")
public class VehicleRegistryImplTst {

    private static final Logger logger = LoggerFactory.getLogger(VehicleRegistryImplTst.class);

    @InjectMocks
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

    @Spy
    private VehicleValidator vehicleValidator;
    @Spy
    private InsuranceCompanyValidator insuranceCompanyValidator;
    @Spy
    private InsuranceEmployeeValidator insuranceEmployeeValidator;
    @Spy
    private InsuranceValidator insuranceValidator;
    @Spy
    private UserValidator userValidator;

    private User userMock;
    private Person personMock;

    @BeforeEach
    void init() {
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

    }

    @Test
    void getUserByLogin() {
        when(userRepo.getUserByLogin("TESTLOGIN")).thenReturn(userMock);
        User usr = vehicleRegistry.getUserByLogin("TESTLOGIN");
        assertEquals(userMock,usr);
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void addOrUpdateUser() {
        UserException exception = assertThrows(UserException.class, () -> {
            vehicleRegistry.addOrUpdateUser(new User());
        });

        assertEquals(exception.getMessage(),(new UserException("Není vyplněno přihlašovací jméno!")).getMessage());

        PersonException exception1 = assertThrows(PersonException.class, () -> {
            userMock.setPerson(null);
            vehicleRegistry.addOrUpdateUser(userMock);
        });

        assertEquals(exception1.getMessage(),(new PersonException("Není vyplněno přihlašovací jméno!")).getMessage());
    }

    @Test
    void removeUser() {
    }

    @Test
    void getAllVehicles() {
    }

    @Test
    void addOrUpdateVehicle() {
    }

    @Test
    void getAllInsuranceCompanies() {
    }

    @Test
    void addOrUpdateInsuranceCompany() {
    }

    @Test
    void removeInsuranceCompany() {
    }

    @Test
    void addOrUpdateInsuranceEmployee() {
    }

    @Test
    void removeInsuranceEmployee() {
    }

    @Test
    void addOrUpdateInsurance() {
    }

    @Test
    void getInsuranceEmployee() {
    }

    @Test
    void getAllInsuranceEmployees() {
    }

    @Test
    void getAllInsurancies() {
    }

    @Test
    void removeInsurance() {
    }

    @Test
    void findVehiclesByKeyWord() {
    }

    @Test
    void getUninsuredVehicles() {
    }

    @Test
    void findUsersByKeyWord() {
    }

    @Test
    void findInsuranceCompaniesByKeyWord() {
    }

    @Test
    void findInsurancisByKeyWord() {
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
    public void searchVehicleTest(){
        List<Vehicle> vehicles = vehicleRegistry.findVehiclesByKeyWord("dsadsa");
        vehicles.forEach(vehicle -> logger.info(vehicle.toString()));
    }

    @Test
    @Disabled
    public void searchUsersTest(){
        List<User> vehicles = vehicleRegistry.findUsersByKeyWord("dfdsfdsfs");
        vehicles.forEach(vehicle -> logger.info(vehicle.toString()));
    }


    @Test
    @Disabled
    void loginUser() throws FaultLoginException {
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
        assertEquals(user,userMock);
    }
}