package cz.uhk.ppro.vehicle.registry.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Entita pro Dokumenty (TP atd.)
 *
 * @author hotov
 */
@Entity
@Table(name = "DOCUMENT")
public class Document {

  @Id
  @Column(nullable = false)
  @GeneratedValue
  private long idDocument;

  /**
   * Identifikát dokumentu
   */
  @Column(nullable = false, length = 45)
  private String documentNumber;

  /**
   * Datum splatnosti dokumentu
   * nullable
   */
  private java.sql.Timestamp toDate;

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
