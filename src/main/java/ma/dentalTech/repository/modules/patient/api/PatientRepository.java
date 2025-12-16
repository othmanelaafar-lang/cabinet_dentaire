package ma.dentalTech.repository.modules.patient.api;

import ma.dentalTech.entities.patient.Patient;
import ma.dentalTech.repository.common.CrudRepository;

/**
 * Interface API pour PatientRepository
 * Cette interface est utilisée par ApplicationContext
 */
public interface PatientRepository extends CrudRepository<Patient, Long> {
    // Les méthodes spécifiques sont définies dans le repository principal
    // Cette interface sert de point d'entrée pour l'injection de dépendances
}
