package cz.uhk.ppro.vehicle.registry.entities;

import javax.persistence.*;

@Entity
@Table(name = "VIN")
public class Vin {

  @Id
  @Column(nullable = false)
  @GeneratedValue
  private long idvin;

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
