package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findByIdPerson(Long id);

    Person findByBornNum(String bornNum);

    Person getPersonByBornNumAndIdPersonIsNot(String bornNum, Long idPerson);

    Person findByBornNumAndFirstNameAndLastName(String bornNum, String firstName, String lastName);
}
