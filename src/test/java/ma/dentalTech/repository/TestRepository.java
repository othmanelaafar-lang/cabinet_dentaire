package ma.dentalTech.repository;

import ma.dentalTech.conf.ApplicationContext;
import ma.dentalTech.entities.enums.Assurance;
import ma.dentalTech.entities.enums.Sexe;
import ma.dentalTech.entities.patient.Patient;
import ma.dentalTech.repository.modules.patient.api.PatientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Classe de test pour les repositories
 * Teste les opérations CRUD de base sur PatientRepository
 */
public class TestRepository {

    private static PatientRepository patientRepository;

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   TEST DES REPOSITORIES");
        System.out.println("==========================================\n");

        try {
            // Récupération du repository depuis ApplicationContext
            patientRepository = ApplicationContext.getBean(PatientRepository.class);
            
            if (patientRepository == null) {
                System.err.println("ERREUR: Impossible de récupérer PatientRepository depuis ApplicationContext");
                System.err.println("Vérifiez que le fichier config/beans.properties est correctement configuré.");
                return;
            }

            System.out.println("✓ Repository récupéré avec succès\n");

            // Exécution des tests
            testFindAll();
            testFindById();
            testCreate();
            testUpdate();
            testCount();
            testExistsById();
            testDelete();
            testSave();

            System.out.println("\n==========================================");
            System.out.println("   TOUS LES TESTS SONT TERMINÉS");
            System.out.println("==========================================");

        } catch (Exception e) {
            System.err.println("ERREUR lors des tests: " + e.getMessage());
            e.printStackTrace();
            System.err.println("\nVérifiez que:");
            System.err.println("1. Le fichier config/beans.properties existe");
            System.err.println("2. La classe PatientRepositoryImpl est correctement configurée");
            System.err.println("3. Le fichier fileBase/patients.psv existe dans src/main/resources");
        }
    }

    /**
     * Test de la méthode findAll()
     */
    private static void testFindAll() {
        System.out.println("--- Test findAll() ---");
        try {
            List<Patient> patients = patientRepository.findAll();
            System.out.println("✓ Nombre de patients trouvés: " + patients.size());
            if (!patients.isEmpty()) {
                Patient premier = patients.get(0);
                System.out.println("  Premier patient: " + premier.getNom() + " " + premier.getPrenom());
                System.out.println("  ID: " + premier.getId());
            } else {
                System.out.println("  ⚠ Aucun patient trouvé dans la base de données");
            }
        } catch (Exception e) {
            System.err.println("✗ ERREUR dans findAll(): " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * Test de la méthode findById()
     */
    private static void testFindById() {
        System.out.println("--- Test findById() ---");
        try {
            // Test avec un ID existant (si des patients existent)
            List<Patient> allPatients = patientRepository.findAll();
            if (!allPatients.isEmpty()) {
                Long firstId = allPatients.get(0).getId();
                Optional<Patient> patient = patientRepository.findById(firstId);
                if (patient.isPresent()) {
                    Patient p = patient.get();
                    System.out.println("✓ Patient trouvé avec ID " + firstId + ": " + 
                                     p.getNom() + " " + p.getPrenom());
                } else {
                    System.out.println("✗ Patient avec ID " + firstId + " non trouvé");
                }
            } else {
                System.out.println("⚠ Aucun patient trouvé pour tester findById()");
            }

            // Test avec un ID inexistant
            Optional<Patient> notFound = patientRepository.findById(99999L);
            if (notFound.isEmpty()) {
                System.out.println("✓ ID inexistant correctement géré (Optional.empty)");
            } else {
                System.out.println("✗ ID inexistant retourne un patient (inattendu)");
            }
        } catch (Exception e) {
            System.err.println("✗ ERREUR dans findById(): " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * Test de la méthode create()
     */
    private static void testCreate() {
        System.out.println("--- Test create() ---");
        try {
            Patient nouveauPatient = Patient.builder()
                    .nom("TEST")
                    .prenom("Repository")
                    .adresse("123 Rue Test")
                    .telephone("0612345678")
                    .email("test.repository@example.com")
                    .dateNaissance(LocalDate.of(1990, 5, 15))
                    .sexe(Sexe.Homme)
                    .assurance(Assurance.CNOPS)
                    .build();

            patientRepository.create(nouveauPatient);
            System.out.println("✓ Nouveau patient créé avec ID: " + nouveauPatient.getId());
            System.out.println("  Nom: " + nouveauPatient.getNom() + " " + nouveauPatient.getPrenom());
            
            // Nettoyer: supprimer le patient de test créé
            if (nouveauPatient.getId() != null) {
                patientRepository.deleteById(nouveauPatient.getId());
                System.out.println("✓ Patient de test supprimé (nettoyage)");
            }
        } catch (Exception e) {
            System.err.println("✗ ERREUR dans create(): " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * Test de la méthode update()
     */
    private static void testUpdate() {
        System.out.println("--- Test update() ---");
        try {
            // Trouver un patient existant
            List<Patient> patients = patientRepository.findAll();
            if (!patients.isEmpty()) {
                Patient patient = patients.get(0);
                String ancienNom = patient.getNom();
                String ancienEmail = patient.getEmail();
                
                // Modifier le patient
                patient.setNom("MODIFIE");
                patient.setEmail("modifie@example.com");
                
                Patient updated = patientRepository.update(patient);
                System.out.println("✓ Patient mis à jour avec succès");
                System.out.println("  Ancien nom: " + ancienNom);
                System.out.println("  Nouveau nom: " + updated.getNom());
                
                // Restaurer les valeurs originales pour ne pas affecter les autres tests
                patient.setNom(ancienNom);
                patient.setEmail(ancienEmail);
                patientRepository.update(patient);
                System.out.println("✓ Valeurs originales restaurées");
            } else {
                System.out.println("⚠ Aucun patient trouvé pour tester update()");
            }
        } catch (Exception e) {
            System.err.println("✗ ERREUR dans update(): " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * Test de la méthode count()
     */
    private static void testCount() {
        System.out.println("--- Test count() ---");
        try {
            long count = patientRepository.count();
            System.out.println("✓ Nombre total de patients: " + count);
            
            // Vérifier la cohérence avec findAll()
            long countFromFindAll = patientRepository.findAll().size();
            if (count == countFromFindAll) {
                System.out.println("✓ Cohérence vérifiée: count() == findAll().size()");
            } else {
                System.out.println("⚠ Incohérence: count()=" + count + " mais findAll().size()=" + countFromFindAll);
            }
        } catch (Exception e) {
            System.err.println("✗ ERREUR dans count(): " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * Test de la méthode existsById()
     */
    private static void testExistsById() {
        System.out.println("--- Test existsById() ---");
        try {
            List<Patient> patients = patientRepository.findAll();
            if (!patients.isEmpty()) {
                Long firstId = patients.get(0).getId();
                boolean exists = patientRepository.existsById(firstId);
                if (exists) {
                    System.out.println("✓ Patient avec ID " + firstId + " existe");
                } else {
                    System.out.println("✗ Patient avec ID " + firstId + " n'existe pas (inattendu)");
                }
            } else {
                System.out.println("⚠ Aucun patient trouvé pour tester existsById()");
            }

            // Test avec un ID inexistant
            boolean notExists = patientRepository.existsById(99999L);
            if (!notExists) {
                System.out.println("✓ ID inexistant correctement détecté (false)");
            } else {
                System.out.println("✗ ID inexistant retourne true (inattendu)");
            }
        } catch (Exception e) {
            System.err.println("✗ ERREUR dans existsById(): " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * Test de la méthode delete()
     */
    private static void testDelete() {
        System.out.println("--- Test delete() ---");
        try {
            // Créer un patient de test pour le supprimer
            Patient patientTest = Patient.builder()
                    .nom("DELETE")
                    .prenom("Test")
                    .adresse("Rue Delete")
                    .telephone("0699999999")
                    .email("delete.test@example.com")
                    .dateNaissance(LocalDate.of(2000, 1, 1))
                    .sexe(Sexe.Femme)
                    .assurance(Assurance.Autre)
                    .build();

            patientRepository.create(patientTest);
            Long idToDelete = patientTest.getId();
            System.out.println("✓ Patient de test créé avec ID: " + idToDelete);

            // Vérifier qu'il existe
            boolean existsBefore = patientRepository.existsById(idToDelete);
            System.out.println("  Existe avant suppression: " + existsBefore);

            // Supprimer
            patientRepository.deleteById(idToDelete);
            System.out.println("✓ Patient supprimé");

            // Vérifier qu'il n'existe plus
            boolean existsAfter = patientRepository.existsById(idToDelete);
            if (!existsAfter) {
                System.out.println("✓ Vérification: patient n'existe plus après suppression");
            } else {
                System.out.println("✗ ERREUR: patient existe encore après suppression");
            }
        } catch (Exception e) {
            System.err.println("✗ ERREUR dans delete(): " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * Test de la méthode save()
     */
    private static void testSave() {
        System.out.println("--- Test save() ---");
        try {
            // Test save() pour créer un nouveau patient (sans ID)
            Patient nouveauPatient = Patient.builder()
                    .nom("SAVE")
                    .prenom("Test")
                    .adresse("Rue Save")
                    .telephone("0688888888")
                    .email("save.test@example.com")
                    .dateNaissance(LocalDate.of(1995, 3, 20))
                    .sexe(Sexe.Homme)
                    .assurance(Assurance.CNSS)
                    .build();

            Patient saved = patientRepository.save(nouveauPatient);
            System.out.println("✓ Patient sauvegardé avec save()");
            System.out.println("  ID généré: " + saved.getId());
            System.out.println("  Nom: " + saved.getNom() + " " + saved.getPrenom());

            // Nettoyer: supprimer le patient de test
            if (saved.getId() != null) {
                patientRepository.deleteById(saved.getId());
                System.out.println("✓ Patient de test supprimé (nettoyage)");
            }
        } catch (Exception e) {
            System.err.println("✗ ERREUR dans save(): " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }
}

