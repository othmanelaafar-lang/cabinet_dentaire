package ma.dentalTech.entities.medicament;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.enums.forme;

import java.math.BigDecimal;

/**
 * Entité représentant un médicament
 */
public class Medicament extends BaseEntity {
    private Long idMct;
    private String nom;
    private String laboratoire;
    private String type;
    private forme forme;
    private Boolean remboursable;
    private BigDecimal prixUnitaire;
    private String description;

    // Constructeurs
    public Medicament() {
        super();
    }

    public Medicament(Long idMct, String nom, String laboratoire, String type, 
                     forme forme, Boolean remboursable, BigDecimal prixUnitaire, String description) {
        super();
        this.idMct = idMct;
        this.nom = nom;
        this.laboratoire = laboratoire;
        this.type = type;
        this.forme = forme;
        this.remboursable = remboursable;
        this.prixUnitaire = prixUnitaire;
        this.description = description;
    }

    // Getters et Setters
    public Long getIdMct() {
        return idMct;
    }

    public void setIdMct(Long idMct) {
        this.idMct = idMct;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(String laboratoire) {
        this.laboratoire = laboratoire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public forme getForme() {
        return forme;
    }

    public void setForme(forme forme) {
        this.forme = forme;
    }

    public Boolean getRemboursable() {
        return remboursable;
    }

    public void setRemboursable(Boolean remboursable) {
        this.remboursable = remboursable;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
