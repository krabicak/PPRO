package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceEmployee;
import cz.uhk.ppro.vehicle.registry.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceEmployeeRepo extends JpaRepository<InsuranceEmployee, Long> {

    InsuranceEmployee getByUser(User user);

    List<InsuranceEmployee> findByInsuranceCompany(InsuranceCompany insuranceCompany);
}
