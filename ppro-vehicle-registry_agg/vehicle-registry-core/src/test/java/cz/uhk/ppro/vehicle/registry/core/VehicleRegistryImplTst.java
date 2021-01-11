package cz.uhk.ppro.vehicle.registry.core;

import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.entities.Vehicle;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.UserException;
import cz.uhk.ppro.vehicle.registry.common.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginUserTest() throws FaultLoginException {

    }

    @Test
    public void getUserByLoginTest() {
        User user = vehicleRegistry.getUserByLogin("hotov");
    }

    @Test
    public void getAllUsersTest() {
        final List<User> users = vehicleRegistry.getAllUsers();
        users.forEach(user -> {
            logger.info(user.toString());
        });
    }

    @Test
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
    public void getDocumentTest() {
        documentRepo.getDocumentByDocumentNumber(null);
    }

    @Test
    public void removeUserTest() throws PersonException {
        User user = vehicleRegistry.getUserByLogin("dsadsa");
        vehicleRegistry.removeUser(user);
    }

    @Test
    public void searchVehicleTest(){
        List<Vehicle> vehicles = vehicleRegistry.findVehiclesByKeyWord("dsadsa");
        vehicles.forEach(vehicle -> logger.info(vehicle.toString()));
    }

    @Test
    public void searchUsersTest(){
        List<User> vehicles = vehicleRegistry.findUsersByKeyWord("dfdsfdsfs");
        vehicles.forEach(vehicle -> logger.info(vehicle.toString()));
    }


    @Test
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
        Mockito.when(vehicleRegistry.loginUser("hotov", "94EE059335E587E501CC4BF90613E0814F00A7B08BC7C648FD865A2AF6A22CC2")).thenReturn(userMock);


        User user = vehicleRegistry.loginUser("hotov", "94EE059335E587E501CC4BF90613E0814F00A7B08BC7C648FD865A2AF6A22CC2");
        assertEquals(user,userMock);
    }

    @Test
    void getUserByLogin() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void addOrUpdateUser() {
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
}