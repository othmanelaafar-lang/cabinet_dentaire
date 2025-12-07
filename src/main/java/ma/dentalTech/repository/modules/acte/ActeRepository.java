package ma.dentalTech.repository.modules.acte;

import ma.dentalTech.entities.acte.Acte;
import ma.dentalTech.entities.enums.enPromo;
import ma.dentalTech.repository.common.CrudRepository;

import java.util.List;

public interface ActeRepository extends CrudRepository<Acte, Long> {
    // Trouver les actes par code
    List<Acte> findByCode(String code);
    
    // Trouver les actes en promotion
    List<Acte> findByEnPromo(enPromo enPromo);
    
    // Trouver les actes dont le prix est supérieur à un certain montant
    List<Acte> findByPrixGreaterThan(double prix);
    
    // Trouver les actes par catégorie
    List<Acte> findByCategorie(String categorie);
    
    // Mettre à jour le prix d'un acte
    void updatePrix(Long id, double nouveauPrix);
    
    // Compter le nombre d'actes par catégorie
    long countByCategorie(String categorie);
}
