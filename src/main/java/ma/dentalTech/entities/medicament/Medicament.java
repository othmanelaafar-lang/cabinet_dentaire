package ma.dentalTech.entities.medicament;

public class Medicament {
    public Long idMct;
    public String nom;
    public String laboratoire;
    public String type;
    public enum forme {
        forme1,
        forme2
    };
    public boolean remboursable;
    public Double prixUnitaire;
    public String Descreption;


}
