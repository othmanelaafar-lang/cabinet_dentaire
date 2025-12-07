package ma.dentalTech.repository.modules.antecedents;

import ma.dentalTech.entities.antecedents.Antecedents;
import ma.dentalTech.entities.enums.NiveauDeRisque;
import ma.dentalTech.repository.common.CrudRepository;

import java.util.List;

public interface AntecedentsRepository extends CrudRepository<Antecedents, Long> {
    // Trouver les antécédents par catégorie
    List<Antecedents> findByCategorie(String categorie);
    
    // Trouver les antécédents par niveau de risque
    List<Antecedents> findByNiveauDeRisque(NiveauDeRisque niveauDeRisque);
    
    // Vérifier si un antécédent existe déjà avec ce nom
    boolean existsByNom(String nom);
}
