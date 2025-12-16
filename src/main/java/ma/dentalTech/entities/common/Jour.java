package ma.dentalTech.entities.common;

import java.time.LocalDate;

/**
 * Classe représentant un jour dans un agenda
 */
public class Jour {
    private LocalDate date;
    private Boolean disponible;

    public Jour() {
    }

    public Jour(LocalDate date, Boolean disponible) {
        this.date = date;
        this.disponible = disponible;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}

