package ma.dentalTech.entities.antecedents;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.enums.NiveauDeRisque;

/**
 * Entité représentant les antécédents médicaux d'un patient
 */
public class Antecedents extends BaseEntity {
    private Long idAntecedent;
    private Long patientId; // Référence au patient
    private String nom;
    private String categorie;
    private NiveauDeRisque niveauDeRisque;

    // Constructeurs
    public Antecedents() {
        super();
    }

    public Antecedents(Long idAntecedent, Long patientId, String nom, String categorie, NiveauDeRisque niveauDeRisque) {
        super();
        this.idAntecedent = idAntecedent;
        this.patientId = patientId;
        this.nom = nom;
        this.categorie = categorie;
        this.niveauDeRisque = niveauDeRisque;
    }

    // Getters et Setters
    public Long getIdAntecedent() {
        return idAntecedent;
    }

    public void setIdAntecedent(Long idAntecedent) {
        this.idAntecedent = idAntecedent;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public NiveauDeRisque getNiveauDeRisque() {
        return niveauDeRisque;
    }

    public void setNiveauDeRisque(NiveauDeRisque niveauDeRisque) {
        this.niveauDeRisque = niveauDeRisque;
    }
}
