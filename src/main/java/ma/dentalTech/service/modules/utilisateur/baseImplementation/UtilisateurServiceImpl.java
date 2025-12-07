package ma.dentalTech.service.modules.utilisateur.baseImplementation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.common.exceptions.ServiceException;
import ma.dentalTech.common.exceptions.ValidationException;
import ma.dentalTech.common.utilitaire.Crypto;
import ma.dentalTech.common.validation.Validators;
import ma.dentalTech.entities.utilisateur.Utilisateur;
import ma.dentalTech.repository.modules.utilisateur.UtilisateurRepository;
import ma.dentalTech.service.modules.utilisateur.api.UtilisateurService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository repository;

    /**
     * Valide les données d'un utilisateur
     */
    private void validateUtilisateur(Utilisateur utilisateur) throws ServiceException {
        try {
            Validators.notBlank(utilisateur.nom, "Le nom");
            Validators.notBlank(utilisateur.login, "Le login");
            Validators.minLen(utilisateur.login, 3, "Le login");
            
            if (utilisateur.email != null && !utilisateur.email.trim().isEmpty()) {
                Validators.email(utilisateur.email);
            }
            
            if (utilisateur.tel != null && !utilisateur.tel.trim().isEmpty()) {
                Validators.phone(utilisateur.tel);
            }
            
            // Vérifier l'unicité du login
            if (utilisateur.idUser == null) { // Nouvel utilisateur
                // Le repository n'a pas findByLogin, on cherche dans findAll
                boolean loginExists = repository.findAll().stream()
                        .anyMatch(u -> u.login != null && u.login.equals(utilisateur.login));
                if (loginExists) {
                    throw new ValidationException("Ce login est déjà utilisé");
                }
            } else { // Mise à jour
                boolean loginExists = repository.findAll().stream()
                        .anyMatch(u -> u.login != null && u.login.equals(utilisateur.login) && 
                                     !u.idUser.equals(utilisateur.idUser));
                if (loginExists) {
                    throw new ValidationException("Ce login est déjà utilisé");
                }
            }
            
            // Vérifier l'unicité de l'email si fourni
            if (utilisateur.email != null && !utilisateur.email.trim().isEmpty()) {
                boolean emailExists = repository.findAll().stream()
                        .anyMatch(u -> u.email != null && u.email.equals(utilisateur.email) && 
                                     (utilisateur.idUser == null || !u.idUser.equals(utilisateur.idUser)));
                if (emailExists) {
                    throw new ValidationException("Cet email est déjà utilisé");
                }
            }
        } catch (ValidationException e) {
            throw new ServiceException("Erreur de validation : " + e.getMessage(), e);
        }
    }

    @Override
    public List<Utilisateur> findAll() {
        return repository.findAll();
    }

    @Override
    public Utilisateur findById(Long id) {
        if (id == null) {
            return null;
        }
        return repository.findById(id);
    }

    @Override
    public void create(Utilisateur utilisateur) throws ServiceException {
        if (utilisateur == null) {
            throw new ServiceException("L'utilisateur ne peut pas être null");
        }
        
        validateUtilisateur(utilisateur);
        
        // Hasher le mot de passe si fourni
        if (utilisateur.motDePasse != null && !utilisateur.motDePasse.trim().isEmpty()) {
            utilisateur.motDePasse = Crypto.hash(utilisateur.motDePasse);
        } else {
            throw new ServiceException("Le mot de passe est obligatoire");
        }
        
        try {
            repository.create(utilisateur);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la création de l'utilisateur : " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Utilisateur utilisateur) throws ServiceException {
        if (utilisateur == null) {
            throw new ServiceException("L'utilisateur ne peut pas être null");
        }
        
        if (utilisateur.idUser == null) {
            throw new ServiceException("L'ID de l'utilisateur est requis pour la mise à jour");
        }
        
        Utilisateur existing = repository.findById(utilisateur.idUser);
        if (existing == null) {
            throw new ServiceException("Utilisateur avec ID " + utilisateur.idUser + " introuvable");
        }
        
        validateUtilisateur(utilisateur);
        
        // Ne pas modifier le mot de passe lors d'une mise à jour normale
        // Utiliser changePassword pour changer le mot de passe
        utilisateur.motDePasse = existing.motDePasse;
        
        try {
            repository.update(utilisateur);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("L'ID ne peut pas être null");
        }
        
        Utilisateur utilisateur = repository.findById(id);
        if (utilisateur == null) {
            throw new ServiceException("Utilisateur avec ID " + id + " introuvable");
        }
        
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression de l'utilisateur : " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Utilisateur utilisateur) throws ServiceException {
        if (utilisateur == null) {
            throw new ServiceException("L'utilisateur ne peut pas être null");
        }
        deleteById(utilisateur.idUser);
    }

    @Override
    public Optional<Utilisateur> findByLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            return Optional.empty();
        }
        // Le repository n'a pas cette méthode, on cherche dans findAll
        return repository.findAll().stream()
                .filter(u -> u.login != null && u.login.equals(login.trim()))
                .findFirst();
    }

    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        // Le repository n'a pas cette méthode, on cherche dans findAll
        return repository.findAll().stream()
                .filter(u -> u.email != null && u.email.equals(email.trim()))
                .findFirst();
    }

    @Override
    public List<Utilisateur> findByNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            return List.of();
        }
        // Le repository n'a pas cette méthode, on filtre depuis findAll
        String searchTerm = nom.trim().toLowerCase();
        return repository.findAll().stream()
                .filter(u -> u.nom != null && u.nom.toLowerCase().contains(searchTerm))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Optional<Utilisateur> authenticate(String login, String motDePasse) throws ServiceException {
        if (login == null || login.trim().isEmpty()) {
            throw new ServiceException("Le login est obligatoire");
        }
        
        if (motDePasse == null || motDePasse.trim().isEmpty()) {
            throw new ServiceException("Le mot de passe est obligatoire");
        }
        
        Optional<Utilisateur> utilisateurOpt = findByLogin(login.trim());
        
        if (utilisateurOpt.isEmpty()) {
            return Optional.empty();
        }
        
        Utilisateur utilisateur = utilisateurOpt.get();
        
        if (utilisateur.motDePasse == null) {
            return Optional.empty();
        }
        
        if (Crypto.matches(motDePasse, utilisateur.motDePasse)) {
            updateLastLoginDate(utilisateur.idUser);
            return Optional.of(utilisateur);
        }
        
        return Optional.empty();
    }

    @Override
    public void changePassword(Long userId, String ancienMotDePasse, String nouveauMotDePasse) throws ServiceException {
        if (userId == null) {
            throw new ServiceException("L'ID de l'utilisateur est obligatoire");
        }
        
        if (nouveauMotDePasse == null || nouveauMotDePasse.trim().length() < 6) {
            throw new ServiceException("Le nouveau mot de passe doit contenir au moins 6 caractères");
        }
        
        Utilisateur utilisateur = repository.findById(userId);
        if (utilisateur == null) {
            throw new ServiceException("Utilisateur avec ID " + userId + " introuvable");
        }
        
        // Vérifier l'ancien mot de passe
        if (utilisateur.motDePasse == null || !Crypto.matches(ancienMotDePasse, utilisateur.motDePasse)) {
            throw new ServiceException("L'ancien mot de passe est incorrect");
        }
        
        // Hasher et mettre à jour le nouveau mot de passe
        utilisateur.motDePasse = Crypto.hash(nouveauMotDePasse);
        
        try {
            repository.update(utilisateur);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors du changement de mot de passe : " + e.getMessage(), e);
        }
    }

    @Override
    public void updateLastLoginDate(Long userId) {
        if (userId == null) {
            return;
        }
        
        try {
            Utilisateur utilisateur = repository.findById(userId);
            if (utilisateur != null) {
                utilisateur.lastLoginDate = LocalDate.now();
                repository.update(utilisateur);
            }
        } catch (Exception e) {
            // Log l'erreur mais ne pas la propager pour ne pas bloquer l'authentification
            System.err.println("Erreur lors de la mise à jour de la date de dernière connexion : " + e.getMessage());
        }
    }
}

