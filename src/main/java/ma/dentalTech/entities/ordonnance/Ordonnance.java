package ma.dentalTech.entities.ordonnance;

import ma.dentalTech.entities.common.BaseEntity;

import java.time.LocalDate;

/**
 * Entité représentant une ordonnance médicale
 */
public class Ordonnance extends BaseEntity {
    private Long idOrd;
    private Long dossierMedicaleId; // Référence au dossier médical
    private Long consultationId; // Référence à la consultation
    private LocalDate dateEmission;
    private String notes;

    // Constructeurs
    public Ordonnance() {
        super();
        this.dateEmission = LocalDate.now();
    }

    public Ordonnance(Long idOrd, Long consultationId) {
        super();
        this.idOrd = idOrd;
        this.consultationId = consultationId;
        this.dateEmission = LocalDate.now();
    }

    // Getters et Setters
    public Long getIdOrd() {
        return idOrd;
    }

    public void setIdOrd(Long idOrd) {
        this.idOrd = idOrd;
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

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
