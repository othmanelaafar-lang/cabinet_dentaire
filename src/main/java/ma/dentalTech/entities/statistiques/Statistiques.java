package ma.dentalTech.entities.statistiques;

import ma.dentalTech.entities.enums.Categorie;

import java.time.LocalDate;

public class Statistiques {
    public long id;
    public String nom;
    public Categorie categorie;
    public Double chiffre;
    public LocalDate datecalcul;
}
