package ma.dentalTech.entities.utilisateur;

import ma.dentalTech.entities.enums.Sexe;

import java.time.LocalDate;

public class Utilisateur {
    public Long idUser;
    public String nom;
    public String email;
    public String adresse;
    public String cin;
    public String tel;
    public Sexe sexe;
    public String login;
    public String motDePasse;
    public LocalDate lastLoginDate;
    public LocalDate dateNaissance;
}
