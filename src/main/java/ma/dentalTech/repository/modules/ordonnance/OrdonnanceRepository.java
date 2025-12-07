package ma.dentalTech.repository.modules.ordonnance;

import ma.dentalTech.entities.ordonnance.Ordonnance;
import ma.dentalTech.repository.common.CrudRepository;

public interface OrdonnanceRepository extends CrudRepository<Ordonnance, Long> {
    // Méthodes spécifiques aux Ordonnances peuvent être ajoutées ici
}
