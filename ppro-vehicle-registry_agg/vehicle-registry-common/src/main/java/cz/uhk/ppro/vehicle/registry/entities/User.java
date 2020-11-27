package cz.uhk.ppro.vehicle.registry.entities;

import cz.uhk.ppro.vehicle.registry.converters.BooleanConverter;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

  @Id
  @Column(nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDUSER")
  private Person iduser;

  @Column(nullable = false, length = 7)
  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Column(nullable = false, length = 512)
  private String password;

  @Column(length = 1, nullable = false)
  @Convert(converter = BooleanConverter.class)
  private boolean enable;

  public enum UserRole{
    ADMIN,INSURER,CLERK
  }

  public Person getIduser() {
    return iduser;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  @Override
  public String toString() {
    return "User{" +
            "iduser=" + iduser +
            ", role=" + role +
            ", password='" + password + '\'' +
            ", enable=" + enable +
            '}';
  }
}
