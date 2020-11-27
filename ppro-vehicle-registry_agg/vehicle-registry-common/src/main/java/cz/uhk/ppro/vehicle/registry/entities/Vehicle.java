package cz.uhk.ppro.vehicle.registry.entities;

import cz.uhk.ppro.vehicle.registry.converters.BooleanConverter;

import javax.persistence.*;

/**
 * Entita vozidla
 *
 * @author hotov
 */
@Entity
@Table(name = "VEHICLE")
public class Vehicle {

  @Id
  @Column(nullable = false)
  @GeneratedValue
  private long idVehicle;

  /**
   * SPZ ze smětiště
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDSPZ")
  private Spz spz;

  /**
   * VIN ze smetiště
   */
  @Column(nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDVIN")
  private Vin vin;

  /**
   * Malý TP
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDSTECHNICALCERT")
  private Document sTechnicalCert;

  /**
   * velký TP
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDBTECHNICALCERT")
  private Document bTechnicalCert;

  /**
   * Majitel vozidla
   */
  @Column(nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IDOWNER")
  private Person owner;

  /**
   * Je vozidlo v provozu?
   */
  @Column(length = 1, nullable = false)
  @Convert(converter = BooleanConverter.class)
  private boolean active;

  public long getIdVehicle() {
    return idVehicle;
  }

  public Spz getSpz() {
    return spz;
  }

  public void setSpz(Spz spz) {
    this.spz = spz;
  }

  public Vin getVin() {
    return vin;
  }

  public void setVin(Vin vin) {
    this.vin = vin;
  }

  public Document getsTechnicalCert() {
    return sTechnicalCert;
  }

  public void setsTechnicalCert(Document sTechnicalCert) {
    this.sTechnicalCert = sTechnicalCert;
  }

  public Document getbTechnicalCert() {
    return bTechnicalCert;
  }

  public void setbTechnicalCert(Document bTechnicalCert) {
    this.bTechnicalCert = bTechnicalCert;
  }

  public Person getOwner() {
    return owner;
  }

  public void setOwner(Person owner) {
    this.owner = owner;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
            "idVehicle=" + idVehicle +
            ", spz=" + spz +
            ", vin=" + vin +
            ", sTechnicalCert=" + sTechnicalCert +
            ", bTechnicalCert=" + bTechnicalCert +
            ", owner=" + owner +
            ", active=" + active +
            '}';
  }
}
