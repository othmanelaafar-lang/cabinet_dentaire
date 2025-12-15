package ma.dentalTech.entities.statistiques;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.agendamensuel.AgendaMensuel;
import ma.dentalTech.entities.enums.Categorie;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entité représentant des statistiques du cabinet
 * Relation 1-1 avec AgendaMensuel
 */
public class Statistiques extends BaseEntity {
    private Long id;
    private Long cabinetMedicaleId; // Référence au cabinet médical
    private String nom;
    private Categorie categorie;
    private BigDecimal chiffre;
    private LocalDate dateCalcul;
    private AgendaMensuel agendaMensuel; // Relation 1-1 avec AgendaMensuel

    // Constructeurs
    public Statistiques() {
        super();
        this.dateCalcul = LocalDate.now();
    }

    public Statistiques(Long id, Long cabinetMedicaleId, String nom, 
                       Categorie categorie, BigDecimal chiffre) {
        super();
        this.id = id;
        this.cabinetMedicaleId = cabinetMedicaleId;
        this.nom = nom;
        this.categorie = categorie;
        this.chiffre = chiffre;
        this.dateCalcul = LocalDate.now();
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCabinetMedicaleId() {
        return cabinetMedicaleId;
    }

    public void setCabinetMedicaleId(Long cabinetMedicaleId) {
        this.cabinetMedicaleId = cabinetMedicaleId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public BigDecimal getChiffre() {
        return chiffre;
    }

    public void setChiffre(BigDecimal chiffre) {
        this.chiffre = chiffre;
    }

    public LocalDate getDateCalcul() {
        return dateCalcul;
    }

    public void setDateCalcul(LocalDate dateCalcul) {
        this.dateCalcul = dateCalcul;
    }

    public AgendaMensuel getAgendaMensuel() {
        return agendaMensuel;
    }

    public void setAgendaMensuel(AgendaMensuel agendaMensuel) {
        this.agendaMensuel = agendaMensuel;
    }
}
