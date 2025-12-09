package ma.dentalTech.entities.facture;

import ma.dentalTech.entities.enums.Statut;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Facture {
    public Long idFacture;
    public Long consultationId;
    public Long patientId;
    public double totalepaye;
    public double Reste;
    public BigDecimal montantTotal;
    public Statut statut;
    public LocalDateTime dateFacture;
}
