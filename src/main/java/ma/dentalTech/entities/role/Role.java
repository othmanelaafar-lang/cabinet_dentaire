package ma.dentalTech.entities.role;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.enums.libelle;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un rôle dans le système
 * Relation many-to-many avec Utilisateur (pas d'héritage)
 */
public class Role extends BaseEntity {
    private Long idRole;
    private libelle libelle;
    private List<String> privileges; // Liste des privilèges associés au rôle

    // Constructeurs
    public Role() {
        super();
        this.privileges = new ArrayList<>();
    }

    public Role(Long idRole, libelle libelle) {
        super();
        this.idRole = idRole;
        this.libelle = libelle;
        this.privileges = new ArrayList<>();
    }

    // Getters et Setters
    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public libelle getLibelle() {
        return libelle;
    }

    public void setLibelle(libelle libelle) {
        this.libelle = libelle;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }

    // Méthodes utilitaires
    public void ajouterPrivilege(String privilege) {
        if (privileges == null) {
            privileges = new ArrayList<>();
        }
        if (!privileges.contains(privilege)) {
            privileges.add(privilege);
        }
    }

    public void retirerPrivilege(String privilege) {
        if (privileges != null) {
            privileges.remove(privilege);
        }
    }
}
