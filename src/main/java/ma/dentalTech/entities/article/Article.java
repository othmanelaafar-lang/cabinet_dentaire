package ma.dentalTech.entities.article;

import ma.dentalTech.entities.common.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entité représentant un article (fourniture médicale)
 */
public class Article extends BaseEntity {
    private Long id;
    private String reference;
    private String libelle;
    private String description;
    private String categorie;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private Integer quantiteStock;
    private Integer seuilAlerte;
    private Long fournisseurId; // Référence au fournisseur
    private LocalDate dateDernierAchat;
    private Boolean actif;

    // Constructeurs
    public Article() {
        super();
        this.quantiteStock = 0;
        this.seuilAlerte = 5;
        this.actif = true;
    }

    public Article(Long id, String reference, String libelle, BigDecimal prixAchat, BigDecimal prixVente) {
        super();
        this.id = id;
        this.reference = reference;
        this.libelle = libelle;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.quantiteStock = 0;
        this.seuilAlerte = 5;
        this.actif = true;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public BigDecimal getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }

    public BigDecimal getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(BigDecimal prixVente) {
        this.prixVente = prixVente;
    }

    public Integer getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(Integer quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public Integer getSeuilAlerte() {
        return seuilAlerte;
    }

    public void setSeuilAlerte(Integer seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public LocalDate getDateDernierAchat() {
        return dateDernierAchat;
    }

    public void setDateDernierAchat(LocalDate dateDernierAchat) {
        this.dateDernierAchat = dateDernierAchat;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    // Méthodes utilitaires
    public boolean estEnRuptureStock() {
        return quantiteStock != null && quantiteStock <= 0;
    }

    public boolean estSousSeuilAlerte() {
        return quantiteStock != null && seuilAlerte != null && quantiteStock <= seuilAlerte;
    }
}

