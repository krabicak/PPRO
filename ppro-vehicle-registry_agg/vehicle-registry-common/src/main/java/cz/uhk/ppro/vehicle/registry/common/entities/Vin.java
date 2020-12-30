package cz.uhk.ppro.vehicle.registry.common.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entita pro vin
 *
 * @author hotov
 */
@Entity
@Table(name = "VIN")
public class Vin implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idvin;

  /**
   * samotné VIN
   */
  @Column(length = 40, nullable = false)
  private String vin;

  public Vin() {
  }

  public void setIdvin(Long idvin) {
    this.idvin = idvin;
  }

  public Long getIdvin() {
    return idvin;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  @Override
  public String toString() {
    return "Vin{" +
            "idvin=" + idvin +
            ", vin='" + vin + '\'' +
            '}';
  }
}
