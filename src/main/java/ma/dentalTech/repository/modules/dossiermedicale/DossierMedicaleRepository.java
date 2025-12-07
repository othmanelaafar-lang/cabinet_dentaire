package ma.dentalTech.repository.modules.dossiermedicale;

import ma.dentalTech.entities.dossiermedicale.DossierMedicale;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface DossierMedicaleRepository extends CrudRepository<DossierMedicale, Long> {
    // Trouver les dossiers médicaux créés à une date spécifique
    List<DossierMedicale> findByDateDeCreation(LocalDate date);
    
    // Trouver les dossiers médicaux créés entre deux dates
    List<DossierMedicale> findByDateDeCreationBetween(LocalDate startDate, LocalDate endDate);
    
    // Vérifier si un dossier médical existe déjà pour un patient (à implémenter avec la relation)
    // boolean existsByPatientId(Long patientId);
}
