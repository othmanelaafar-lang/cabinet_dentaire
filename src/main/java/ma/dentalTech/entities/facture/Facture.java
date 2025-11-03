package ma.dentalTech.entities.facture;

import ma.dentalTech.entities.enums.Statut;

import java.time.LocalDateTime;

public class Facture {
public long idFacture;
public double totalepaye;
public double Reste;
public Statut statut;
public LocalDateTime dateFacture;

}
