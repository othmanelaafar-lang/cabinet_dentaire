package ma.dentalTech.repository.modules.revenues;

import ma.dentalTech.entities.revenues.Revenues;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface RevenuesRepository extends CrudRepository<Revenues, Long> {
    // Trouver les revenus par date
    List<Revenues> findByDate(LocalDate date);
    
    // Trouver les revenus entre deux dates
    List<Revenues> findByDateBetween(LocalDate debut, LocalDate fin);
    
    // Calculer le total des revenus sur une période
    Double sumMontantByDateBetween(LocalDate debut, LocalDate fin);
    
    // Trouver les revenus par type
    List<Revenues> findByType(String type);
    
    // Calculer le total des revenus par type
    Double sumMontantByType(String type);
    
    // Trouver les revenus supérieurs à un montant
    List<Revenues> findByMontantGreaterThan(Double montant);
    
    // Compter le nombre de transactions par type
    long countByType(String type);
}
