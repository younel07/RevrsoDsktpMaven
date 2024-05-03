package Dao;

import logs.MyLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Cette classe gère la connexion à la base de données.
 * Elle récupère les informations de connexion à partir d'un fichier de propriétés et établit la connexion à la base de données.
 * Elle gère également la fermeture de la connexion à l'arrêt de l'application.
 * Le fichier de propriétés doit contenir les clés "url", "username" et "password" pour la connexion à la base de données.
 *
 * @author Younes
 * @version 1.0
 * @since 2024-03-24
 */
public class Connexion {
    private static Connection connection;

    /**
     * Initialise et établit la connexion à la base de données.
     *
     * @return La connexion établie à la base de données.
     * @throws Exception si une erreur survient lors de la connexion.
     */
    public static Connection startConnection() throws Exception {
        Properties dataProperties = new Properties();
        File fichier = new File("src/main/databaseClientProspect.properties");

        if (fichier.exists()==false) {
            throw new DaoException("Le fichier de propriétés 'databaseClientProspect.properties' n'existe pas", 3);
        }

        try (FileInputStream input = new FileInputStream(fichier)) {
            dataProperties.load(input);

            String url = dataProperties.getProperty("url");
            String username = dataProperties.getProperty("username");
            String password = dataProperties.getProperty("password");

            // La connexion sera fermée automatiquement si une exception se produit dans le bloc try-with-resources
            connection = DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur lors de la lecture du fichier de propriétés", e.getMessage());
            throw new DaoException("Erreur est survenue veuillez réessayer ultérieurement", 3);
        } catch (SQLException sqe) {
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur de connexion", sqe.getMessage());
            throw new DaoException("La base de données ne peut pas s'ouvrir : problème de connexion", 3);
        }
        return connection;
    }

    /**
     * Ferme la connexion à la base de données lors de l'arrêt de l'application.
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (connection != null) {
                    try {
                        MyLogger.LOGGER.info("Base de données fermée");
                        connection.close();
                    } catch (SQLException ex) {
                        MyLogger.LOGGER.log(Level.SEVERE, "Erreur de fermeture de la connexion", ex.getMessage());
                    }
                }
            }
        });
    }
}
