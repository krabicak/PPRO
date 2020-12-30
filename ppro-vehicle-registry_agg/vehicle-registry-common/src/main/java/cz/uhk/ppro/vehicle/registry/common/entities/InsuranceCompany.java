package cz.uhk.ppro.vehicle.registry.common.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Pojišťovna
 *
 * @author hotov
 */
@Entity
@Table(name = "INSURANCE_COMPANY")
public class InsuranceCompany implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idInsuranceCompany;

  /**
   * Název pojišťovny
   */
  @Column(nullable = false, length = 45)
  private String companyName;

  public InsuranceCompany() {
  }

  public Long getIdInsuranceCompany() {
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
