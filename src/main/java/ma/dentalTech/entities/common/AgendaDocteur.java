package ma.dentalTech.entities.common;

import ma.dentalTech.entities.agendamensuel.AgendaMensuel;

/**
 * Classe représentant l'agenda d'un docteur
 * Contient les agendas mensuels du médecin
 */
public class AgendaDocteur {
    private Long medecinId;
    private AgendaMensuel agendaMensuel;

    public AgendaDocteur() {
    }

    public AgendaDocteur(Long medecinId, AgendaMensuel agendaMensuel) {
        this.medecinId = medecinId;
        this.agendaMensuel = agendaMensuel;
    }

    public Long getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(Long medecinId) {
        this.medecinId = medecinId;
    }

    public AgendaMensuel getAgendaMensuel() {
        return agendaMensuel;
    }

    public void setAgendaMensuel(AgendaMensuel agendaMensuel) {
        this.agendaMensuel = agendaMensuel;
    }
}

