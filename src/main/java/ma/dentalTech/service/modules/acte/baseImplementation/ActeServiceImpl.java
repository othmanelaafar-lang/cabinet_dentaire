package ma.dentalTech.service.modules.acte.baseImplementation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.common.exceptions.ServiceException;
import ma.dentalTech.common.exceptions.ValidationException;
import ma.dentalTech.common.validation.Validators;
import ma.dentalTech.entities.acte.Acte;
import ma.dentalTech.repository.modules.acte.ActeRepository;
import ma.dentalTech.service.modules.acte.api.ActeService;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActeServiceImpl implements ActeService {

    private ActeRepository repository;

    /**
     * Valide les données d'un acte
     */
    private void validateActe(Acte acte) throws ServiceException {
        try {
            Validators.notBlank(acte.libelle, "Le libellé");
            Validators.minLen(acte.libelle, 3, "Le libellé");
            
            if (acte.prixDeBase < 0) {
                throw new ValidationException("Le prix de base ne peut pas être négatif");
            }
        } catch (ValidationException e) {
            throw new ServiceException("Erreur de validation : " + e.getMessage(), e);
        }
    }

    @Override
    public List<Acte> findAll() {
        return repository.findAll();
    }

    @Override
    public Acte findById(Long id) {
        if (id == null) {
            return null;
        }
        return repository.findById(id);
    }

    @Override
    public void create(Acte acte) throws ServiceException {
        if (acte == null) {
            throw new ServiceException("L'acte ne peut pas être null");
        }
        
        validateActe(acte);
        
        try {
            repository.create(acte);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création de l'acte : " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Acte acte) throws ServiceException {
        if (acte == null) {
            throw new ServiceException("L'acte ne peut pas être null");
        }
        
        if (acte.idActe == 0) {
            throw new ServiceException("L'ID de l'acte est requis pour la mise à jour");
        }
        
        Acte existing = repository.findById(acte.idActe);
        if (existing == null) {
            throw new ServiceException("Acte avec ID " + acte.idActe + " introuvable");
        }
        
        validateActe(acte);
        
        try {
            repository.update(acte);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour de l'acte : " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("L'ID ne peut pas être null");
        }
        
        Acte acte = repository.findById(id);
        if (acte == null) {
            throw new ServiceException("Acte avec ID " + id + " introuvable");
        }
        
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression de l'acte : " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Acte acte) throws ServiceException {
        if (acte == null) {
            throw new ServiceException("L'acte ne peut pas être null");
        }
        deleteById(acte.idActe);
    }

    @Override
    public List<Acte> findByCategorie(String categorie) {
        if (categorie == null || categorie.trim().isEmpty()) {
            return List.of();
        }
        return repository.findByCategorie(categorie.trim());
    }

    @Override
    public List<Acte> findByLibelle(String libelle) {
        if (libelle == null || libelle.trim().isEmpty()) {
            return List.of();
        }
        // Recherche dans tous les actes par libellé
        return repository.findAll().stream()
                .filter(acte -> acte.libelle != null && acte.libelle.toLowerCase().contains(libelle.trim().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Acte> search(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return repository.findAll();
        }
        
        String term = searchTerm.trim().toLowerCase();
        return repository.findAll().stream()
                .filter(acte -> {
                    boolean matchLibelle = acte.libelle != null && acte.libelle.toLowerCase().contains(term);
                    boolean matchCategorie = acte.categorie != null && acte.categorie.toLowerCase().contains(term);
                    return matchLibelle || matchCategorie;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Acte> findByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return List.of();
        }
        return repository.findByCode(code.trim());
    }

    @Override
    public List<Acte> findByEnPromo(ma.dentalTech.entities.enums.enPromo enPromo) {
        if (enPromo == null) {
            return List.of();
        }
        return repository.findByEnPromo(enPromo);
    }

    @Override
    public List<Acte> findByPrixGreaterThan(double prix) {
        if (prix < 0) {
            return List.of();
        }
        return repository.findByPrixGreaterThan(prix);
    }

    @Override
    public void updatePrix(Long id, double nouveauPrix) throws ServiceException {
        if (id == null) {
            throw new ServiceException("L'ID ne peut pas être null");
        }
        
        if (nouveauPrix < 0) {
            throw new ServiceException("Le prix ne peut pas être négatif");
        }
        
        Acte acte = repository.findById(id);
        if (acte == null) {
            throw new ServiceException("Acte avec ID " + id + " introuvable");
        }
        
        try {
            repository.updatePrix(id, nouveauPrix);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour du prix : " + e.getMessage(), e);
        }
    }

    @Override
    public long countByCategorie(String categorie) {
        if (categorie == null || categorie.trim().isEmpty()) {
            return 0;
        }
        return repository.countByCategorie(categorie.trim());
    }
}

