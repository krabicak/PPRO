package cz.uhk.ppro.vehicle.registry.entities;


import javax.persistence.*;

@Entity
@Table(name = "PERSON")
public class Person {

  @Id
  @Column(nullable = false)
  @GeneratedValue
  private long idPerson;

  @Column(nullable = false, length = 45)
  private String firstName;

  @Column(nullable = false, length = 45)
  private String lastName;

  public long getIdPerson() {
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
