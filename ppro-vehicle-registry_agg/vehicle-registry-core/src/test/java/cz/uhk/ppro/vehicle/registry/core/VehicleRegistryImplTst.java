package cz.uhk.ppro.vehicle.registry.core;

import cz.uhk.ppro.vehicle.registry.common.VehicleRegistry;
import cz.uhk.ppro.vehicle.registry.common.exceptions.FaultLoginException;
import cz.uhk.ppro.vehicle.registry.common.repositories.DocumentRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-vehicle-registry-common-test-AplicationContext.xml")
public class VehicleRegistryImplTst {

    @Autowired
    private VehicleRegistry vehicleRegistry;

    @Autowired
    private DocumentRepo documentRepo;

    @Test
    public void loginUserTest() throws FaultLoginException {
        vehicleRegistry.loginUser(null,null);
    }

    @Test
    public void getDocumentTest(){
        documentRepo.getDocumentByDocumentNumber(null);
    }

}