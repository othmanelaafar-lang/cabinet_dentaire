package ma.dentalTech.repository.modules.consultation;

import ma.dentalTech.entities.consultation.Consultation;
import ma.dentalTech.repository.common.CrudRepository;

public interface ConsultationRepository extends CrudRepository<Consultation, Long> {
    // Méthodes spécifiques à la Consultation peuvent être ajoutées ici
    // Par exemple :
    // List<Consultation> findByDateBetween(LocalDate startDate, LocalDate endDate);
    // List<Consultation> findByPatientId(Long patientId);
}
