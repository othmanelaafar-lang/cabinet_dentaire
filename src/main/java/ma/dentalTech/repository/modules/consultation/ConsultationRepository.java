package ma.dentalTech.repository.modules.consultation;

import ma.dentalTech.entities.consultation.Consultation;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ConsultationRepository extends CrudRepository<Consultation, Long> {
    // Trouver les consultations par date
    List<Consultation> findByDate(LocalDate date);
    
    // Trouver les consultations entre deux dates
    List<Consultation> findByDateRange(LocalDate dateDebut, LocalDate dateFin);
    
    // Trouver les consultations d'un patient (nécessite un champ patientId dans Consultation)
    List<Consultation> findByPatientId(Long patientId);
    
    // Trouver les consultations d'un médecin (nécessite un champ medecinId dans Consultation)
    List<Consultation> findByMedecinId(Long medecinId);
}
