package cz.uhk.ppro.vehicle.registry.common.entities;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity jakékoliv osoby, ať už pojištovák
 * uživatel systému nebo jen identifikace majitele
 *
 * @author hotov
 */
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPerson;

  @OneToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn(referencedColumnName = "IDUSER", name = "IDPERSON")
  private User user;

  /**
   * Jméno
   */
  @Column(nullable = false, length = 45)
  private String firstName;

  /**
   * Příjmení
   */
  @Column(nullable = false, length = 45)
  private String lastName;

  public Person() {
  }

  public Long getIdPerson() {
    return idPerson;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "Person{" +
            "idPerson=" + idPerson +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
