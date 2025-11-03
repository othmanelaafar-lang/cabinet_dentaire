package ma.dentalTech.entities.notification;

import ma.dentalTech.entities.enums.Priorite;
import ma.dentalTech.entities.enums.Titre;
import ma.dentalTech.entities.enums.Type;

import java.time.LocalDate;
import java.time.LocalTime;

public class Notification {
    public long id;
    public Titre titre;
    public String message;
    public LocalDate date;
    public LocalTime time;
    public Type type;
    public Priorite priorite;

}
