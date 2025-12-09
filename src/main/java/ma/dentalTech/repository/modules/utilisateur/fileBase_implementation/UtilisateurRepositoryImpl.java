package ma.dentalTech.repository.modules.utilisateur.fileBase_implementation;

import ma.dentalTech.entities.utilisateur.Utilisateur;
import ma.dentalTech.repository.modules.utilisateur.UtilisateurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implémentation en mémoire (In-Memory) de UtilisateurRepository
 * Pour une implémentation file-based, voir PatientRepositoryImpl comme exemple
 */
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

    private final List<Utilisateur> utilisateurs = new ArrayList<>();
    private long nextId = 1;

    @Override
    public List<Utilisateur> findAll() {
        return new ArrayList<>(utilisateurs);
    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        return utilisateurs.stream()
                .filter(u -> u.idUser != null && u.idUser.equals(id))
                .findFirst();
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    public long count() {
        return utilisateurs.size();
    }

    @Override
    public <S extends Utilisateur> S save(S entity) {
        if (entity.idUser == null) {
            create(entity);
            return entity;
        } else {
            update(entity);
            return entity;
        }
    }

    @Override
    public <S extends Utilisateur> List<S> saveAll(Iterable<S> entities) {
        List<S> saved = new ArrayList<>();
        for (S entity : entities) {
            saved.add(save(entity));
        }
        return saved;
    }

    @Override
    public void create(Utilisateur utilisateur) {
        if (utilisateur.idUser == null) {
            utilisateur.idUser = nextId++;
        }
        utilisateurs.add(utilisateur);
    }

    @Override
    public <S extends Utilisateur> S update(S utilisateur) {
        for (int i = 0; i < utilisateurs.size(); i++) {
            if (utilisateurs.get(i).idUser != null && utilisateurs.get(i).idUser.equals(utilisateur.idUser)) {
                utilisateurs.set(i, utilisateur);
                return utilisateur;
            }
        }
        throw new RuntimeException("Utilisateur avec ID " + utilisateur.idUser + " introuvable");
    }

    @Override
    public void deleteById(Long id) {
        utilisateurs.removeIf(u -> u.idUser != null && u.idUser.equals(id));
    }

    @Override
    public void delete(Utilisateur utilisateur) {
        if (utilisateur != null && utilisateur.idUser != null) {
            deleteById(utilisateur.idUser);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Utilisateur> entities) {
        List<Long> idsToDelete = new ArrayList<>();
        for (Utilisateur entity : entities) {
            if (entity != null && entity.idUser != null) {
                idsToDelete.add(entity.idUser);
            }
        }
        utilisateurs.removeIf(u -> u.idUser != null && idsToDelete.contains(u.idUser));
    }

    @Override
    public void deleteAll() {
        utilisateurs.clear();
    }

    @Override
    public Optional<Utilisateur> findByLogin(String login) {
        if (login == null) {
            return Optional.empty();
        }
        return utilisateurs.stream()
                .filter(u -> u.login != null && u.login.equals(login.trim()))
                .findFirst();
    }

    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }
        return utilisateurs.stream()
                .filter(u -> u.email != null && u.email.equals(email.trim()))
                .findFirst();
    }

    @Override
    public List<Utilisateur> findByNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            return List.of();
        }
        String searchTerm = nom.trim().toLowerCase();
        return utilisateurs.stream()
                .filter(u -> u.nom != null && u.nom.toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }
}

