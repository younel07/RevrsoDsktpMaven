package Dao;

import entites.EntitiesException;
import entites.Prospect;
import logs.MyLogger;
import outils.EnumProspectInteresse;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
/**
 * Cette classe fournit les méthodes d'accès aux données pour les objets de type Prospect.
 * Elle permet de récupérer, insérer, mettre à jour et supprimer des enregistrements de la table PROSPECT.
 * La classe gère également les exceptions liées à l'accès aux données.
 *
 * @author Younes
 * @version 1.0
 * @since 2024-03-24
 */
public class DaoProspect {
    /**
     * Récupère la liste complète des prospets depuis la base de données.
     *
     * @return La liste des prospects.
     * @throws Exception si une erreur survient lors de la récupération des données ou de l'instanciation des objets Prospects.
     * @see Prospect
     */
    public static ArrayList<Prospect> findAllProspects() throws Exception {
        Statement statement = null;
        String sql = "SELECT * FROM PROSPECT ORDER BY RAISONSOCIAL ASC";
        ArrayList<Prospect> prospects = new ArrayList<>();

        Connection con = Connexion.startConnection();

        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int idProspect = rs.getInt("IDPROSPECT");
                String raisonSocial = rs.getString("RAISONSOCIAL");
                String numRue = rs.getString("NUMRUE");
                String nomRue = rs.getString("NOMRUE");
                String cdPostal = rs.getString("CDPOSTAL");
                String ville = rs.getString("ville");
                String telephone = rs.getString("TELEPHONE");
                String mail = rs.getString("MAIL");
                String commentaire = rs.getString("COMMENTAIRES");

                Date date = rs.getDate("DATEPROSPECTION");
                LocalDate dateProspection = date.toLocalDate();

                String prospectOuiNon = rs.getString("PROSPECTINTERESSE");
                EnumProspectInteresse prospectInteresse = EnumProspectInteresse.valueOf(prospectOuiNon);

                Prospect prospect = new Prospect(idProspect, raisonSocial, numRue, nomRue, cdPostal, ville,
                        telephone, mail, commentaire, dateProspection, prospectInteresse);
                prospects.add(prospect);
            }
        } catch (SQLException e) {
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur sql pour recuperer la bdd prospects: "+e.getMessage(), e);
            throw new DaoException ("Erreur pour recuperer la liste des prospects", 3);
        } catch (EntitiesException e){
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur sql pour recuperer la bdd prospects: "+e.getMessage(), e);
            throw new EntitiesException ("Erreur pour instencier le prospet",3);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return prospects;
    }

    /**
     * Récupère un prospect depuis la base de données en fonction de sa raison sociale.
     *
     * @param filtreRaisonSocial La raison sociale du prospect à rechercher.
     * @return Le prospect correspondant à la raison sociale spécifiée.
     * @throws Exception si une erreur survient lors de la recherche du prospect ou lors de l'instanciation de l'objet Proespect.
     * @throws DaoException si le prospect spécifié n'est pas trouvé dans la base de données.
     * @see Prospect
     */
    public static Prospect findByNameProspect(String filtreRaisonSocial) throws Exception {
        PreparedStatement findByNameProspect = null;
        String sql = "SELECT * FROM prospect WHERE RAISONSOCIAL = ?";

        Connection con = Connexion.startConnection();

        try {
            findByNameProspect = con.prepareStatement(sql);
            findByNameProspect.setString(1, filtreRaisonSocial);
            ResultSet rs = findByNameProspect.executeQuery();

            if (rs.next()) {
                int idProspect = rs.getInt("IDPROSPECT");
                String raisonSocial = rs.getString("RAISONSOCIAL");
                String numRue = rs.getString("NUMRUE");
                String nomRue = rs.getString("NOMRUE");
                String cdPostal = rs.getString("CDPOSTAL");
                String ville = rs.getString("ville");
                String telephone = rs.getString("TELEPHONE");
                String mail = rs.getString("MAIL");
                String commentaire = rs.getString("COMMENTAIRES");

                Date date = rs.getDate("DATEPROSPECTION");
                LocalDate dateProspection = date.toLocalDate();

                String prospectOuiNon = rs.getString("PROSPECTINTERESSE");
                EnumProspectInteresse prospectInteresse = EnumProspectInteresse.valueOf(prospectOuiNon);

                return  new Prospect(idProspect, raisonSocial, numRue, nomRue, cdPostal, ville,
                        telephone, mail, commentaire, dateProspection, prospectInteresse);

            } else throw new DaoException("Impossible de Trouver ce prospet", 3);

        } catch (Exception e){
            throw e;
        }
        finally {
            if (findByNameProspect != null){findByNameProspect.close();}
        }

    }

    /**
     * Crée un nouveau prospect dans la base de données.
     *
     * @param prospect Le prospect à créer.
     * @throws Exception si une erreur survient lors de la création du propsect.
     * @throws DaoException si une erreur spécifique à la base de données survient lors de la création du prospect,
     *                       comme une violation de contrainte d'unicité ou une longueur de champ dépassée.
     * @see Prospect
     */
    public static void creatProspect(Prospect prospect) throws Exception {
        PreparedStatement creatProspect = null;
        String sql =
            "INSERT INTO prospect (`RAISONSOCIAL`, `NUMRUE`, `NOMRUE`, `CDPOSTAL`, `VILLE`,`TELEPHONE`," +
                    " `MAIL`, `COMMENTAIRES`, `DATEPROSPECTION`, `PROSPECTINTERESSE`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";

        Connection con = Connexion.startConnection();

        try {
            con.setAutoCommit(false);
            creatProspect = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            creatProspect.setString(1, prospect.getRaisonSociale());
            creatProspect.setString(2, prospect.getNumRue());
            creatProspect.setString(3, prospect.getNomRue());
            creatProspect.setString(4, prospect.getCdPostal());
            creatProspect.setString(5, prospect.getVille());
            creatProspect.setString(6, prospect.getTelephone());
            creatProspect.setString(7, prospect.getMail());
            creatProspect.setString(8, prospect.getCommentaire());
            // Conversion LocalDate vers Date
            creatProspect.setDate(9, Date.valueOf(prospect.getDateProspection()));
            // Conversion enum to String
            creatProspect.setString(10, prospect.getProspectInteresse().toString());

            creatProspect.executeUpdate();

            con.commit();

            //Rollback transaction on error
        } catch (SQLException ex){
            if (con !=null) {
                MyLogger.LOGGER.log(Level.SEVERE, "Transaction create prospect rolled back, cause: "+
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
            if (creatProspect != null) {
                creatProspect.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
            }
        }
    }

    /**
     * Met à jour les informations d'un prospect dans la base de données.
     *
     * @param prospect Le prospect avec les informations mises à jour.
     * @throws Exception si une erreur survient lors de la mise à jour du prospect.
     * @throws DaoException si une erreur spécifique à la base de données survient lors de la mise à jour du prospect,
     *                       comme une violation de contrainte d'unicité ou une longueur de champ dépassée.
     * @see Prospect
     */
    public static void updateProspect(Prospect prospect) throws Exception {
        PreparedStatement updateProspect = null;
        String sql = " UPDATE prospect SET RAISONSOCIAL=?, NUMRUE=?, NOMRUE=?, CDPOSTAL=?, VILLE=? , " +
                "TELEPHONE=?, MAIL=?, COMMENTAIRES=?, DATEPROSPECTION=?, PROSPECTINTERESSE=? WHERE IDPROSPECT=? ";

        Connection con = Connexion.startConnection();

        try {
            con.setAutoCommit(false);
            updateProspect = con.prepareStatement(sql);

            updateProspect.setString(1, prospect.getRaisonSociale());
            updateProspect.setString(2, prospect.getNumRue());
            updateProspect.setString(3, prospect.getNomRue());
            updateProspect.setString(4, prospect.getCdPostal());
            updateProspect.setString(5, prospect.getVille());
            updateProspect.setString(6, prospect.getTelephone());
            updateProspect.setString(7, prospect.getMail());
            updateProspect.setString(8, prospect.getCommentaire());
            // Conversion LocalDate vers Date
            updateProspect.setDate(9, Date.valueOf(prospect.getDateProspection()));
            // Conversion enum to String
            updateProspect.setString(10, prospect.getProspectInteresse().toString());
            updateProspect.setInt(11, prospect.getId());

            updateProspect.executeUpdate();

            con.commit();
        } catch (SQLException ex){
            if (con !=null) {
                MyLogger.LOGGER.log(Level.SEVERE, "Transaction update prospect rolled back, cause: "+
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
            if (updateProspect != null) {
                updateProspect.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
            }
        }
    }

    /**
     * Supprime un prospect de la base de données en fonction de son identifiant.
     *
     * @param idProspect L'identifiant du prospect à supprimer.
     * @throws Exception si une erreur survient lors de la suppression du prospect.
     * @throws DaoException si une erreur spécifique à la base de données survient lors de la suppression du prospect.
     */
    public static void deletProspect (int idProspect) throws Exception {
        PreparedStatement deletProspect = null;
        String sql = "DELETE FROM prospect WHERE IDPROSPECT = ?";
        Connection con = Connexion.startConnection();
        if (con==null){throw new DaoException("Problem d'accés a la base de données", 5);}

        try{
            con.setAutoCommit(false);
            deletProspect =  con.prepareStatement(sql);

            deletProspect.setInt(1, idProspect);
            deletProspect.executeUpdate();

            con.commit();
        }catch (SQLException ex){
            if (con != null){
                MyLogger.LOGGER.log(Level.SEVERE, "Transaction delete prospect rolled back, cause: "+
                        ex.getMessage(), ex);
                con.rollback();
            }
            throw ex;
        }finally {
            if (deletProspect !=null){
                deletProspect.close();
            }
            if (con != null){
                con.setAutoCommit(true);
            }
        }
    }
}