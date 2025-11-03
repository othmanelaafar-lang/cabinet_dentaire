package ma.dentalTech.entities.rdv;

import ma.dentalTech.entities.enums.Statut;

import java.time.LocalDate;
import java.time.LocalTime;

public class RDV {
    public Long idRDV;
    public LocalDate Date;
    public LocalTime heure;
    public String motif;
    public String noteMedecin;
    public Statut statut;
}

