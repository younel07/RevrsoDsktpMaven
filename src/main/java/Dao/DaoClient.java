package Dao;

import entites.Client;
import entites.EntitiesException;
import logs.MyLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Cette classe fournit les méthodes d'accès aux données pour les objets de type Client.
 * Elle permet de récupérer, insérer, mettre à jour et supprimer des enregistrements de la table CLIENT.
 * La classe gère également les exceptions liées à l'accès aux données.
 *
 * @author Younes
 * @version 1.0
 * @since 2024-03-24
 */
public class DaoClient {
    /**
     * Récupère la liste complète des clients depuis la base de données.
     *
     * @return La liste des clients.
     * @throws Exception si une erreur survient lors de la récupération des données ou de l'instanciation des objets Client.
     * @see Client
     */
    public static ArrayList<Client> findAllClient() throws Exception {
        Statement statement = null;
        String sql = "SELECT * FROM CLIENT ORDER BY RAISONSOCIAL ASC";
        ArrayList<Client> clients = new ArrayList<>();

        Connection con = Connexion.startConnection();

        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
            int idClient = rs.getInt("IDCLIENT");
            String raisonSocial = rs.getString("RAISONSOCIAL");
            String numRue = rs.getString("NUMRUE");
            String nomRue = rs.getString("NOMRUE");
            String cdPostal = rs.getString("CDPOSTAL");
            String ville = rs.getString("ville");
            String telephone = rs.getString("TELEPHONE");
            String mail = rs.getString("MAIL");
            String commentaire = rs.getString("COMMENTAIRES");
            double chiffreAffaire = rs.getDouble("CHIFFREAFFAIRES");
            int nbrEmployes = rs.getInt("NBREMPLOYES");

                Client client = new Client(idClient,raisonSocial,numRue,nomRue,cdPostal,ville,
                        telephone,mail,commentaire,chiffreAffaire,nbrEmployes);
                clients.add(client);
            }

        } catch (SQLException e){
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur sql pour recuperer la bdd clients: "+e.getMessage(), e);
            throw new DaoException ("Erreur pour recuperer la liste des clients", 3);
        } catch (EntitiesException e){
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur sql pour recuperer la bdd client: "+e.getMessage(), e);
            throw new EntitiesException ("Erreur pour instencier le client",3);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    return clients;
    }

    /**
     * Récupère un client depuis la base de données en fonction de sa raison sociale.
     *
     * @param filterRaisonSocial La raison sociale du client à rechercher.
     * @return Le client correspondant à la raison sociale spécifiée.
     * @throws Exception si une erreur survient lors de la recherche du client ou lors de l'instanciation de l'objet Client.
     * @throws DaoException si le client spécifié n'est pas trouvé dans la base de données.
     * @see Client
     */

    public static Client findByNameClient(String filterRaisonSocial) throws Exception {
        PreparedStatement findByNameClient = null;
        String sql = "SELECT * FROM client WHERE RAISONSOCIAL = ?";

        Connection con = Connexion.startConnection();

        try {
            findByNameClient = con.prepareStatement(sql);
            findByNameClient.setString(1, filterRaisonSocial);
            ResultSet rs = findByNameClient.executeQuery();

            if (rs.next()) {
                int idClient = rs.getInt("IDCLIENT");
                String raisonSocial = rs.getString("RAISONSOCIAL");
                String numRue = rs.getString("NUMRUE");
                String nomRue = rs.getString("NOMRUE");
                String cdPostal = rs.getString("CDPOSTAL");
                String ville = rs.getString("ville");
                String telephone = rs.getString("TELEPHONE");
                String mail = rs.getString("MAIL");
                String commentaire = rs.getString("COMMENTAIRES");
                double chiffreAffaire = rs.getDouble("CHIFFREAFFAIRES");
                int nbrEmployes = rs.getInt("NBREMPLOYES");

                return new Client(idClient,raisonSocial,numRue,nomRue,cdPostal,ville,
                        telephone,mail,commentaire,chiffreAffaire,nbrEmployes);

            }else {
                throw new DaoException("Impossible de trouver ce client", 2);
            }

        }catch (Exception e){
            throw e;
        }
        finally {
            if (findByNameClient !=null){findByNameClient.close();}
        }

    }

    /**
     * Crée un nouveau client dans la base de données.
     *
     * @param client Le client à créer.
     * @throws Exception si une erreur survient lors de la création du client.
     * @throws DaoException si une erreur spécifique à la base de données survient lors de la création du client,
     *                       comme une violation de contrainte d'unicité ou une longueur de champ dépassée.
     * @see Client
     */
    public static void creatClient (Client client) throws Exception {
        PreparedStatement creatClient = null;
        String sql = "INSERT INTO client (`RAISONSOCIAL`, `NUMRUE`, `NOMRUE`, `CDPOSTAL`, `VILLE`, \n" +
                "`TELEPHONE`, `MAIL`, `COMMENTAIRES`, `CHIFFREAFFAIRES`, `NBREMPLOYES`) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?);";
        Connection con = Connexion.startConnection();

        try {
            con.setAutoCommit(false);// debut de transaction (false to enable the rollback and commit)
            creatClient =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            creatClient.setString(1, client.getRaisonSociale());
            creatClient.setString(2, client.getNumRue());
            creatClient.setString(3, client.getNomRue());
            creatClient.setString(4, client.getCdPostal());
            creatClient.setString(5, client.getVille());
            creatClient.setString(6, client.getTelephone());
            creatClient.setString(7, client.getMail());
            creatClient.setString(8, client.getCommentaire());
            creatClient.setDouble(9, client.getChiffreAffaire());
            creatClient.setInt(10, client.getNbrEmploye());

            creatClient.executeUpdate();

            con.commit();

            //rollback transaction on error
        }catch (SQLException ex){
            if (con !=null) {
                MyLogger.LOGGER.log(Level.SEVERE, "Transaction create client rolled back, cause: "+
                        ex.getMessage(), ex);
                con.rollback();
            }
            if (ex.getErrorCode()==1062){
                throw new DaoException ("Raison social existe déja", 2);
            }
            else if (ex.getErrorCode()==1406)
            {
                int startIndex = ex.getLocalizedMessage().lastIndexOf("column '");
                int lastIndex = ex.getLocalizedMessage().indexOf("' at row 1");
                String colonnName = ex.getLocalizedMessage().substring(startIndex +8,lastIndex);

                throw new DaoException("Trop de caractères dans le "+colonnName,2);
            }
            else {MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage()+" erreur: "+ex.getErrorCode(), ex);
                throw new SQLException(ex.getMessage());}

        } finally {
            if (creatClient !=null){
                creatClient.close();
            }
            //This ensures that we don't try to call setAutoCommit(true) on a null object.
            if (con != null) {
                con.setAutoCommit(true);
            }
        }
    }

    /**
     * Met à jour les informations d'un client dans la base de données.
     *
     * @param client Le client avec les informations mises à jour.
     * @throws Exception si une erreur survient lors de la mise à jour du client.
     * @throws DaoException si une erreur spécifique à la base de données survient lors de la mise à jour du client,
     *                       comme une violation de contrainte d'unicité ou une longueur de champ dépassée.
     * @see Client
     */

    public static void updateClient (Client client) throws Exception {
        PreparedStatement updateClient = null;
        String sql = "UPDATE client SET RAISONSOCIAL=?, NUMRUE=?, NOMRUE=?, CDPOSTAL=?, VILLE=?, " +
                "TELEPHONE=?, MAIL=?, COMMENTAIRES=?, CHIFFREAFFAIRES=?, NBREMPLOYES=? WHERE IDCLIENT=?";

        Connection con = Connexion.startConnection();

        try {
            con.setAutoCommit(false);// Begin transaction (false to enable the rollback and commit)
            updateClient = con.prepareStatement(sql);

            updateClient.setString(1, client.getRaisonSociale());
            updateClient.setString(2, client.getNumRue());
            updateClient.setString(3, client.getNomRue());
            updateClient.setString(4, client.getCdPostal());
            updateClient.setString(5, client.getVille());
            updateClient.setString(6, client.getTelephone());
            updateClient.setString(7, client.getMail());
            updateClient.setString(8, client.getCommentaire());
            updateClient.setDouble(9, client.getChiffreAffaire());
            updateClient.setInt(10, client.getNbrEmploye());
            updateClient.setInt(11, client.getId());

            updateClient.executeUpdate();

            con.commit();
        } catch (SQLException ex){

            if (con !=null) {
                MyLogger.LOGGER.log(Level.SEVERE, "Transaction update client rolled back, cause: "+
                        ex.getMessage(), ex);
                con.rollback();
            }

            if (ex.getErrorCode()==1062)
            {
                throw new DaoException ("Raison social existe déja", 2);
            }
            else if (ex.getErrorCode()==1406)
            {
                int startIndex = ex.getLocalizedMessage().lastIndexOf("column '");
                int lastIndex = ex.getLocalizedMessage().indexOf("' at row 1");
                String colonnName = ex.getLocalizedMessage().substring(startIndex +8,lastIndex);

                throw new DaoException("Trop de caractères dans le "+colonnName,2);
            }
            else {MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage()+" erreur: "+ex.getErrorCode(), ex);
                throw new SQLException(ex.getMessage());}

        }finally {
            if (updateClient != null){
                updateClient.close();
            }
            if (con != null){
                con.setAutoCommit(true);
            }
        }
    }

    /**
     * Supprime un client de la base de données en fonction de son identifiant.
     *
     * @param idClient L'identifiant du client à supprimer.
     * @throws Exception si une erreur survient lors de la suppression du client.
     * @throws DaoException si une erreur spécifique à la base de données survient lors de la suppression du client.
     */

    public static void deletClient (int idClient) throws Exception {
        PreparedStatement deletClient = null;
        String sql = "DELETE FROM client WHERE IDCLIENT = ?";
        Connection con = Connexion.startConnection();
        if (con==null){throw new DaoException("Problem d'accés a la base de données", 5);}

        try{
            con.setAutoCommit(false);
            deletClient =  con.prepareStatement(sql);

            deletClient.setInt(1, idClient);
            deletClient.executeUpdate();

            con.commit();
        }catch (SQLException ex){
            if (con != null){
                MyLogger.LOGGER.log(Level.SEVERE, "Transaction delete client rolled back, cause: "+
                        ex.getMessage(), ex);
                con.rollback();
            }
            throw ex;
        }finally {
            if (deletClient !=null){
                deletClient.close();
            }
            if (con != null){
                con.setAutoCommit(true);
            }
        }
    }
}