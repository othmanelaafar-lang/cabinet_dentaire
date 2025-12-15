package ma.dentalTech.entities.facture;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.enums.Statut;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entité représentant une facture
 */
public class Facture extends BaseEntity {
    private Long idFacture;
    private Long dossierMedicaleId; // Référence au dossier médical
    private Long consultationId; // Référence à la consultation
    private Long patientId; // Référence au patient
    private BigDecimal montantTotal;
    private BigDecimal totalePaye;
    private BigDecimal reste;
    private Statut statut;
    private LocalDateTime dateFacture;

    // Constructeurs
    public Facture() {
        super();
        this.dateFacture = LocalDateTime.now();
        this.totalePaye = BigDecimal.ZERO;
        this.reste = BigDecimal.ZERO;
    }

    public Facture(Long idFacture, Long consultationId, Long patientId, BigDecimal montantTotal) {
        super();
        this.idFacture = idFacture;
        this.consultationId = consultationId;
        this.patientId = patientId;
        this.montantTotal = montantTotal;
        this.totalePaye = BigDecimal.ZERO;
        this.reste = montantTotal;
        this.dateFacture = LocalDateTime.now();
    }

    // Getters et Setters
    public Long getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Long idFacture) {
        this.idFacture = idFacture;
    }

    public Long getDossierMedicaleId() {
        return dossierMedicaleId;
    }

    public void setDossierMedicaleId(Long dossierMedicaleId) {
        this.dossierMedicaleId = dossierMedicaleId;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
        // Recalculer le reste
        if (this.totalePaye != null && montantTotal != null) {
            this.reste = montantTotal.subtract(this.totalePaye);
        }
    }

    public BigDecimal getTotalePaye() {
        return totalePaye;
    }

    public void setTotalePaye(BigDecimal totalePaye) {
        this.totalePaye = totalePaye;
        // Recalculer le reste
        if (this.montantTotal != null && totalePaye != null) {
            this.reste = this.montantTotal.subtract(totalePaye);
        }
    }

    public BigDecimal getReste() {
        return reste;
    }

    public void setReste(BigDecimal reste) {
        this.reste = reste;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(LocalDateTime dateFacture) {
        this.dateFacture = dateFacture;
    }
}
