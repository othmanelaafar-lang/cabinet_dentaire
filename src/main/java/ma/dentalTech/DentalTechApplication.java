package ma.dentalTech;

/**
 * Classe principale de l'application Cabinet Dentaire
 * Cette application utilise un système de dépendances personnalisé via ApplicationContext
 * et ne dépend pas de Spring Boot
 */
public class DentalTechApplication {
    public static void main(String[] args) {
        System.out.println("=== Application Cabinet Dentaire ===");
        System.out.println("Application démarrée avec succès !");
        
        // Exemple d'utilisation du contexte d'application
        // Vous pouvez accéder aux beans via ApplicationContext.getBean()
        // Exemple:
        // PatientService patientService = ApplicationContext.getBean(PatientService.class);
        
        System.out.println("Application prête à être utilisée.");
    }
}
