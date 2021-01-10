package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v from Vehicle v " +
            "join v.vin vi " +
            "join v.owner o " +
            "join v.bTechnicalCert b " +
            "join v.sTechnicalCert s " +
            "join v.spz sp " +
            "where o.bornNum like %:keyword% OR " +
            "o.lastName like %:keyword% OR " +
            "b.documentNumber like %:keyword% OR " +
            "s.documentNumber like %:keyword% OR " +
            "sp.spz like %:keyword% OR " +
            "vi.vin like %:keyword%")
    List<Vehicle> findVehiclesByKeyWord(@Param("keyword") String keyWord);

    @Query("SELECT v from Insurance i join i.vehicle v " +
            "where v.active = ?1 and (i.toDate < ?2 OR i is null)")
    List<Vehicle> findVehiclesWithFailedInsurance(boolean active, Timestamp timestamp);

    @Query("SELECT v from Vehicle v where v.active = ?1 and v.insurances is empty ")
    List<Vehicle> findVehiclesWithoutInsurance(boolean active);
}
