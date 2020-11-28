package cz.uhk.ppro.vehicle.registry.common.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Entita pro Dokumenty (TP atd.)
 *
 * @author hotov
 */
@Entity
@Table(name = "DOCUMENT")
public class Document implements Serializable {

  @Id
  @Column(nullable = false)
  @GeneratedValue
  private Long idDocument;

  /**
   * Identifikát dokumentu
   */
  @Column(nullable = false, length = 45)
  private String documentNumber;

  /**
   * Datum splatnosti dokumentu
   * nullable
   */
  private Timestamp toDate;

  public Document() {
  }

  public long getIdDocument() {
    return idDocument;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }

  public void setDocumentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  public Timestamp getToDate() {
    return toDate;
  }

  public void setToDate(Timestamp toDate) {
    this.toDate = toDate;
  }

  @Override
  public String toString() {
    return "Document{" +
            "idDocument=" + idDocument +
            ", documentNumber='" + documentNumber + '\'' +
            ", toDate=" + toDate +
            '}';
  }
}