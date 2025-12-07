package ma.dentalTech.repository.modules.prescription;

import ma.dentalTech.entities.prescription.Prescription;
import ma.dentalTech.entities.enums.Statut;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    // Trouver les prescriptions par date
    List<Prescription> findByDatePrescription(LocalDate date);
    
    // Trouver les prescriptions entre deux dates
    List<Prescription> findByDatePrescriptionBetween(LocalDate debut, LocalDate fin);
    
    // Trouver les prescriptions d'un patient spécifique
    List<Prescription> findByPatientId(Long patientId);
    
    // Trouver les prescriptions par statut
    List<Prescription> findByStatut(Statut statut);
    
    // Trouver les prescriptions par médecin prescripteur
    List<Prescription> findByMedecinId(Long medecinId);
    
    // Vérifier si une prescription existe déjà pour une consultation
    boolean existsByConsultationId(Long consultationId);
    
    // Compter le nombre de prescriptions par statut
    long countByStatut(Statut statut);
    
    // Trouver les prescriptions contenant un médicament spécifique
    List<Prescription> findByMedicamentsContaining(String medicament);
}
