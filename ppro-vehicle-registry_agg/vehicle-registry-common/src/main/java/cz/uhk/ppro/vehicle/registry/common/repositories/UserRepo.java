package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User getUserByLoginAndPassword(String login, String password);

    User getUserByLogin(String login);

    User getUserByLoginAndIdUserIsNot(String login, Long idUser);

    User getUserByIdUser(Long idUser);

    List<User> findAllByOrderByPerson();

    @Query("select u from User u join u.person p " +
            "where u.login like %:keyword% or " +
            "p.firstName like %:keyword% or " +
            "p.lastName like %:keyword% or " +
            "p.bornNum like %:keyword%")
    List<User> findUsersByKeyword(@Param("keyword") String keyword);
}
