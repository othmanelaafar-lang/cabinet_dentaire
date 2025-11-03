package ma.dentalTech.entities.agendamensuel;

import ma.dentalTech.entities.enums.mois;

import java.util.List;

public class AgendaMensuel {
    public mois mois;
    private List<String> jourNonDisponible;
    public List<String> maListe= jourNonDisponible;
}
