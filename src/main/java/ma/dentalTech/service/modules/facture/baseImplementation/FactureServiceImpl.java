package ma.dentalTech.service.modules.facture.baseImplementation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.common.exceptions.ServiceException;
import ma.dentalTech.common.exceptions.ValidationException;
import ma.dentalTech.entities.enums.Statut;
import ma.dentalTech.entities.facture.Facture;
import ma.dentalTech.repository.modules.facture.FactureRepository;
import ma.dentalTech.service.modules.facture.api.FactureService;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureServiceImpl implements FactureService {

    private FactureRepository repository;

    /**
     * Valide les données d'une facture
     */
    private void validateFacture(Facture facture) throws ServiceException {
        try {
            if (facture.totalepaye < 0) {
                throw new ValidationException("Le montant payé ne peut pas être négatif");
            }
            
            if (facture.Reste < 0) {
                throw new ValidationException("Le reste à payer ne peut pas être négatif");
            }
        } catch (ValidationException e) {
            throw new ServiceException("Erreur de validation : " + e.getMessage(), e);
        }
    }

    @Override
    public List<Facture> findAll() {
        return repository.findAll();
    }

    @Override
    public Facture findById(Long id) {
        if (id == null) {
            return null;
        }
        return repository.findById(id);
    }

    @Override
    public void create(Facture facture) throws ServiceException {
        if (facture == null) {
            throw new ServiceException("La facture ne peut pas être null");
        }
        
        validateFacture(facture);
        
        // Définir la date par défaut
        if (facture.dateFacture == null) {
            facture.dateFacture = LocalDateTime.now();
        }
        
        // Définir le statut par défaut
        if (facture.statut == null) {
            facture.statut = Statut.statut1; // EN_ATTENTE
        }
        
        // Calculer le reste si nécessaire
        if (facture.Reste == 0 && facture.totalepaye > 0) {
            // Le reste sera calculé en fonction du total de la consultation
            // Pour l'instant, on laisse tel quel
        }
        
        try {
            repository.create(facture);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création de la facture : " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Facture facture) throws ServiceException {
        if (facture == null) {
            throw new ServiceException("La facture ne peut pas être null");
        }
        
        if (facture.idFacture == 0) {
            throw new ServiceException("L'ID de la facture est requis pour la mise à jour");
        }
        
        Facture existing = repository.findById(facture.idFacture);
        if (existing == null) {
            throw new ServiceException("Facture avec ID " + facture.idFacture + " introuvable");
        }
        
        validateFacture(facture);
        
        try {
            repository.update(facture);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour de la facture : " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("L'ID ne peut pas être null");
        }
        
        Facture facture = repository.findById(id);
        if (facture == null) {
            throw new ServiceException("Facture avec ID " + id + " introuvable");
        }
        
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression de la facture : " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Facture facture) throws ServiceException {
        if (facture == null) {
            throw new ServiceException("La facture ne peut pas être null");
        }
        deleteById(facture.idFacture);
    }

    @Override
    public List<Facture> findByPatientId(Long patientId) {
        if (patientId == null) {
            return List.of();
        }
        return repository.findByPatientId(patientId);
    }

    @Override
    public List<Facture> findByDateRange(LocalDateTime dateDebut, LocalDateTime dateFin) {
        if (dateDebut == null || dateFin == null) {
            return List.of();
        }
        if (dateDebut.isAfter(dateFin)) {
            return List.of();
        }
        return repository.findByDateRange(dateDebut, dateFin);
    }

    @Override
    public List<Facture> findByStatut(Statut statut) {
        if (statut == null) {
            return List.of();
        }
        return repository.findByStatut(statut);
    }

    @Override
    public double getTotalPayeByPatient(Long patientId) {
        if (patientId == null) {
            return 0.0;
        }
        return findByPatientId(patientId).stream()
                .mapToDouble(f -> f.totalepaye)
                .sum();
    }

    @Override
    public double getResteAPayerByPatient(Long patientId) {
        if (patientId == null) {
            return 0.0;
        }
        return findByPatientId(patientId).stream()
                .mapToDouble(f -> f.Reste)
                .sum();
    }

    @Override
    public void enregistrerPaiement(Long factureId, double montant) throws ServiceException {
        if (factureId == null) {
            throw new ServiceException("L'ID de la facture ne peut pas être null");
        }
        
        if (montant <= 0) {
            throw new ServiceException("Le montant doit être positif");
        }
        
        Facture facture = findById(factureId);
        if (facture == null) {
            throw new ServiceException("Facture avec ID " + factureId + " introuvable");
        }
        
        facture.totalepaye += montant;
        facture.Reste = Math.max(0, facture.Reste - montant);
        
        // Si le reste est à 0, marquer comme payée
        if (facture.Reste == 0) {
            facture.statut = Statut.statut2; // PAYEE
        }
        
        update(facture);
    }

    @Override
    public void marquerCommePayee(Long id) throws ServiceException {
        Facture facture = findById(id);
        if (facture == null) {
            throw new ServiceException("Facture avec ID " + id + " introuvable");
        }
        
        facture.statut = Statut.statut2; // PAYEE
        facture.Reste = 0;
        update(facture);
    }
}

