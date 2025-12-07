package ma.dentalTech.repository.modules.patient;

import ma.dentalTech.entities.patient.Patient;
import ma.dentalTech.entities.enums.Sexe;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    // Trouver un patient par son email
    Optional<Patient> findByEmail(String email);
    
    // Trouver les patients par nom (recherche insensible à la casse)
    List<Patient> findByNomContainingIgnoreCase(String nom);
    
    // Trouver les patients par numéro de téléphone
    List<Patient> findByTelephone(String telephone);
    
    // Vérifier si un patient existe avec cet email
    boolean existsByEmail(String email);
    
    // Trouver les patients par ville
    List<Patient> findByVille(String ville);
    
    // Trouver les patients par sexe
    List<Patient> findBySexe(Sexe sexe);
    
    // Trouver les patients par tranche d'âge
    List<Patient> findByDateNaissanceBetween(LocalDate debut, LocalDate fin);
    
    // Compter le nombre de patients par ville
    long countByVille(String ville);
    
    // Mettre à jour l'email d'un patient
    void updateEmail(Long id, String nouveauEmail);
    
    // Mettre à jour le numéro de téléphone d'un patient
    void updateTelephone(Long id, String nouveauTelephone);
}
