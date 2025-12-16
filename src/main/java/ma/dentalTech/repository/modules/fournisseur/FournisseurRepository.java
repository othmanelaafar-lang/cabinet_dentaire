package ma.dentalTech.repository.modules.fournisseur;

import ma.dentalTech.entities.fournisseur.Fournisseur;
import ma.dentalTech.repository.common.CrudRepository;

import java.util.List;

public interface FournisseurRepository extends CrudRepository<Fournisseur, Long> {
    // Trouver les fournisseurs actifs
    List<Fournisseur> findByActif(Boolean actif);
    
    // Trouver un fournisseur par nom
    List<Fournisseur> findByNom(String nom);
    
    // Trouver un fournisseur par email
    Fournisseur findByEmail(String email);
}

