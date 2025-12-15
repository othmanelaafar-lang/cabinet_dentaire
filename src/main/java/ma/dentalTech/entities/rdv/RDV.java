package ma.dentalTech.entities.rdv;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.enums.Statut;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Entité représentant un rendez-vous
 */
public class RDV extends BaseEntity {
    private Long idRDV;
    private Long dossierMedicaleId; // Référence au dossier médical
    private Long patientId; // Référence au patient
    private Long medecinId; // Référence au médecin
    private LocalDate Date; // Date du rendez-vous (pour compatibilité)
    private LocalTime heure; // Heure du rendez-vous (pour compatibilité)
    private LocalDateTime dateHeureDebut; // Date et heure de début
    private LocalDateTime dateHeureFin; // Date et heure de fin
    private String motif;
    private String motifConsultation;
    private String noteMedecin;
    private String notes;
    private Statut statut;

    // Constructeurs
    public RDV() {
        super();
    }

    public RDV(Long idRDV, Long patientId, Long medecinId, LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin) {
        super();
        this.idRDV = idRDV;
        this.patientId = patientId;
        this.medecinId = medecinId;
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
        if (dateHeureDebut != null) {
            this.Date = dateHeureDebut.toLocalDate();
            this.heure = dateHeureDebut.toLocalTime();
        }
    }

    // Getters et Setters
    public Long getIdRDV() {
        return idRDV;
    }

    public void setIdRDV(Long idRDV) {
        this.idRDV = idRDV;
    }

    public Long getDossierMedicaleId() {
        return dossierMedicaleId;
    }

    public void setDossierMedicaleId(Long dossierMedicaleId) {
        this.dossierMedicaleId = dossierMedicaleId;
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

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        this.Date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public LocalDateTime getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(LocalDateTime dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
        if (dateHeureDebut != null) {
            this.Date = dateHeureDebut.toLocalDate();
            this.heure = dateHeureDebut.toLocalTime();
        }
    }

    public LocalDateTime getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(LocalDateTime dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getMotifConsultation() {
        return motifConsultation;
    }

    public void setMotifConsultation(String motifConsultation) {
        this.motifConsultation = motifConsultation;
    }

    public String getNoteMedecin() {
        return noteMedecin;
    }

    public void setNoteMedecin(String noteMedecin) {
        this.noteMedecin = noteMedecin;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
