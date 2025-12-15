package ma.dentalTech.entities.prescreption;

import ma.dentalTech.entities.common.BaseEntity;

/**
 * Entité représentant une prescription de médicament dans une ordonnance
 */
public class Prescreption extends BaseEntity {
    private Long idPr;
    private Long ordonnanceId; // Référence à l'ordonnance
    private Long medicamentId; // Référence au médicament
    private Integer quantite;
    private String frequence; // Ex: "3 fois par jour"
    private Integer dureeEnJours; // Durée du traitement en jours

    // Constructeurs
    public Prescreption() {
        super();
    }

    public Prescreption(Long idPr, Long ordonnanceId, Long medicamentId, 
                       Integer quantite, String frequence, Integer dureeEnJours) {
        super();
        this.idPr = idPr;
        this.ordonnanceId = ordonnanceId;
        this.medicamentId = medicamentId;
        this.quantite = quantite;
        this.frequence = frequence;
        this.dureeEnJours = dureeEnJours;
    }

    // Getters et Setters
    public Long getIdPr() {
        return idPr;
    }

    public void setIdPr(Long idPr) {
        this.idPr = idPr;
    }

    public Long getOrdonnanceId() {
        return ordonnanceId;
    }

    public void setOrdonnanceId(Long ordonnanceId) {
        this.ordonnanceId = ordonnanceId;
    }

    public Long getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(Long medicamentId) {
        this.medicamentId = medicamentId;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public Integer getDureeEnJours() {
        return dureeEnJours;
    }

    public void setDureeEnJours(Integer dureeEnJours) {
        this.dureeEnJours = dureeEnJours;
    }
}
