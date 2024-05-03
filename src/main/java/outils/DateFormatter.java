package outils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Cette classe fournit des méthodes pour formater et convertir des dates.
 *
 * @author Younes
 * @version 1.0
 * @since 27/02/2024
 *
 */
public class DateFormatter {
    /**
     * Le formateur de date utilisé pour formater et parser les dates.
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /**
     * Renvoie une représentation sous forme de chaîne de caractères de la date spécifiée
     * formatée selon le modèle "jj/mm/aaaa".
     *
     * @param date La date à formater.
     * @return La date formatée sous forme de chaîne de caractères.
     */
    public static String afficherDate (LocalDate date){
        return date.format(DateFormatter.DATE_FORMATTER);
    }
    /**
     * Convertit une chaîne de caractères représentant une date au format "jj/mm/aaaa"
     * en un objet LocalDate.
     *
     * @param strDate La chaîne de caractères représentant la date à convertir.
     * @return L'objet LocalDate correspondant à la date convertie.
     * @throws DateTimeParseException Si la chaîne de caractères n'est pas au format valide.
     */
    public static LocalDate convertirDate (String strDate){
        return LocalDate.parse(strDate, DateFormatter.DATE_FORMATTER);
    }

}
