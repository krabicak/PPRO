package cz.uhk.ppro.vehicle.registry.core;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-vehicle-registry-common-test-AplicationContext.xml")
public class VehicleRegistryImplTest {

    @Autowired
    private VehicleRegistry vehicleRegistry;

    @Test
    public void loginUserTest() throws FaultLoginException {
        vehicleRegistry.loginUser(null,null);
    }

}