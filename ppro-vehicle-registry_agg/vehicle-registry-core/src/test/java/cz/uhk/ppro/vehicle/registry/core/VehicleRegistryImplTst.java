package cz.uhk.ppro.vehicle.registry.core;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.entities.Vehicle;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.UserException;
import cz.uhk.ppro.vehicle.registry.common.repositories.DocumentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration("classpath:/spring-vehicle-registry-common-test-AplicationContext.xml")
public class VehicleRegistryImplTst {

    private static final Logger logger = LoggerFactory.getLogger(VehicleRegistryImplTst.class);

    @Autowired
    private VehicleRegistry vehicleRegistry;

    @Mock
    private DocumentRepo documentRepo;

    @BeforeEach
    void init() {

    }

    @Test
    public void loginUserTest() throws FaultLoginException {
        User user = vehicleRegistry.loginUser("hotov", "94EE059335E587E501CC4BF90613E0814F00A7B08BC7C648FD865A2AF6A22CC2");
        logger.info(user.toString());
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
    public void addUserTest() throws PersonException {
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

}