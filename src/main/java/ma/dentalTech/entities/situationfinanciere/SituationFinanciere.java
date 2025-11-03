package ma.dentalTech.entities.situationfinanciere;

import ma.dentalTech.entities.enums.Statut;

import java.time.LocalDateTime;

public class SituationFinanciere {
    public long idSF;
    public double totaleFacture;
    public double totalepaye;
    public double Reste;
    public Statut staut;
    public LocalDateTime dateFacture;
}
