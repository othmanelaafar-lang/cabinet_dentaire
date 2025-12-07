package ma.dentalTech.service.modules.ordonnance.baseImplementation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.common.exceptions.ServiceException;
import ma.dentalTech.common.exceptions.ValidationException;
import ma.dentalTech.entities.ordonnance.Ordonnance;
import ma.dentalTech.repository.modules.ordonnance.OrdonnanceRepository;
import ma.dentalTech.service.modules.ordonnance.api.OrdonnanceService;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdonnanceServiceImpl implements OrdonnanceService {

    private OrdonnanceRepository repository;

    /**
     * Valide les données d'une ordonnance
     */
    private void validateOrdonnance(Ordonnance ordonnance) throws ServiceException {
        try {
            // Validation basique - peut être étendue selon les besoins
            if (ordonnance.date == 0 && ordonnance.LocalDate == 0) {
                throw new ValidationException("La date de l'ordonnance est obligatoire");
            }
        } catch (ValidationException e) {
            throw new ServiceException("Erreur de validation : " + e.getMessage(), e);
        }
    }

    @Override
    public List<Ordonnance> findAll() {
        return repository.findAll();
    }

    @Override
    public Ordonnance findById(Long id) {
        if (id == null) {
            return null;
        }
        return repository.findById(id);
    }

    @Override
    public void create(Ordonnance ordonnance) throws ServiceException {
        if (ordonnance == null) {
            throw new ServiceException("L'ordonnance ne peut pas être null");
        }
        
        validateOrdonnance(ordonnance);
        
        try {
            repository.create(ordonnance);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création de l'ordonnance : " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Ordonnance ordonnance) throws ServiceException {
        if (ordonnance == null) {
            throw new ServiceException("L'ordonnance ne peut pas être null");
        }
        
        if (ordonnance.idOrd == 0) {
            throw new ServiceException("L'ID de l'ordonnance est requis pour la mise à jour");
        }
        
        Ordonnance existing = repository.findById(ordonnance.idOrd);
        if (existing == null) {
            throw new ServiceException("Ordonnance avec ID " + ordonnance.idOrd + " introuvable");
        }
        
        validateOrdonnance(ordonnance);
        
        try {
            repository.update(ordonnance);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour de l'ordonnance : " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("L'ID ne peut pas être null");
        }
        
        Ordonnance ordonnance = repository.findById(id);
        if (ordonnance == null) {
            throw new ServiceException("Ordonnance avec ID " + id + " introuvable");
        }
        
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression de l'ordonnance : " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Ordonnance ordonnance) throws ServiceException {
        if (ordonnance == null) {
            throw new ServiceException("L'ordonnance ne peut pas être null");
        }
        deleteById(ordonnance.idOrd);
    }

    @Override
    public List<Ordonnance> findByConsultationId(Long consultationId) {
        if (consultationId == null) {
            return List.of();
        }
        return repository.findByConsultationId(consultationId);
    }

    @Override
    public List<Ordonnance> findByDate(LocalDate date) {
        if (date == null) {
            return List.of();
        }
        return repository.findByDate(date);
    }

    @Override
    public List<Ordonnance> findByDateRange(LocalDate dateDebut, LocalDate dateFin) {
        if (dateDebut == null || dateFin == null) {
            return List.of();
        }
        if (dateDebut.isAfter(dateFin)) {
            return List.of();
        }
        return repository.findByDateRange(dateDebut, dateFin);
    }
}

