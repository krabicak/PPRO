package cz.uhk.ppro.vehicle.registry.common.repositories;

import cz.uhk.ppro.vehicle.registry.common.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {

    Document getDocumentByDocumentNumber(String documentNumber);
}
