package ma.dentalTech.repository.modules.certificat;

import ma.dentalTech.entities.certificat.Certificat;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CertificatRepository extends CrudRepository<Certificat, Long> {
    // Trouver les certificats par date d'émission
    List<Certificat> findByDateEmission(LocalDate date);
    
    // Trouver les certificats entre deux dates
    List<Certificat> findByDateEmissionBetween(LocalDate debut, LocalDate fin);
    
    // Trouver les certificats par type
    List<Certificat> findByType(String type);
    
    // Trouver les certificats d'un patient spécifique
    List<Certificat> findByPatientId(Long patientId);
    
    // Vérifier si un certificat existe déjà pour une consultation
    boolean existsByConsultationId(Long consultationId);
    
    // Compter le nombre de certificats par type
    long countByType(String type);
}
