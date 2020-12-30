package cz.uhk.ppro.vehicle.registry.common.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Zaměstnanec pojišťovny/pojišťovák
 *
 * @author hotov
 */
@Entity
@Table(name = "INSURANCE_EMPLOYEE")
public class InsuranceEmployee implements Serializable {

  @Id
  @Column(nullable = false, updatable = false)
  private Long idUser;
  /**
   * Informace o uživateli
   */
  @OneToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn(name = "IDUSER", referencedColumnName="IDUSER")
  private User user;

  /**
   * Pojišťovna
   */
  @Id
  @Column(nullable = false, updatable = false)
  private Long idInsuranceCompany;

  @OneToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn(name = "IDINSURANCECOMPANY", referencedColumnName="IDINSURANCECOMPANY")
  private InsuranceCompany insuranceCompany;

  public InsuranceEmployee() {
  }

  public Long getIdUser() {
    return idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public Long getIdInsuranceCompany() {
    return idInsuranceCompany;
  }

  public void setIdInsuranceCompany(Long idInsuranceCompany) {
    this.idInsuranceCompany = idInsuranceCompany;
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
