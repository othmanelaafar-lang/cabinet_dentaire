package ma.dentalTech.entities.ordonnance;

import java.time.LocalDate;

public class Ordonnance {
    public Long idOrd;
    public Long consultationId;
    public LocalDate date;
    public LocalDate dateEmission;
    public String notes;
}
