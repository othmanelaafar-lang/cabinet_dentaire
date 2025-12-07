package ma.dentalTech.repository.modules.medecin;

import ma.dentalTech.entities.medecin.Medecin;
import ma.dentalTech.repository.common.CrudRepository;

public interface MedecinRepository extends CrudRepository<Medecin, Long> {
    // Méthodes spécifiques au Medecin peuvent être ajoutées ici
    // Par exemple :
    // List<Medecin> findBySpecialite(String specialite);
}
