package ma.dentalTech.entities.consultation;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.enums.Statut;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entité représentant une consultation médicale
 */
public class Consultation extends BaseEntity {
    private Long idConsultation;
    private Long dossierMedicaleId; // Référence au dossier médical
    private Long rendezVousId; // Référence au rendez-vous
    private Long patientId; // Référence au patient
    private Long medecinId; // Référence au médecin
    private LocalDate dateConsultation;
    private Statut statut;
    private String motif;
    private String diagnostic;
    private String traitementPrescrit;
    private String observationMedecin;
    private String notes;
    private BigDecimal montantTotal;
    private Boolean regle;

    // Constructeurs
    public Consultation() {
        super();
        this.dateConsultation = LocalDate.now();
        this.regle = false;
    }

    public Consultation(Long idConsultation, Long patientId, Long medecinId, LocalDate dateConsultation) {
        super();
        this.idConsultation = idConsultation;
        this.patientId = patientId;
        this.medecinId = medecinId;
        this.dateConsultation = dateConsultation;
        this.regle = false;
    }

    // Getters et Setters
    public Long getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(Long idConsultation) {
        this.idConsultation = idConsultation;
    }

    public Long getDossierMedicaleId() {
        return dossierMedicaleId;
    }

    public void setDossierMedicaleId(Long dossierMedicaleId) {
        this.dossierMedicaleId = dossierMedicaleId;
    }

    public Long getRendezVousId() {
        return rendezVousId;
    }

    public void setRendezVousId(Long rendezVousId) {
        this.rendezVousId = rendezVousId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(Long medecinId) {
        this.medecinId = medecinId;
    }

    public LocalDate getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(LocalDate dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTraitementPrescrit() {
        return traitementPrescrit;
    }

    public void setTraitementPrescrit(String traitementPrescrit) {
        this.traitementPrescrit = traitementPrescrit;
    }

    public String getObservationMedecin() {
        return observationMedecin;
    }

    public void setObservationMedecin(String observationMedecin) {
        this.observationMedecin = observationMedecin;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Boolean getRegle() {
        return regle;
    }

    public void setRegle(Boolean regle) {
        this.regle = regle;
    }
}
