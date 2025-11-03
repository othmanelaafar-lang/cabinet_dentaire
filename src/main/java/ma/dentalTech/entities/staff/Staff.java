package ma.dentalTech.entities.staff;

import ma.dentalTech.entities.utilisateur.Utilisateur;

import java.time.LocalDate;

public class Staff extends Utilisateur  {
    public Double salaire;
    public Double prime;
    public LocalDate dateRecrutement;
    public int soldeconge;
}
