package ma.dentalTech.entities.dossiermedicale;

import ma.dentalTech.entities.common.BaseEntity;

/**
 * Entité représentant le dossier médical d'un patient
 */
public class DossierMedicale extends BaseEntity {
    private Long idDM;
    private Long patientId; // Référence au patient (relation 1-1)

    // Constructeurs
    public DossierMedicale() {
        super();
    }

    public DossierMedicale(Long idDM, Long patientId) {
        super();
        this.idDM = idDM;
        this.patientId = patientId;
    }

    // Getters et Setters
    public Long getIdDM() {
        return idDM;
    }

    public void setIdDM(Long idDM) {
        this.idDM = idDM;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
