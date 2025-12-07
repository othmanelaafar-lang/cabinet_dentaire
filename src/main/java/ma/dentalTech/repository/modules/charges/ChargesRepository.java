package ma.dentalTech.repository.modules.charges;

import ma.dentalTech.entities.charges.Charges;
import ma.dentalTech.entities.enums.Type;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ChargesRepository extends CrudRepository<Charges, Long> {
    // Trouver les charges par type
    List<Charges> findByType(Type type);
    
    // Trouver les charges entre deux dates
    List<Charges> findByDateBetween(LocalDate debut, LocalDate fin);
    
    // Calculer le total des charges par type
    Double sumMontantByType(Type type);
    
    // Calculer le total des charges sur une période
    Double sumMontantByDateBetween(LocalDate debut, LocalDate fin);
    
    // Trouver les charges supérieures à un montant
    List<Charges> findByMontantGreaterThan(Double montant);
    
    // Compter le nombre de charges par type
    long countByType(Type type);
}
