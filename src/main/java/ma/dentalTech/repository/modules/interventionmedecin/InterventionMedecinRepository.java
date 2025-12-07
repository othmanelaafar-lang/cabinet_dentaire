package ma.dentalTech.repository.modules.interventionmedecin;

import ma.dentalTech.entities.interventionmedecin.InterventionMedecin;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InterventionMedecinRepository extends CrudRepository<InterventionMedecin, Long> {
    // Trouver les interventions par date
    List<InterventionMedecin> findByDateIntervention(LocalDateTime date);
    
    // Trouver les interventions entre deux dates
    List<InterventionMedecin> findByDateInterventionBetween(LocalDateTime debut, LocalDateTime fin);
    
    // Trouver les interventions d'un médecin spécifique
    List<InterventionMedecin> findByMedecinId(Long medecinId);
    
    // Trouver les interventions pour un patient spécifique
    List<InterventionMedecin> findByPatientId(Long patientId);
    
    // Compter le nombre d'interventions par type
    long countByTypeIntervention(String typeIntervention);
    
    // Vérifier si une intervention existe déjà à cette date et heure pour ce médecin
    boolean existsByDateInterventionAndMedecinId(LocalDateTime dateIntervention, Long medecinId);
}
