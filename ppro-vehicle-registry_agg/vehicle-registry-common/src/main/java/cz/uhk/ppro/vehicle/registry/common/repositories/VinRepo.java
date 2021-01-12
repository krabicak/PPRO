package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.Vin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinRepo extends JpaRepository<Vin, Long> {
    Vin findByVin(String vin);

    Vin getVinByVin(String vin);
}
