package ma.dentalTech.entities.agendamensuel;

import ma.dentalTech.entities.common.BaseEntity;
import ma.dentalTech.entities.common.Jour;
import ma.dentalTech.entities.enums.mois;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un agenda mensuel
 */
public class AgendaMensuel extends BaseEntity {
    private Long idAgenda;
    private mois mois;
    private List<Jour> joursNonDisponible; // Liste des jours non disponibles

    // Constructeurs
    public AgendaMensuel() {
        super();
        this.joursNonDisponible = new ArrayList<>();
    }

    public AgendaMensuel(Long idAgenda, mois mois) {
        super();
        this.idAgenda = idAgenda;
        this.mois = mois;
        this.joursNonDisponible = new ArrayList<>();
    }

    // Getters et Setters
    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public mois getMois() {
        return mois;
    }

    public void setMois(mois mois) {
        this.mois = mois;
    }

    public List<Jour> getJoursNonDisponible() {
        return joursNonDisponible;
    }

    public void setJoursNonDisponible(List<Jour> joursNonDisponible) {
        this.joursNonDisponible = joursNonDisponible;
    }

    // Méthodes utilitaires
    public void ajouterJourNonDisponible(Jour jour) {
        if (joursNonDisponible == null) {
            joursNonDisponible = new ArrayList<>();
        }
        joursNonDisponible.add(jour);
    }
}
