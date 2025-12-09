package ma.dentalTech.entities.acte;

import java.math.BigDecimal;

public class Acte {
    public Long idActe;
    public String code;
    public String libelle;
    public String categorie;
    public String description;
    public Integer dureeMoyenne;
    public double prixDeBase;
    public BigDecimal prixUnitaire;
    public Boolean actif;
}
