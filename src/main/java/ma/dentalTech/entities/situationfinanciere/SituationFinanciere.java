package ma.dentalTech.entities.situationfinanciere;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.enums.Statut;
import ma.dentalTech.entities.enums.enPromo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entité représentant la situation financière d'un patient
 */
public class SituationFinanciere extends BaseEntity {
    private Long idSF;
    private Long dossierMedicaleId; // Référence au dossier médical
    private BigDecimal totaleDesActes;
    private BigDecimal totalePaye;
    private BigDecimal credit;
    private Statut statut;
    private enPromo enPromo;
    private LocalDateTime dateFacture;

    // Constructeurs
    public SituationFinanciere() {
        super();
    }

    public SituationFinanciere(Long idSF, Long dossierMedicaleId, BigDecimal totaleDesActes, 
                               BigDecimal totalePaye, BigDecimal credit, Statut statut, 
                               enPromo enPromo) {
        super();
        this.idSF = idSF;
        this.dossierMedicaleId = dossierMedicaleId;
        this.totaleDesActes = totaleDesActes;
        this.totalePaye = totalePaye;
        this.credit = credit;
        this.statut = statut;
        this.enPromo = enPromo;
        this.dateFacture = LocalDateTime.now();
    }

    // Getters et Setters
    public Long getIdSF() {
        return idSF;
    }

    public void setIdSF(Long idSF) {
        this.idSF = idSF;
    }

    public Long getDossierMedicaleId() {
        return dossierMedicaleId;
    }

    public void setDossierMedicaleId(Long dossierMedicaleId) {
        this.dossierMedicaleId = dossierMedicaleId;
    }

    public BigDecimal getTotaleDesActes() {
        return totaleDesActes;
    }

    public void setTotaleDesActes(BigDecimal totaleDesActes) {
        this.totaleDesActes = totaleDesActes;
    }

    public BigDecimal getTotalePaye() {
        return totalePaye;
    }

    public void setTotalePaye(BigDecimal totalePaye) {
        this.totalePaye = totalePaye;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public enPromo getEnPromo() {
        return enPromo;
    }

    public void setEnPromo(enPromo enPromo) {
        this.enPromo = enPromo;
    }

    public LocalDateTime getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(LocalDateTime dateFacture) {
        this.dateFacture = dateFacture;
    }
}
