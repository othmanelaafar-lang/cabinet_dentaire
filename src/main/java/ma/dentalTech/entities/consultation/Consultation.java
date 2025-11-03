package ma.dentalTech.entities.consultation;


import ma.dentalTech.entities.enums.Statut;

import java.time.LocalDate;

public class Consultation {
    public long idConsultation;
    public LocalDate Date;
    public Statut statut;
    public String observationMedecin;


}
