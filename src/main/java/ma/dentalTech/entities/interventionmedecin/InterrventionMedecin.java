package ma.dentalTech.entities.interventionmedecin;

import ma.dentalTech.entities.common.BaseEntity;

import java.math.BigDecimal;

/**
 * Entité représentant une intervention médicale effectuée par un médecin
 */
public class InterrventionMedecin extends BaseEntity {
    private Long idIM;
    private Long consultationId; // Référence à la consultation
    private Long acteId; // Référence à l'acte médical
    private Integer numDent; // Numéro de la dent concernée
    private BigDecimal prixDePatient; // Prix facturé au patient

    // Constructeurs
    public InterrventionMedecin() {
        super();
    }

    public InterrventionMedecin(Long idIM, Long consultationId, Long acteId, 
                                 Integer numDent, BigDecimal prixDePatient) {
        super();
        this.idIM = idIM;
        this.consultationId = consultationId;
        this.acteId = acteId;
        this.numDent = numDent;
        this.prixDePatient = prixDePatient;
    }

    // Getters et Setters
    public Long getIdIM() {
        return idIM;
    }

    public void setIdIM(Long idIM) {
        this.idIM = idIM;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public Long getActeId() {
        return acteId;
    }

    public void setActeId(Long acteId) {
        this.acteId = acteId;
    }

    public Integer getNumDent() {
        return numDent;
    }

    public void setNumDent(Integer numDent) {
        this.numDent = numDent;
    }

    public BigDecimal getPrixDePatient() {
        return prixDePatient;
    }

    public void setPrixDePatient(BigDecimal prixDePatient) {
        this.prixDePatient = prixDePatient;
    }
}
