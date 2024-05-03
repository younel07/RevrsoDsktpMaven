package Dao;
/**
 * Cette classe représente une exception spécifique pour les erreurs liées à l'accès aux données dans la couche DAO.
 * Elle permet de spécifier un niveau de gravité pour chaque exception.
 *
 * @since 2024-03-24
 * @author Younes
 */
    public class DaoException extends Exception {

        private int severity;
    /**
     * Constructeur de l'exception avec un message et un niveau de gravité spécifiés.
     *
     * @param message   Le message décrivant l'exception.
     * @param p_severity Le niveau de gravité de l'exception.
     */
        public DaoException(String message, int p_severity) {
            super(message);
            this.severity = p_severity;
        }
    /**
     * Renvoie le niveau de gravité de l'exception.
     *
     * @return Le niveau de gravité de l'exception.
     */
        public int getSeverityLevel() {
            return severity;
        }
    }

