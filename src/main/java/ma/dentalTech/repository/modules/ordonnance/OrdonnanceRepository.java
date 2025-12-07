package ma.dentalTech.repository.modules.ordonnance;

import ma.dentalTech.entities.ordonnance.Ordonnance;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdonnanceRepository extends CrudRepository<Ordonnance, Long> {
    // Trouver les ordonnances d'une consultation (nécessite un champ consultationId dans Ordonnance)
    List<Ordonnance> findByConsultationId(Long consultationId);
    
    // Trouver les ordonnances par date
    List<Ordonnance> findByDate(LocalDate date);
    
    // Trouver les ordonnances entre deux dates
    List<Ordonnance> findByDateRange(LocalDate dateDebut, LocalDate dateFin);
}
