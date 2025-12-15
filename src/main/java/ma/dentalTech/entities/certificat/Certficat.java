package ma.dentalTech.entities.certificat;

import ma.dentalTech.entities.common.BaseEntity;

import java.time.LocalDate;

/**
 * Entité représentant un certificat médical
 */
public class Certficat extends BaseEntity {
    private Long idCertif;
    private Long patientId; // Référence au patient
    private Long medecinId; // Référence au médecin
    private Long dossierMedicaleId; // Référence au dossier médical
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer duree; // Durée en jours
    private String noteMedecin;

    // Constructeurs
    public Certficat() {
        super();
    }

    public Certficat(Long idCertif, Long patientId, Long medecinId, Long dossierMedicaleId,
                     LocalDate dateDebut, LocalDate dateFin, Integer duree, String noteMedecin) {
        super();
        this.idCertif = idCertif;
        this.patientId = patientId;
        this.medecinId = medecinId;
        this.dossierMedicaleId = dossierMedicaleId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.noteMedecin = noteMedecin;
    }

    // Getters et Setters
    public Long getIdCertif() {
        return idCertif;
    }

    public void setIdCertif(Long idCertif) {
        this.idCertif = idCertif;
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

    public Long getDossierMedicaleId() {
        return dossierMedicaleId;
    }

    public void setDossierMedicaleId(Long dossierMedicaleId) {
        this.dossierMedicaleId = dossierMedicaleId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getNoteMedecin() {
        return noteMedecin;
    }

    public void setNoteMedecin(String noteMedecin) {
        this.noteMedecin = noteMedecin;
    }
}
