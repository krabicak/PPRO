package cz.uhk.ppro.vehicle.registry.common.entities;

import javax.persistence.*;

/**
 * Entita pro vin
 *
 * @author hotov
 */
@Entity
@Table(name = "VIN")
public class Vin {

  @Id
  @Column(nullable = false)
  @GeneratedValue
  private Long idvin;

  /**
   * samotné VIN
   */
  @Column(length = 40, nullable = false)
  private String vin;

  public long getIdvin() {
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
