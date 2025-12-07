package ma.dentalTech.service.modules.consultation.baseImplementation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.common.exceptions.ServiceException;
import ma.dentalTech.common.exceptions.ValidationException;
import ma.dentalTech.entities.consultation.Consultation;
import ma.dentalTech.entities.enums.Statut;
import ma.dentalTech.repository.modules.consultation.ConsultationRepository;
import ma.dentalTech.service.modules.consultation.api.ConsultationService;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private ConsultationRepository repository;

    /**
     * Valide les données d'une consultation
     */
    private void validateConsultation(Consultation consultation) throws ServiceException {
        try {
            if (consultation.Date == null) {
                throw new ValidationException("La date de consultation est obligatoire");
            }
            
            // La date ne doit pas être dans le futur
            if (consultation.Date.isAfter(LocalDate.now())) {
                throw new ValidationException("La date de consultation ne peut pas être dans le futur");
            }
        } catch (ValidationException e) {
            throw new ServiceException("Erreur de validation : " + e.getMessage(), e);
        }
    }

    @Override
    public List<Consultation> findAll() {
        return repository.findAll();
    }

    @Override
    public Consultation findById(Long id) {
        if (id == null) {
            return null;
        }
        return repository.findById(id);
    }

    @Override
    public void create(Consultation consultation) throws ServiceException {
        if (consultation == null) {
            throw new ServiceException("La consultation ne peut pas être null");
        }
        
        validateConsultation(consultation);
        
        // Définir le statut par défaut
        if (consultation.statut == null) {
            consultation.statut = Statut.statut1; // EN_COURS
        }
        
        try {
            repository.create(consultation);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création de la consultation : " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Consultation consultation) throws ServiceException {
        if (consultation == null) {
            throw new ServiceException("La consultation ne peut pas être null");
        }
        
        if (consultation.idConsultation == 0) {
            throw new ServiceException("L'ID de la consultation est requis pour la mise à jour");
        }
        
        Consultation existing = repository.findById(consultation.idConsultation);
        if (existing == null) {
            throw new ServiceException("Consultation avec ID " + consultation.idConsultation + " introuvable");
        }
        
        validateConsultation(consultation);
        
        try {
            repository.update(consultation);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour de la consultation : " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("L'ID ne peut pas être null");
        }
        
        Consultation consultation = repository.findById(id);
        if (consultation == null) {
            throw new ServiceException("Consultation avec ID " + id + " introuvable");
        }
        
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression de la consultation : " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Consultation consultation) throws ServiceException {
        if (consultation == null) {
            throw new ServiceException("La consultation ne peut pas être null");
        }
        deleteById(consultation.idConsultation);
    }

    @Override
    public List<Consultation> findByPatientId(Long patientId) {
        if (patientId == null) {
            return List.of();
        }
        // Le repository n'a pas cette méthode, on filtre depuis findAll
        // Note: nécessite que l'entité Consultation ait un champ patientId
        return repository.findAll().stream()
                .filter(cons -> cons.idConsultation != 0) // Placeholder - adapter selon la structure réelle
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<Consultation> findByMedecinId(Long medecinId) {
        if (medecinId == null) {
            return List.of();
        }
        // Le repository n'a pas cette méthode, on filtre depuis findAll
        // Note: nécessite que l'entité Consultation ait un champ medecinId
        return repository.findAll().stream()
                .filter(cons -> cons.idConsultation != 0) // Placeholder - adapter selon la structure réelle
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<Consultation> findByDate(LocalDate date) {
        if (date == null) {
            return List.of();
        }
        // Le repository n'a pas cette méthode, on filtre depuis findAll
        return repository.findAll().stream()
                .filter(cons -> cons.Date != null && cons.Date.equals(date))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<Consultation> findByDateRange(LocalDate dateDebut, LocalDate dateFin) {
        if (dateDebut == null || dateFin == null) {
            return List.of();
        }
        if (dateDebut.isAfter(dateFin)) {
            return List.of();
        }
        // Le repository n'a pas cette méthode, on filtre depuis findAll
        return repository.findAll().stream()
                .filter(cons -> cons.Date != null && 
                               !cons.Date.isBefore(dateDebut) && 
                               !cons.Date.isAfter(dateFin))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void terminer(Long id) throws ServiceException {
        Consultation consultation = findById(id);
        if (consultation == null) {
            throw new ServiceException("Consultation avec ID " + id + " introuvable");
        }
        
        consultation.statut = Statut.statut2; // TERMINE
        update(consultation);
    }
}

