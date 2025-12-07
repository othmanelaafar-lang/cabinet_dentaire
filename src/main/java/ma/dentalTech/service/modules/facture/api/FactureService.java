package ma.dentalTech.service.modules.facture.api;

import ma.dentalTech.entities.facture.Facture;

import java.time.LocalDateTime;
import java.util.List;

public interface FactureService {
    /**
     * Récupère toutes les factures
     */
    List<Facture> findAll();

    /**
     * Récupère une facture par son ID
     */
    Facture findById(Long id);

    /**
     * Crée une nouvelle facture avec validation
     */
    void create(Facture facture) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Met à jour une facture existante avec validation
     */
    void update(Facture facture) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime une facture par son ID
     */
    void deleteById(Long id) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime une facture
     */
    void delete(Facture facture) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Recherche les factures d'un patient
     */
    List<Facture> findByPatientId(Long patientId);

    /**
     * Recherche les factures dans une plage de dates
     */
    List<Facture> findByDateRange(LocalDateTime dateDebut, LocalDateTime dateFin);

    /**
     * Recherche les factures par statut
     */
    List<Facture> findByStatut(ma.dentalTech.entities.enums.Statut statut);

    /**
     * Calcule le montant total payé pour un patient
     */
    double getTotalPayeByPatient(Long patientId);

    /**
     * Calcule le reste à payer pour un patient
     */
    double getResteAPayerByPatient(Long patientId);

    /**
     * Enregistre un paiement partiel
     */
    void enregistrerPaiement(Long factureId, double montant) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Marque une facture comme payée
     */
    void marquerCommePayee(Long id) throws ma.dentalTech.common.exceptions.ServiceException;
}

