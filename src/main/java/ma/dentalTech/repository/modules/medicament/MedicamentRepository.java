package ma.dentalTech.repository.modules.medicament;

import ma.dentalTech.entities.medicament.Medicament;
import ma.dentalTech.entities.enums.forme;
import ma.dentalTech.repository.common.CrudRepository;

import java.util.List;

public interface MedicamentRepository extends CrudRepository<Medicament, Long> {
    // Trouver les médicaments par nom (recherche insensible à la casse)
    List<Medicament> findByNomContainingIgnoreCase(String nom);
    
    // Trouver les médicaments par forme (comprimé, sirop, etc.)
    List<Medicament> findByForme(forme forme);
    
    // Trouver les médicaments dont la quantité en stock est inférieure au seuil d'alerte
    List<Medicament> findByQuantiteStockLessThan(int seuilAlerte);
    
    // Vérifier si un médicament existe déjà avec ce nom et ce dosage
    boolean existsByNomAndDosage(String nom, String dosage);
    
    // Mettre à jour la quantité en stock d'un médicament
    void updateQuantiteStock(Long id, int nouvelleQuantite);
    
    // Trouver les médicaments par laboratoire
    List<Medicament> findByLaboratoire(String laboratoire);
    
    // Compter le nombre de médicaments en rupture de stock
    long countByQuantiteStock(int quantite);
}
