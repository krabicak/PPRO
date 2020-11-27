package cz.uhk.ppro.vehicle.registry.entities;

import javax.persistence.*;

/**
 * Pojišťovna
 *
 * @author hotov
 */
@Entity
@Table(name = "INSURANCE_COMPANY")
public class InsuranceCompany {

  @Id
  @Column(nullable = false)
  @GeneratedValue
  private long idInsuranceCompany;

  /**
   * Název pojišťovny
   */
  @Column(nullable = false, length = 45)
  private String companyName;

  public long getIdInsuranceCompany() {
    return idInsuranceCompany;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @Override
  public String toString() {
    return "InsuranceCompany{" +
            "idInsuranceCompany=" + idInsuranceCompany +
            ", companyName='" + companyName + '\'' +
            '}';
  }
}
