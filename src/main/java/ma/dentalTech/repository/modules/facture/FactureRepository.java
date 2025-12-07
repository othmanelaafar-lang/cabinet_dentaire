package ma.dentalTech.repository.modules.facture;

import ma.dentalTech.entities.facture.Facture;
import ma.dentalTech.entities.enums.Statut;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FactureRepository extends CrudRepository<Facture, Long> {
    // Trouver les factures entre deux dates
    List<Facture> findByDateRange(LocalDateTime dateDebut, LocalDateTime dateFin);
    
    // Trouver les factures d'un patient (nécessite un champ patientId dans Facture)
    List<Facture> findByPatientId(Long patientId);
    
    // Trouver les factures par statut
    List<Facture> findByStatut(Statut statut);
}
