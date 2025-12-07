package ma.dentalTech.repository.modules.agendamensuel;

import ma.dentalTech.entities.agendamensuel.AgendaMensuel;
import ma.dentalTech.repository.common.CrudRepository;

public interface AgendaMensuelRepository extends CrudRepository<AgendaMensuel, Long> {
    // Méthodes spécifiques à l'AgendaMensuel peuvent être ajoutées ici
    // Par exemple :
    // AgendaMensuel findByMois(ma.dentalTech.entities.enums.mois mois);
}
