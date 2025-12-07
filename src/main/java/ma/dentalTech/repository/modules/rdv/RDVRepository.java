package ma.dentalTech.repository.modules.rdv;

import ma.dentalTech.entities.rdv.RDV;
import ma.dentalTech.entities.enums.Statut;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RDVRepository extends CrudRepository<RDV, Long> {
    // Trouver les RDV par date
    List<RDV> findByDate(LocalDate date);
    
    // Trouver les RDV par statut
    List<RDV> findByStatut(Statut statut);
    
    // Trouver les RDV entre deux dates
    List<RDV> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Trouver les RDV par date et heure
    List<RDV> findByDateAndHeure(LocalDate date, LocalTime heure);
    
    // Compter les RDV par statut
    long countByStatut(Statut statut);
    
    // Vérifier si un RDV existe déjà à cette date et heure
    boolean existsByDateAndHeure(LocalDate date, LocalTime heure);
}
