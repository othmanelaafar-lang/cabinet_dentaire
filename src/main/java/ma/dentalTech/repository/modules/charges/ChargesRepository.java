package ma.dentalTech.repository.modules.charges;

import ma.dentalTech.entities.charges.Charges;
import ma.dentalTech.repository.common.CrudRepository;

public interface ChargesRepository extends CrudRepository<Charges, Long> {
    // Méthodes spécifiques aux Charges peuvent être ajoutées ici
}
