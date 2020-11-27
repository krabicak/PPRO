package cz.uhk.ppro.vehicle.registry.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Entity pro pojištění na určité vozidlo
 *
 * @author hotov
 */
@Entity
@Table(name = "INSURANCE")
public class Insurance {

  @Id
  @Column(nullable = false)
  @GeneratedValue
  private long idInsurance;

  /**
   * Pojištění od
   */
  @Column(nullable = false)
  private java.sql.Timestamp fromDate;

  /**
   * Pojištění do
   */
  @Column(nullable = false)
  private java.sql.Timestamp toDate;

  /**
   * Pojišťovna
   */
  @Column(nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDINSURANCECOMPANY")
  private InsuranceCompany insuranceCompany;

  /**
   * Vozidlo na které se vztahuje pojištění
   */
  @Column(nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDVEHICLE")
  private Vehicle vehicle;

  @Column(nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDUSER")
  private User user;

  public long getIdInsurance() {
    return idInsurance;
  }

  public Timestamp getFromDate() {
    return fromDate;
  }

  public void setFromDate(Timestamp fromDate) {
    this.fromDate = fromDate;
  }

  public Timestamp getToDate() {
    return toDate;
  }

  public void setToDate(Timestamp toDate) {
    this.toDate = toDate;
  }

  public InsuranceCompany getInsuranceCompany() {
    return insuranceCompany;
  }

  public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
    this.insuranceCompany = insuranceCompany;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Insurance{" +
            "idInsurance=" + idInsurance +
            ", fromDate=" + fromDate +
            ", toDate=" + toDate +
            ", insuranceCompany=" + insuranceCompany +
            ", vehicle=" + vehicle +
            ", user=" + user +
            '}';
  }
}
