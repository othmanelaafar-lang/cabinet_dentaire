package ma.dentalTech.entities.role;

import ma.dentalTech.entities.enums.libelle;

import java.util.List;

public class Role extends Utilisateur {
    public long idRole;
    public libelle libelle;
    private List<String> privileges;
    public List<String> maliste= privileges;
}
