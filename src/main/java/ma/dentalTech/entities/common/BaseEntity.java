package ma.dentalTech.entities.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe de base pour toutes les entités du système
 * Fournit les attributs communs de traçabilité
 */
public abstract class BaseEntity {
    protected Long idEntite;
    protected LocalDate dateCreation;
    protected LocalDateTime dateDerniereModification;
    protected String modifiePar;
    protected String creePar;

    // Constructeurs
    public BaseEntity() {
        this.dateCreation = LocalDate.now();
    }

    public BaseEntity(Long idEntite) {
        this();
        this.idEntite = idEntite;
    }

    // Getters et Setters
    public Long getIdEntite() {
        return idEntite;
    }

    public void setIdEntite(Long idEntite) {
        this.idEntite = idEntite;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateDerniereModification() {
        return dateDerniereModification;
    }

    public void setDateDerniereModification(LocalDateTime dateDerniereModification) {
        this.dateDerniereModification = dateDerniereModification;
    }

    public String getModifiePar() {
        return modifiePar;
    }

    public void setModifiePar(String modifiePar) {
        this.modifiePar = modifiePar;
    }

    public String getCreePar() {
        return creePar;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }
}

