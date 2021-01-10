package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceCompanyRepo extends JpaRepository<InsuranceCompany, Long> {

    @Query("SELECT ic from InsuranceCompany ic where ic.companyName like %:keyword%")
    List<InsuranceCompany> findInsuranceCompaniesByKeyword(@Param("keyword") String keyword);
}
