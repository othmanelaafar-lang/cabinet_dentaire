package ma.dentalTech.entities.acte;

import ma.dentalTech.entities.common.BaseEntity;

import java.math.BigDecimal;

/**
 * Entité représentant un acte médical
 */
public class Acte extends BaseEntity {
    private Long idActe;
    private String code;
    private String libelle;
    private String categorie;
    private String description;
    private Integer dureeMoyenne; // Durée moyenne en minutes
    private BigDecimal prixDeBase;
    private BigDecimal prixUnitaire;
    private Boolean actif;

    // Constructeurs
    public Acte() {
        super();
        this.actif = true;
    }

    public Acte(Long idActe, String code, String libelle, String categorie, 
                BigDecimal prixUnitaire) {
        super();
        this.idActe = idActe;
        this.code = code;
        this.libelle = libelle;
        this.categorie = categorie;
        this.prixUnitaire = prixUnitaire;
        this.actif = true;
    }

    // Getters et Setters
    public Long getIdActe() {
        return idActe;
    }

    public void setIdActe(Long idActe) {
        this.idActe = idActe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDureeMoyenne() {
        return dureeMoyenne;
    }

    public void setDureeMoyenne(Integer dureeMoyenne) {
        this.dureeMoyenne = dureeMoyenne;
    }

    public BigDecimal getPrixDeBase() {
        return prixDeBase;
    }

    public void setPrixDeBase(BigDecimal prixDeBase) {
        this.prixDeBase = prixDeBase;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }
}
