package logs;

import java.util.logging.Logger;

/**
 * Cette classe fournit une instance de Logger pour le logging (journalisation) dans l'application.
 * Elle utilise la configuration par défaut pour le Logger.
 *
 * @author Younes
 * @since 2024-03-24
 */
public class MyLogger {

    /**
     * Logger utilisé pour la journalisation.
     */
    public static final Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
}
