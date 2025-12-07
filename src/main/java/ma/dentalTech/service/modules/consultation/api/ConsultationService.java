package ma.dentalTech.service.modules.consultation.api;

import ma.dentalTech.entities.consultation.Consultation;

import java.time.LocalDate;
import java.util.List;

public interface ConsultationService {
    /**
     * Récupère tous les consultations
     */
    List<Consultation> findAll();

    /**
     * Récupère une consultation par son ID
     */
    Consultation findById(Long id);

    /**
     * Crée une nouvelle consultation avec validation
     */
    void create(Consultation consultation) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Met à jour une consultation existante avec validation
     */
    void update(Consultation consultation) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime une consultation par son ID
     */
    void deleteById(Long id) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime une consultation
     */
    void delete(Consultation consultation) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Recherche les consultations d'un patient
     */
    List<Consultation> findByPatientId(Long patientId);

    /**
     * Recherche les consultations d'un médecin
     */
    List<Consultation> findByMedecinId(Long medecinId);

    /**
     * Recherche les consultations par date
     */
    List<Consultation> findByDate(LocalDate date);

    /**
     * Recherche les consultations dans une plage de dates
     */
    List<Consultation> findByDateRange(LocalDate dateDebut, LocalDate dateFin);

    /**
     * Termine une consultation
     */
    void terminer(Long id) throws ma.dentalTech.common.exceptions.ServiceException;
}

