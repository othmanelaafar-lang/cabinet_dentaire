package ma.dentalTech.repository.modules.utilisateur;

import ma.dentalTech.entities.utilisateur.Utilisateur;
import ma.dentalTech.repository.common.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
    // Trouver un utilisateur par login
    Optional<Utilisateur> findByLogin(String login);
    
    // Trouver un utilisateur par email
    Optional<Utilisateur> findByEmail(String email);
    
    // Trouver des utilisateurs par nom
    List<Utilisateur> findByNom(String nom);
}
