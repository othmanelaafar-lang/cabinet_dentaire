package ma.dentalTech.repository.common;

import java.util.List;
import java.util.Optional;

/**
 * Interface générique pour les opérations CRUD sur les entités.
 *
 * @param <T>  le type de l'entité
 * @param <ID> le type de l'identifiant de l'entité
 */
public interface CrudRepository<T, ID> {

    /**
     * Récupère toutes les entités.
     *
     * @return une liste de toutes les entités
     */
    List<T> findAll();

    /**
     * Récupère une entité par son identifiant.
     *
     * @param id l'identifiant de l'entité à récupérer
     * @return l'entité correspondante, ou null si aucune entité n'est trouvée
     */
    Optional<T> findById(ID id);

    /**
     * Vérifie si une entité avec l'identifiant spécifié existe.
     *
     * @param id l'identifiant à vérifier
     * @return true si une entité avec l'identifiant existe, false sinon
     */
    boolean existsById(ID id);

    /**
     * Compte le nombre total d'entités.
     *
     * @return le nombre total d'entités
     */
    long count();

    /**
     * Crée une nouvelle entité.
     *
     * @param entity l'entité à créer
     */
    void create(T entity);

    /**
     * Enregistre une nouvelle entité.
     *
     * @param entity l'entité à enregistrer
     * @return l'entité enregistrée (avec un identifiant généré si nécessaire)
     */
    <S extends T> S save(S entity);

    /**
     * Enregistre toutes les entités fournies.
     *
     * @param entities les entités à enregistrer
     * @return la liste des entités enregistrées
     */
    <S extends T> List<S> saveAll(Iterable<S> entities);

    /**
     * Met à jour une entité existante.
     *
     * @param entity l'entité à mettre à jour
     * @return l'entité mise à jour
     */
    <S extends T> S update(S entity);

    /**
     * Supprime l'entité avec l'identifiant spécifié.
     *
     * @param id l'identifiant de l'entité à supprimer
     */
    void deleteById(ID id);

    /**
     * Supprime l'entité spécifiée.
     *
     * @param entity l'entité à supprimer
     */
    void delete(T entity);

    /**
     * Supprime toutes les entités spécifiées.
     *
     * @param entities les entités à supprimer
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Supprime toutes les entités.
     */
    void deleteAll();
}
