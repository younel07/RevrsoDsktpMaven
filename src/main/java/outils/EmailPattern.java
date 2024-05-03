package outils;

import java.util.regex.Pattern;

/**
 * Cette classe fournit une méthode pour valider un format d'adresse e-mail à l'aide d'une expression régulière.
 *
 * @author Younes
 * @version 1.0
 * @since 27/02/2024
 */
public class EmailPattern {

    /**
     * Le motif regex utilisé pour valider les adresses e-mail.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    /**
     * Vérifie si l'adresse e-mail spécifiée correspond au format attendu.
     *
     * @param email L'adresse e-mail à valider.
     * @return true si l'adresse e-mail est valide, false sinon.
     */
    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

}
