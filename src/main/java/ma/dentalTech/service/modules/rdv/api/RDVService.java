package ma.dentalTech.service.modules.rdv.api;

import ma.dentalTech.entities.rdv.RDV;

import java.time.LocalDate;
import java.util.List;

public interface RDVService {
    /**
     * Récupère tous les rendez-vous
     */
    List<RDV> findAll();

    /**
     * Récupère un rendez-vous par son ID
     */
    RDV findById(Long id);

    /**
     * Crée un nouveau rendez-vous avec validation
     */
    void create(RDV rdv) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Met à jour un rendez-vous existant avec validation
     */
    void update(RDV rdv) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime un rendez-vous par son ID
     */
    void deleteById(Long id) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime un rendez-vous
     */
    void delete(RDV rdv) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Recherche les rendez-vous par date
     */
    List<RDV> findByDate(LocalDate date);

    /**
     * Recherche les rendez-vous d'un patient
     */
    List<RDV> findByPatientId(Long patientId);

    /**
     * Recherche les rendez-vous d'un médecin
     */
    List<RDV> findByMedecinId(Long medecinId);

    /**
     * Vérifie si un créneau est disponible pour un médecin
     */
    boolean isCreneauDisponible(Long medecinId, LocalDate date, java.time.LocalTime heure);

    /**
     * Annule un rendez-vous
     */
    void annuler(Long id) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Confirme un rendez-vous
     */
    void confirmer(Long id) throws ma.dentalTech.common.exceptions.ServiceException;
}

