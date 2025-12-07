package ma.dentalTech.repository.modules.facture;

import ma.dentalTech.entities.facture.Facture;
import ma.dentalTech.repository.common.CrudRepository;

public interface FactureRepository extends CrudRepository<Facture, Long> {
    // Méthodes spécifiques à la Facture peuvent être ajoutées ici
    // Par exemple :
    // List<Facture> findByDateBetween(LocalDate debut, LocalDate fin);
    // List<Facture> findByPatientId(Long patientId);
    // List<Facture> findByStatutPaiement(StatutPaiement statut);
}
