package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findPersonByIdPerson(Long id);
}