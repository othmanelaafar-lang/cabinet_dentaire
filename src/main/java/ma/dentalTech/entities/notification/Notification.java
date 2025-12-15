package ma.dentalTech.entities.notification;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.enums.Priorite;
import ma.dentalTech.entities.enums.Titre;
import ma.dentalTech.entities.enums.Type;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entité représentant une notification dans le système
 * Relation many-to-many avec Utilisateur
 */
public class Notification extends BaseEntity {
    private Long id;
    private Titre titre;
    private String message;
    private LocalDate date;
    private LocalTime time;
    private Type type;
    private Priorite priorite;

    // Constructeurs
    public Notification() {
        super();
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public Notification(Titre titre, String message, Type type, Priorite priorite) {
        super();
        this.titre = titre;
        this.message = message;
        this.type = type;
        this.priorite = priorite;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }
}
