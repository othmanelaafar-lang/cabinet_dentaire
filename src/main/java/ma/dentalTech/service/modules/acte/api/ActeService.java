package ma.dentalTech.service.modules.acte.api;

import ma.dentalTech.entities.acte.Acte;

import java.util.List;

public interface ActeService {
    /**
     * Récupère tous les actes
     */
    List<Acte> findAll();

    /**
     * Récupère un acte par son ID
     */
    Acte findById(Long id);

    /**
     * Crée un nouvel acte avec validation
     */
    void create(Acte acte) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Met à jour un acte existant avec validation
     */
    void update(Acte acte) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime un acte par son ID
     */
    void deleteById(Long id) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime un acte
     */
    void delete(Acte acte) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Recherche les actes par catégorie
     */
    List<Acte> findByCategorie(String categorie);

    /**
     * Recherche les actes par libellé
     */
    List<Acte> findByLibelle(String libelle);

    /**
     * Recherche les actes (par libellé ou catégorie)
     */
    List<Acte> search(String searchTerm);

    /**
     * Recherche les actes par code
     */
    List<Acte> findByCode(String code);

    /**
     * Recherche les actes en promotion
     */
    List<Acte> findByEnPromo(ma.dentalTech.entities.enums.enPromo enPromo);

    /**
     * Recherche les actes dont le prix est supérieur à un montant
     */
    List<Acte> findByPrixGreaterThan(double prix);

    /**
     * Met à jour le prix d'un acte
     */
    void updatePrix(Long id, double nouveauPrix) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Compte le nombre d'actes par catégorie
     */
    long countByCategorie(String categorie);
}
