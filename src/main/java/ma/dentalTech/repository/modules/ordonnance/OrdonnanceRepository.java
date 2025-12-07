package ma.dentalTech.repository.modules.ordonnance;

import ma.dentalTech.entities.ordonnance.Ordonnance;
import ma.dentalTech.entities.enums.Statut;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdonnanceRepository extends CrudRepository<Ordonnance, Long> {
    // Trouver les ordonnances par date de prescription
    List<Ordonnance> findByDatePrescription(LocalDate date);
    
    // Trouver les ordonnances entre deux dates
    List<Ordonnance> findByDatePrescriptionBetween(LocalDate debut, LocalDate fin);
    
    // Trouver les ordonnances d'un patient spécifique
    List<Ordonnance> findByPatientId(Long patientId);
    
    // Trouver les ordonnances par statut (validée, en attente, etc.)
    List<Ordonnance> findByStatut(Statut statut);
    
    // Trouver les ordonnances par médecin prescripteur
    List<Ordonnance> findByMedecinId(Long medecinId);
    
    // Vérifier si une ordonnance existe déjà pour une consultation
    boolean existsByConsultationId(Long consultationId);
    
    // Compter le nombre d'ordonnances par statut
    long countByStatut(Statut statut);
}
