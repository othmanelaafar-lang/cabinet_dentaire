package ma.dentalTech.repository.modules.cabinetmedicale;

import ma.dentalTech.entities.cabinetmedicale.CabinetMedicale;
import ma.dentalTech.repository.common.CrudRepository;

import java.util.Optional;

public interface CabinetMedicaleRepository extends CrudRepository<CabinetMedicale, Long> {
    // Trouver le cabinet par son nom
    Optional<CabinetMedicale> findByNom(String nom);
    
    // Vérifier si un cabinet existe avec ce numéro de téléphone
    boolean existsByTelephone(String telephone);
    
    // Vérifier si un cabinet existe avec cette adresse email
    boolean existsByEmail(String email);
    
    // Mettre à jour les informations de contact du cabinet
    void updateContactInfo(Long id, String telephone, String email, String adresse);
    
    // Mettre à jour les horaires d'ouverture
    void updateHoraires(Long id, String horaires);
    
    // Récupérer les informations de base du cabinet (nom, logo, etc.)
    CabinetMedicale findInfosBasiques();
}
