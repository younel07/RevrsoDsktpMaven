package logs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Cette classe représente un formateur personnalisé pour les journaux (logs).
 * Elle étend la classe java.util.logging.Formatter et fournit une mise en forme personnalisée
 * pour les enregistrements de logs.
 *
 * @author Younes
 * @since 2024-03-24
 */
public class FormatterLog extends Formatter {

    /**
     * Méthode pour formater un enregistrement de log.
     *
     * @param record L'enregistrement de log à formater.
     * @return Une chaîne de caractères représentant l'enregistrement de log formaté.
     */
    @Override
    public String format(LogRecord record) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder result = new StringBuilder();

        // Date et heure du log
        result.append(format.format(new Date()));
        result.append(" Level : ");
        result.append(record.getLevel());

        // Message du log
        result.append(" / Message : ");
        result.append(record.getMessage());

        // Méthode source du log
        result.append(" / Méthode : ");
        result.append(record.getSourceMethodName());

        // Classe source du log
        result.append(" / Classe : ");
        result.append(record.getSourceClassName());

        // Nouvelle ligne pour séparer les logs
        result.append("\n");

        return result.toString();
    }
}
