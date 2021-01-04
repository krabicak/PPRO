package cz.uhk.ppro.vehicle.registry.common.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Entity pro pojištění na určité vozidlo
 *
 * @author hotov
 */
@Entity
@Table(name = "INSURANCE")
public class Insurance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInsurance;

    /**
     * Pojištění od
     */
    @Column(nullable = false)
    private java.sql.Timestamp fromDate;

    /**
     * Pojištění do
     */
    @Column(nullable = false)
    private java.sql.Timestamp toDate;

    /**
     * Pojišťovna
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDINSURANCECOMPANY", nullable = false)
    private InsuranceCompany insuranceCompany;

    /**
     * Vozidlo na které se vztahuje pojištění
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDVEHICLE", nullable = false)
    private Vehicle vehicle;

    /**
     * Pojišťovák, který tohle vozidlo pojistil
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDINSURANCER", nullable = false)
    private InsuranceEmployee insurancer;

    /**
     * Člověk, který toto vozidlo pojistil
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPERSON", nullable = false)
    private Person person;

    public Insurance() {
    }

    public Long getIdInsurance() {
        return idInsurance;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public InsuranceEmployee getInsurancer() {
        return insurancer;
    }

    public void setInsurancer(InsuranceEmployee insurancer) {
        this.insurancer = insurancer;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "idInsurance=" + idInsurance +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", insuranceCompany=" + insuranceCompany +
                ", vehicle=" + vehicle +
                ", insurancer=" + insurancer +
                ", person=" + person +
                '}';
    }
}
