package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.Spz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpzRepo extends JpaRepository<Spz, Long> {
    Spz findBySpz(String spz);
}
