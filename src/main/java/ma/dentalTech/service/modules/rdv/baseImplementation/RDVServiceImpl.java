package ma.dentalTech.service.modules.rdv.baseImplementation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.common.exceptions.ServiceException;
import ma.dentalTech.common.exceptions.ValidationException;
import ma.dentalTech.common.validation.Validators;
import ma.dentalTech.entities.enums.Statut;
import ma.dentalTech.entities.rdv.RDV;
import ma.dentalTech.repository.modules.rdv.RDVRepository;
import ma.dentalTech.service.modules.rdv.api.RDVService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RDVServiceImpl implements RDVService {

    private RDVRepository repository;

    /**
     * Valide les données d'un rendez-vous
     */
    private void validateRDV(RDV rdv) throws ServiceException {
        try {
            if (rdv.dateHeureDebut == null && rdv.Date == null) {
                throw new ValidationException("La date et l'heure du rendez-vous sont obligatoires");
            }
            
            if (rdv.patientId == null) {
                throw new ValidationException("L'ID du patient est obligatoire");
            }
            
            if (rdv.medecinId == null) {
                throw new ValidationException("L'ID du médecin est obligatoire");
            }
            
            LocalDate dateRDV = rdv.Date != null ? rdv.Date : (rdv.dateHeureDebut != null ? rdv.dateHeureDebut.toLocalDate() : null);
            if (dateRDV != null && dateRDV.isBefore(LocalDate.now())) {
                throw new ValidationException("La date du rendez-vous ne peut pas être dans le passé");
            }
            
            if (rdv.motifConsultation != null && rdv.motifConsultation.trim().length() < 3) {
                throw new ValidationException("Le motif doit contenir au moins 3 caractères");
            }
            
            if (rdv.motif != null && rdv.motif.trim().length() < 3) {
                throw new ValidationException("Le motif doit contenir au moins 3 caractères");
            }
        } catch (ValidationException e) {
            throw new ServiceException("Erreur de validation : " + e.getMessage(), e);
        }
    }

    @Override
    public List<RDV> findAll() {
        return repository.findAll();
    }

    @Override
    public RDV findById(Long id) {
        if (id == null) {
            return null;
        }
        return repository.findById(id).orElse(null);
    }

    @Override
    public void create(RDV rdv) throws ServiceException {
        if (rdv == null) {
            throw new ServiceException("Le rendez-vous ne peut pas être null");
        }
        
        validateRDV(rdv);
        
        // Vérifier la disponibilité du créneau
        LocalDate dateRDV = rdv.Date != null ? rdv.Date : (rdv.dateHeureDebut != null ? rdv.dateHeureDebut.toLocalDate() : LocalDate.now());
        LocalTime heureRDV = rdv.heure != null ? rdv.heure : (rdv.dateHeureDebut != null ? rdv.dateHeureDebut.toLocalTime() : null);
        if (heureRDV != null && !isCreneauDisponible(rdv.medecinId, dateRDV, heureRDV)) {
            throw new ServiceException("Ce créneau n'est pas disponible");
        }
        
        // Définir le statut par défaut
        if (rdv.statut == null) {
            rdv.statut = Statut.statut1; // PLANIFIE
        }
        
        try {
            repository.create(rdv);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création du rendez-vous : " + e.getMessage(), e);
        }
    }

    @Override
    public void update(RDV rdv) throws ServiceException {
        if (rdv == null) {
            throw new ServiceException("Le rendez-vous ne peut pas être null");
        }
        
        if (rdv.idRDV == null) {
            throw new ServiceException("L'ID du rendez-vous est requis pour la mise à jour");
        }
        
        RDV existing = repository.findById(rdv.idRDV).orElse(null);
        if (existing == null) {
            throw new ServiceException("Rendez-vous avec ID " + rdv.idRDV + " introuvable");
        }
        
        validateRDV(rdv);
        
        try {
            repository.update(rdv);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour du rendez-vous : " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("L'ID ne peut pas être null");
        }
        
        RDV rdv = repository.findById(id).orElse(null);
        if (rdv == null) {
            throw new ServiceException("Rendez-vous avec ID " + id + " introuvable");
        }
        
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression du rendez-vous : " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(RDV rdv) throws ServiceException {
        if (rdv == null) {
            throw new ServiceException("Le rendez-vous ne peut pas être null");
        }
        if (rdv.idRDV != null) {
            deleteById(rdv.idRDV);
        }
    }

    @Override
    public List<RDV> findByDate(LocalDate date) {
        if (date == null) {
            return List.of();
        }
        return repository.findByDate(date);
    }

    @Override
    public List<RDV> findByPatientId(Long patientId) {
        if (patientId == null) {
            return List.of();
        }
        return repository.findByPatientId(patientId);
    }

    @Override
    public List<RDV> findByMedecinId(Long medecinId) {
        if (medecinId == null) {
            return List.of();
        }
        return repository.findByMedecinId(medecinId);
    }

    @Override
    public boolean isCreneauDisponible(Long medecinId, LocalDate date, LocalTime heure) {
        if (date == null || heure == null) {
            return false;
        }
        
        // Vérifier s'il existe un RDV à cette date et heure
        if (repository.existsByDateAndHeure(date, heure)) {
            // Vérifier que le RDV n'est pas annulé
            List<RDV> rdvs = repository.findByDateAndHeure(date, heure);
            // Si tous les RDV à ce créneau sont annulés, le créneau est disponible
            return rdvs.stream()
                    .allMatch(rdv -> rdv.statut == Statut.statut3); // statut3 = ANNULE
        }
        return true; // Pas de RDV à cette date/heure = disponible
    }

    @Override
    public void annuler(Long id) throws ServiceException {
        RDV rdv = findById(id);
        if (rdv == null) {
            throw new ServiceException("Rendez-vous avec ID " + id + " introuvable");
        }
        
        rdv.statut = Statut.statut3; // ANNULE
        update(rdv);
    }

    @Override
    public void confirmer(Long id) throws ServiceException {
        RDV rdv = findById(id);
        if (rdv == null) {
            throw new ServiceException("Rendez-vous avec ID " + id + " introuvable");
        }
        
        rdv.statut = Statut.statut2; // CONFIRME
        update(rdv);
    }
}

