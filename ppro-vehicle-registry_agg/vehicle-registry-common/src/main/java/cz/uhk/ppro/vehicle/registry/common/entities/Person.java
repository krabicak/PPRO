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
  @Column(nullable = false, updatable = false)
  private Long idPerson;

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

  /**
   * RČ
   */
  @Column(nullable = false, length = 25)
  private String bornNum;

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

  public String getBornNum() {
    return bornNum;
  }

  public void setBornNum(String bornNum) {
    this.bornNum = bornNum;
  }

  @Override
  public String toString() {
    return "Person{" +
            "idPerson=" + idPerson +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", bornNum='" + bornNum + '\'' +
            '}';
  }
}
