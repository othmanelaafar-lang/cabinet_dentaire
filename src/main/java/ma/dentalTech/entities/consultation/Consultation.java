package ma.dentalTech.entities.consultation;

import ma.dentalTech.entities.enums.Statut;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Consultation {
    public Long idConsultation;
    public Long rendezVousId;
    public Long patientId;
    public Long medecinId;
    public LocalDate Date;
    public LocalDate dateConsultation;
    public Statut statut;
    public String motif;
    public String diagnostic;
    public String traitementPrescrit;
    public String observationMedecin;
    public String notes;
    public BigDecimal montantTotal;
    public Boolean regle;
}
