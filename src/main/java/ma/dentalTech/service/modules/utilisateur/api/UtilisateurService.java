package ma.dentalTech.service.modules.utilisateur.api;

import ma.dentalTech.entities.utilisateur.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    /**
     * Récupère tous les utilisateurs
     */
    List<Utilisateur> findAll();

    /**
     * Récupère un utilisateur par son ID
     */
    Utilisateur findById(Long id);

    /**
     * Crée un nouvel utilisateur avec validation
     */
    void create(Utilisateur utilisateur) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Met à jour un utilisateur existant avec validation
     */
    void update(Utilisateur utilisateur) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime un utilisateur par son ID
     */
    void deleteById(Long id) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Supprime un utilisateur
     */
    void delete(Utilisateur utilisateur) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Recherche un utilisateur par login
     */
    Optional<Utilisateur> findByLogin(String login);

    /**
     * Recherche un utilisateur par email
     */
    Optional<Utilisateur> findByEmail(String email);

    /**
     * Recherche des utilisateurs par nom
     */
    List<Utilisateur> findByNom(String nom);

    /**
     * Authentifie un utilisateur avec login et mot de passe
     */
    Optional<Utilisateur> authenticate(String login, String motDePasse) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Change le mot de passe d'un utilisateur
     */
    void changePassword(Long userId, String ancienMotDePasse, String nouveauMotDePasse) throws ma.dentalTech.common.exceptions.ServiceException;

    /**
     * Met à jour la date de dernière connexion
     */
    void updateLastLoginDate(Long userId);
}

