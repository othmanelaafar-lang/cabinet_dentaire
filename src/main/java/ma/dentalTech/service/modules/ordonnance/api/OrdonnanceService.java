package ma.dentalTech.service.modules.ordonnance.api;

import ma.dentalTech.entities.ordonnance.Ordonnance;

import java.time.LocalDate;
import java.util.List;

public interface OrdonnanceService {
    /**
     * Récupère toutes les ordonnances
     */
    List<Ordonnance> findAll();

    /**
     * Récupère une ordonnance par son ID
     */
    Ordonnance findById(Long id);

    /**
     * Crée une nouvelle ordonnance avec validation
     */
    void create(Ordonnance ordonnance) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Met à jour une ordonnance existante avec validation
     */
    void update(Ordonnance ordonnance) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime une ordonnance par son ID
     */
    void deleteById(Long id) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime une ordonnance
     */
    void delete(Ordonnance ordonnance) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Recherche les ordonnances d'une consultation
     */
    List<Ordonnance> findByConsultationId(Long consultationId);

    /**
     * Recherche les ordonnances par date
     */
    List<Ordonnance> findByDate(LocalDate date);

    /**
     * Recherche les ordonnances dans une plage de dates
     */
    List<Ordonnance> findByDateRange(LocalDate dateDebut, LocalDate dateFin);
}

