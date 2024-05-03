package entites;

/**
 * Exception personnalisée pour les erreurs liées aux entités.
 *
 * <p>Cette exception est utilisée pour signaler les erreurs spécifiques aux entités.</p>
 *
 * @since 2024-03-24
 */
public class EntitiesException extends Exception {
    private int severity;

    /**
     * Constructeur de l'exception.
     *
     * @param message    Le message d'erreur.
     * @param p_severity Le niveau de gravité de l'erreur.
     * @since 2024-03-24
     */
    public EntitiesException(String message, int p_severity) {
        super(message);
        this.severity = p_severity;
    }
}
