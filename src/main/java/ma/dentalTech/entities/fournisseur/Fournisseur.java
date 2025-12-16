package ma.dentalTech.entities.fournisseur;

import ma.dentalTech.entities.common.BaseEntity;

/**
 * Entité représentant un fournisseur
 */
public class Fournisseur extends BaseEntity {
    private Long id;
    private String nom;
    private String contactNom;
    private String telephone;
    private String email;
    private String adresse;
    private String notes;
    private Boolean actif;

    // Constructeurs
    public Fournisseur() {
        super();
        this.actif = true;
    }

    public Fournisseur(Long id, String nom, String contactNom, String telephone, String email) {
        super();
        this.id = id;
        this.nom = nom;
        this.contactNom = contactNom;
        this.telephone = telephone;
        this.email = email;
        this.actif = true;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContactNom() {
        return contactNom;
    }

    public void setContactNom(String contactNom) {
        this.contactNom = contactNom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }
}

