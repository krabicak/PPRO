package cz.uhk.ppro.vehicle.registry.entities;

import javax.persistence.*;

@Entity
@Table(name = "INSURANCE_EMPLOYEE")
public class InsuranceEmployee {

  @Id
  @Column(nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDUSER")
  private User user;

  @Id
  @Column(nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDINSURANCECOMPANY")
  private InsuranceCompany insuranceCompany;

  public User getUser() {
    return user;
  }

  public InsuranceCompany getInsuranceCompany() {
    return insuranceCompany;
  }

  public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
    this.insuranceCompany = insuranceCompany;
  }

  @Override
  public String toString() {
    return "InsuranceEmployee{" +
            "user=" + user +
            ", insuranceCompany=" + insuranceCompany +
            '}';
  }
}
