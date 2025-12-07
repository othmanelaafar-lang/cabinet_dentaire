package ma.dentalTech.repository.modules.notification;

import ma.dentalTech.entities.notification.Notification;
import ma.dentalTech.entities.enums.Statut;
import ma.dentalTech.repository.common.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    // Trouver les notifications par statut (lu/non lu)
    List<Notification> findByStatut(Statut statut);
    
    // Trouver les notifications par destinataire
    List<Notification> findByDestinataireId(Long destinataireId);
    
    // Trouver les notifications non lues pour un utilisateur
    List<Notification> findByDestinataireIdAndStatut(Long destinataireId, Statut statut);
    
    // Trouver les notifications entre deux dates
    List<Notification> findByDateCreationBetween(LocalDateTime debut, LocalDateTime fin);
    
    // Compter le nombre de notifications non lues pour un utilisateur
    long countByDestinataireIdAndStatut(Long destinataireId, Statut statut);
    
    // Marquer une notification comme lue
    void marquerCommeLue(Long notificationId);
    
    // Supprimer les notifications plus anciennes qu'une certaine date
    void deleteByDateCreationBefore(LocalDateTime date);
}
