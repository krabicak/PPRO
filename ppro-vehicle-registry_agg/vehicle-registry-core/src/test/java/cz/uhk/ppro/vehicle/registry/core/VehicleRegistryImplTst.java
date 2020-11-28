package cz.uhk.ppro.vehicle.registry.core;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import cz.uhk.ppro.vehicle.registry.common.exceptions.PersonException;
import cz.uhk.ppro.vehicle.registry.common.repositories.DocumentRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-vehicle-registry-common-test-AplicationContext.xml")
public class VehicleRegistryImplTst {

    private static final Logger logger = LoggerFactory.getLogger(VehicleRegistryImplTst.class);

    @Autowired
    private VehicleRegistry vehicleRegistry;

    @Autowired
    private DocumentRepo documentRepo;

    @Test
    public void loginUserTest() throws FaultLoginException {
        User user = vehicleRegistry.loginUser("hotov","94EE059335E587E501CC4BF90613E0814F00A7B08BC7C648FD865A2AF6A22CC2");
        logger.info(user.toString());
    }

    @Test
    public void getUserByLoginTest() {
        User user = vehicleRegistry.getUserByLogin("hotov");
    }

    @Test
    public void getAllUsersTest(){
        List<User> users = vehicleRegistry.getAllUsers();
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

        vehicleRegistry.addUser(user);
    }

    @Test
    public void getDocumentTest(){
        documentRepo.getDocumentByDocumentNumber(null);
    }

}