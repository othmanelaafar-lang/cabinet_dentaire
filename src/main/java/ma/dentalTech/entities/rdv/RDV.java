package ma.dentalTech.entities.rdv;

import ma.dentalTech.entities.enums.Statut;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RDV {
    public Long idRDV;
    public Long patientId;
    public Long medecinId;
    public LocalDate Date;
    public LocalTime heure;
    public LocalDateTime dateHeureDebut;
    public LocalDateTime dateHeureFin;
    public String motif;
    public String motifConsultation;
    public String noteMedecin;
    public String notes;
    public Statut statut;
}

