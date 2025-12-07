package ma.dentalTech.repository.modules.utilisateur;

import ma.dentalTech.entities.utilisateur.Utilisateur;
import ma.dentalTech.repository.common.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
    // Méthodes spécifiques à l'Utilisateur peuvent être ajoutées ici
    // Par exemple :
    // Optional<Utilisateur> findByUsername(String username);
    // boolean existsByEmail(String email);
}
