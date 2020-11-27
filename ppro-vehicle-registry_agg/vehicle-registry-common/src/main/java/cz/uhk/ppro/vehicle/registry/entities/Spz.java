package cz.uhk.ppro.vehicle.registry.entities;

import javax.persistence.*;

@Entity
@Table(name = "SPZ")
public class Spz {

  @Id
  @Column(nullable = false)
  @GeneratedValue
  private long idSpz;

  @Column(nullable = false, length = 20)
  private String spz;

  public long getIdSpz() {
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
