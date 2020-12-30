package cz.uhk.ppro.vehicle.registry.common.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * číselník/smetiště pro SPZky
 *
 * @author hotov
 */
@Entity
@Table(name = "SPZ")
public class Spz implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idSpz;

  /**
   * Samotná SPZ
   */
  @Column(nullable = false, length = 20)
  private String spz;

  public Spz() {
  }

  public void setIdSpz(Long idSpz) {
    this.idSpz = idSpz;
  }

  public Long getIdSpz() {
    return idSpz;
  }

  public String getSpz() {
    return spz;
  }

  public void setSpz(String spz) {
    this.spz = spz;
  }

  @Override
  public String toString() {
    return "Spz{" +
            "idSpz=" + idSpz +
            ", spz='" + spz + '\'' +
            '}';
  }
}
