package ma.dentalTech.service.modules.patient.api;


import java.util.List;
import ma.dentalTech.entities.patient.Patient;
import ma.dentalTech.mvc.dto.PatientDTO;

public interface PatientService {

    /**
     * Récupère les patients ajoutés aujourd'hui,
     * triés par ordre de création (plus récent -> plus ancien),
     * et les expose sous forme de PatientDTO (nom complet, âge, date formatée).
     */
    List<PatientDTO> getTodayPatientsAsDTO();

    /**
     * Récupère tous les patients et les convertit en DTO
     */
    List<PatientDTO> getAllPatientsAsDTO();

    /**
     * Récupère un patient par son ID
     * @param id l'identifiant du patient
     * @return le patient trouvé ou null
     */
    Patient findById(Long id);

    /**
     * Récupère tous les patients
     * @return la liste de tous les patients
     */
    List<Patient> findAll();

    /**
     * Crée un nouveau patient avec validation
     * @param patient le patient à créer
     * @throws ma.dentalTech.common.exceptions.ServiceException si la validation échoue
     */
    void create(Patient patient) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Met à jour un patient existant avec validation
     * @param patient le patient à mettre à jour
     * @throws ma.dentalTech.common.exceptions.ServiceException si la validation échoue ou si le patient n'existe pas
     */
    void update(Patient patient) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime un patient par son ID
     * @param id l'identifiant du patient à supprimer
     * @throws ma.dentalTech.common.exceptions.ServiceException si le patient n'existe pas
     */
    void deleteById(Long id) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime un patient
     * @param patient le patient à supprimer
     * @throws ma.dentalTech.common.exceptions.ServiceException si le patient n'existe pas
     */
    void delete(Patient patient) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Recherche des patients par nom
     * @param nom le nom à rechercher (insensible à la casse)
     * @return la liste des patients correspondants
     */
    List<Patient> findByNom(String nom);

    /**
     * Recherche des patients par prénom
     * @param prenom le prénom à rechercher (insensible à la casse)
     * @return la liste des patients correspondants
     */
    List<Patient> findByPrenom(String prenom);

    /**
     * Recherche des patients par nom ou prénom
     * @param searchTerm le terme de recherche
     * @return la liste des patients correspondants
     */
    List<Patient> search(String searchTerm);

    /**
     * Recherche des patients et les convertit en DTO
     * @param searchTerm le terme de recherche
     * @return la liste des DTO correspondants
     */
    List<PatientDTO> searchAsDTO(String searchTerm);

    /**
     * Convertit un Patient en PatientDTO
     * @param patient le patient à convertir
     * @return le DTO correspondant
     */
    PatientDTO toDTO(Patient patient);

    /**
     * Convertit une liste de Patients en liste de PatientDTO
     * @param patients la liste de patients à convertir
     * @return la liste de DTO correspondants
     */
    List<PatientDTO> toDTOList(List<Patient> patients);
}
