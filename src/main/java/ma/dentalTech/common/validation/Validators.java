package ma.dentalTech.common.validation;

import java.util.regex.Pattern;
import ma.dentalTech.common.exceptions.ValidationException;

/**
 * Classe utilitaire pour la validation des données
 * Toutes les méthodes lancent une ValidationException si la validation échoue
 */
public final class Validators {
    // Pattern pour validation email (format basique)
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    // Pattern pour validation téléphone (accepte les formats internationaux)
    private static final Pattern PHONE = Pattern.compile("^[0-9+\\-\\s()]{6,20}$");
    
    // Pattern pour validation CIN (format marocain)
    private static final Pattern CIN = Pattern.compile("^[A-Z]{1,2}[0-9]{5,6}$");

    private Validators() {
        // Classe utilitaire, pas d'instanciation
    }

    /**
     * Vérifie qu'une chaîne n'est pas null, vide ou composée uniquement d'espaces
     * @param v la chaîne à valider
     * @param field le nom du champ (pour le message d'erreur)
     * @throws ValidationException si la chaîne est null, vide ou composée uniquement d'espaces
     */
    public static void notBlank(String v, String field) throws ValidationException {
        if (v == null || v.trim().isEmpty()) {
            throw new ValidationException(field + " est obligatoire");
        }
    }

    /**
     * Vérifie qu'une chaîne est un email valide
     * @param v l'email à valider
     * @throws ValidationException si l'email est invalide
     */
    public static void email(String v) throws ValidationException {
        if (v != null && !v.trim().isEmpty() && !EMAIL.matcher(v.trim()).matches()) {
            throw new ValidationException("Email invalide : " + v);
        }
    }

    /**
     * Vérifie qu'une chaîne est un numéro de téléphone valide
     * @param v le numéro de téléphone à valider
     * @throws ValidationException si le numéro de téléphone est invalide
     */
    public static void phone(String v) throws ValidationException {
        if (v != null && !v.trim().isEmpty() && !PHONE.matcher(v.trim()).matches()) {
            throw new ValidationException("Téléphone invalide : " + v);
        }
    }

    /**
     * Vérifie qu'une chaîne a une longueur minimale
     * @param v la chaîne à valider
     * @param n la longueur minimale requise
     * @param field le nom du champ (pour le message d'erreur)
     * @throws ValidationException si la chaîne est null ou plus courte que n
     */
    public static void minLen(String v, int n, String field) throws ValidationException {
        if (v == null || v.length() < n) {
            throw new ValidationException(field + " doit contenir au moins " + n + " caractères");
        }
    }

    /**
     * Vérifie qu'une chaîne a une longueur maximale
     * @param v la chaîne à valider
     * @param n la longueur maximale autorisée
     * @param field le nom du champ (pour le message d'erreur)
     * @throws ValidationException si la chaîne est plus longue que n
     */
    public static void maxLen(String v, int n, String field) throws ValidationException {
        if (v != null && v.length() > n) {
            throw new ValidationException(field + " ne doit pas dépasser " + n + " caractères");
        }
    }

    /**
     * Vérifie qu'une chaîne a une longueur comprise entre min et max
     * @param v la chaîne à valider
     * @param min la longueur minimale
     * @param max la longueur maximale
     * @param field le nom du champ (pour le message d'erreur)
     * @throws ValidationException si la longueur n'est pas dans la plage [min, max]
     */
    public static void length(String v, int min, int max, String field) throws ValidationException {
        if (v == null) {
            throw new ValidationException(field + " est obligatoire");
        }
        if (v.length() < min || v.length() > max) {
            throw new ValidationException(field + " doit contenir entre " + min + " et " + max + " caractères");
        }
    }

    /**
     * Vérifie qu'une valeur numérique est positive
     * @param value la valeur à valider
     * @param field le nom du champ (pour le message d'erreur)
     * @throws ValidationException si la valeur est négative
     */
    public static void positive(double value, String field) throws ValidationException {
        if (value < 0) {
            throw new ValidationException(field + " ne peut pas être négatif");
        }
    }

    /**
     * Vérifie qu'une valeur numérique est strictement positive
     * @param value la valeur à valider
     * @param field le nom du champ (pour le message d'erreur)
     * @throws ValidationException si la valeur est négative ou nulle
     */
    public static void positiveStrict(double value, String field) throws ValidationException {
        if (value <= 0) {
            throw new ValidationException(field + " doit être strictement positif");
        }
    }

    /**
     * Vérifie qu'une valeur est dans une plage donnée
     * @param value la valeur à valider
     * @param min la valeur minimale
     * @param max la valeur maximale
     * @param field le nom du champ (pour le message d'erreur)
     * @throws ValidationException si la valeur n'est pas dans la plage [min, max]
     */
    public static void range(double value, double min, double max, String field) throws ValidationException {
        if (value < min || value > max) {
            throw new ValidationException(field + " doit être entre " + min + " et " + max);
        }
    }

    /**
     * Vérifie qu'une chaîne est un CIN valide (format marocain)
     * @param v le CIN à valider
     * @throws ValidationException si le CIN est invalide
     */
    public static void cin(String v) throws ValidationException {
        if (v != null && !v.trim().isEmpty() && !CIN.matcher(v.trim().toUpperCase()).matches()) {
            throw new ValidationException("CIN invalide : " + v);
        }
    }

    /**
     * Vérifie qu'un objet n'est pas null
     * @param obj l'objet à valider
     * @param field le nom du champ (pour le message d'erreur)
     * @throws ValidationException si l'objet est null
     */
    public static void notNull(Object obj, String field) throws ValidationException {
        if (obj == null) {
            throw new ValidationException(field + " ne peut pas être null");
        }
    }
}
