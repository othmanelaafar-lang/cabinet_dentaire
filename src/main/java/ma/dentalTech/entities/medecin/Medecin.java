package ma.dentalTech.entities.medecin;

import ma.dentalTech.entities.common.AgendaDocteur;
import ma.dentalTech.entities.staff.Staff;

/**
 * Entité représentant un médecin
 * Hérite de Staff
 */
public class Medecin extends Staff {
    private String specialite;
    private AgendaDocteur agendaMensuel; // Agenda du médecin

    // Constructeurs
    public Medecin() {
        super();
    }

    public Medecin(String specialite) {
        super();
        this.specialite = specialite;
    }

    // Getters et Setters
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public AgendaDocteur getAgendaMensuel() {
        return agendaMensuel;
    }

    public void setAgendaMensuel(AgendaDocteur agendaMensuel) {
        this.agendaMensuel = agendaMensuel;
    }
}
