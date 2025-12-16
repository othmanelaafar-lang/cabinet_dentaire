package ma.dentalTech.repository.modules.documentmedical;

import ma.dentalTech.entities.documentmedical.DocumentMedical;
import ma.dentalTech.repository.common.CrudRepository;

import java.util.List;

public interface DocumentMedicalRepository extends CrudRepository<DocumentMedical, Long> {
    // Trouver tous les documents d'un patient
    List<DocumentMedical> findByPatientId(Long patientId);
    
    // Trouver les documents par type
    List<DocumentMedical> findByTypeDocument(String typeDocument);
    
    // Trouver les documents d'un patient par type
    List<DocumentMedical> findByPatientIdAndTypeDocument(Long patientId, String typeDocument);
    
    // Trouver les documents uploadés par un utilisateur
    List<DocumentMedical> findByUploadPar(Long uploadPar);
}

