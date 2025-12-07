package ma.dentalTech.service.modules.patient.baseImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.common.exceptions.ServiceException;
import ma.dentalTech.common.exceptions.ValidationException;
import ma.dentalTech.common.validation.Validators;
import ma.dentalTech.entities.patient.Patient;
import ma.dentalTech.mvc.dto.PatientDTO;
import ma.dentalTech.repository.modules.patient.api.PatientRepository;
import ma.dentalTech.service.modules.patient.api.PatientService;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository repository;


    /**
     * Formattage de date
     * @param dt : date Non Formatée
     * @return  date formatée
     */
    private static String formatDate(java.time.LocalDateTime dt) {
        if (dt == null) return "";
        return dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Calculer l'âge du patient à partir de sa date de naissance
     * @param birthDate
     * @return age
     */
    private static int computeAge(java.time.LocalDate birthDate) {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    /**
     * Valide les données d'un patient avant création ou mise à jour
     * @param patient le patient à valider
     * @throws ServiceException si la validation échoue
     */
    private void validatePatient(Patient patient) throws ServiceException {
        try {
            Validators.notBlank(patient.getNom(), "Le nom");
            Validators.notBlank(patient.getPrenom(), "Le prénom");
            Validators.minLen(patient.getNom(), 2, "Le nom");
            Validators.minLen(patient.getPrenom(), 2, "Le prénom");
            
            if (patient.getEmail() != null && !patient.getEmail().trim().isEmpty()) {
                Validators.email(patient.getEmail());
            }
            
            if (patient.getTelephone() != null && !patient.getTelephone().trim().isEmpty()) {
                Validators.phone(patient.getTelephone());
            }
            
            // Validation de la date de naissance (ne doit pas être dans le futur)
            if (patient.getDateNaissance() != null && patient.getDateNaissance().isAfter(LocalDate.now())) {
                throw new ValidationException("La date de naissance ne peut pas être dans le futur");
            }
        } catch (ValidationException e) {
            throw new ServiceException("Erreur de validation : " + e.getMessage(), e);
        }
    }

    @Override
    public List<PatientDTO> getTodayPatientsAsDTO() {
        LocalDate today = LocalDate.now();
        return repository.findAll().stream()
                .filter(p -> p.getDateCreation() != null && p.getDateCreation().toLocalDate().equals(today))
                .sorted(Comparator.comparing(Patient::getDateCreation).reversed())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDTO> getAllPatientsAsDTO() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Patient::getDateCreation, Comparator.nullsLast(Comparator.reverseOrder())))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Patient findById(Long id) {
        if (id == null) {
            return null;
        }
        return repository.findById(id);
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll();
    }

    @Override
    public void create(Patient patient) throws ServiceException {
        if (patient == null) {
            throw new ServiceException("Le patient ne peut pas être null");
        }
        
        validatePatient(patient);
        
        // Définir la date de création si elle n'est pas déjà définie
        if (patient.getDateCreation() == null) {
            patient.setDateCreation(LocalDateTime.now());
        }
        
        try {
            repository.create(patient);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création du patient : " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Patient patient) throws ServiceException {
        if (patient == null) {
            throw new ServiceException("Le patient ne peut pas être null");
        }
        
        if (patient.getId() == null) {
            throw new ServiceException("L'ID du patient est requis pour la mise à jour");
        }
        
        // Vérifier que le patient existe
        Patient existing = repository.findById(patient.getId());
        if (existing == null) {
            throw new ServiceException("Patient avec ID " + patient.getId() + " introuvable");
        }
        
        validatePatient(patient);
        
        // Préserver la date de création originale
        patient.setDateCreation(existing.getDateCreation());
        
        try {
            repository.update(patient);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour du patient : " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("L'ID ne peut pas être null");
        }
        
        Patient patient = repository.findById(id);
        if (patient == null) {
            throw new ServiceException("Patient avec ID " + id + " introuvable");
        }
        
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression du patient : " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Patient patient) throws ServiceException {
        if (patient == null) {
            throw new ServiceException("Le patient ne peut pas être null");
        }
        
        deleteById(patient.getId());
    }

    @Override
    public List<Patient> findByNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            return List.of();
        }
        
        String searchTerm = nom.trim().toLowerCase();
        return repository.findAll().stream()
                .filter(p -> p.getNom() != null && p.getNom().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findByPrenom(String prenom) {
        if (prenom == null || prenom.trim().isEmpty()) {
            return List.of();
        }
        
        String searchTerm = prenom.trim().toLowerCase();
        return repository.findAll().stream()
                .filter(p -> p.getPrenom() != null && p.getPrenom().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> search(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return repository.findAll();
        }
        
        String term = searchTerm.trim().toLowerCase();
        return repository.findAll().stream()
                .filter(p -> {
                    boolean matchNom = p.getNom() != null && p.getNom().toLowerCase().contains(term);
                    boolean matchPrenom = p.getPrenom() != null && p.getPrenom().toLowerCase().contains(term);
                    boolean matchEmail = p.getEmail() != null && p.getEmail().toLowerCase().contains(term);
                    boolean matchTelephone = p.getTelephone() != null && p.getTelephone().contains(term);
                    return matchNom || matchPrenom || matchEmail || matchTelephone;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDTO> searchAsDTO(String searchTerm) {
        return search(searchTerm).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO toDTO(Patient patient) {
        if (patient == null) {
            return null;
        }
        
        return PatientDTO.builder()
                .nomComplet((patient.getNom() == null ? "" : patient.getNom().trim()) + " " + 
                           (patient.getPrenom() == null ? "" : patient.getPrenom().trim()))
                .age(computeAge(patient.getDateNaissance()))
                .dateCreationFormatee(formatDate(patient.getDateCreation()))
                .build();
    }

    @Override
    public List<PatientDTO> toDTOList(List<Patient> patients) {
        if (patients == null) {
            return List.of();
        }
        
        return patients.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
