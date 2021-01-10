package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepo extends JpaRepository<Insurance, Long> {

    @Query("SELECT i from Insurance i " +
            "join i.person p " +
            "join i.vehicle v " +
            "join v.spz s " +
            "join v.vin vi " +
            "join v.sTechnicalCert st " +
            "join v.bTechnicalCert bt " +
            "join v.owner o " +
            "join i.insuranceCompany ic " +
            "where " +
            "p.bornNum like %:keyword% or " +
            "p.lastName like %:keyword% or " +
            "p.firstName like %:keyword% or " +
            "s.spz like %:keyword% or " +
            "vi.vin like %:keyword% or " +
            "st.documentNumber like %:keyword% or " +
            "bt.documentNumber like %:keyword% or " +
            "ic.companyName like %:keyword% or " +
            "o.firstName like %:keyword% or " +
            "o.bornNum like %:keyword% or " +
            "o.lastName like %:keyword%")
    List<Insurance> findInsurancesByKeyword(@Param("keyword") String keyword);
}
