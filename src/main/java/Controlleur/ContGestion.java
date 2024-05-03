package Controlleur;

import Dao.DaoClient;
import Dao.DaoProspect;
import Vues.Gestion;
import entites.Client;
import entites.Prospect;
import outils.EnumProspectInteresse;

import java.time.LocalDate;
/**
 * Cette classe gère les opérations de gestion des clients et prospects, telles que l'insertion, la modification
 * et la suppression, ainsi que l'initialisation des pages et la récupération des données.
 *
 * <p>
 *     Cette classe fait le lien entre les différentes vues (UI) et les couches d'accès aux données (DAO).
 * </p>
 *
 * @author Younes
 * @version 1.0
 * @since 2024-03-24
 */
public class ContGestion {

    /**
     * Initialise la page d'accueil.
     */
    public static void launchAccueil (){
        ContAccueil.initAccueil();
    }

    /**
     * Initialise la page de gestion avec les paramètres indiquant si un client ou un prospect est sélectionné.
     *
     * @param isClientSelected   Indique si un client est sélectionné.
     * @param isProspectSelected Indique si un prospect est sélectionné.
     */
    public static void initGestion (boolean isClientSelected, boolean isProspectSelected){
        Gestion gestion = new Gestion(isClientSelected, isProspectSelected);
    }

    /**
     * Insère un nouveau client dans la base de données.
     *
     * @param raisonSocial    Raison sociale du client.
     * @param numRue          Numéro de rue de l'adresse du client.
     * @param nomRue          Nom de la rue de l'adresse du client.
     * @param cdPostal        Code postal de l'adresse du client.
     * @param ville           Ville de l'adresse du client.
     * @param telephone       Numéro de téléphone du client.
     * @param mail            Adresse email du client.
     * @param chiffreAffaire  Chiffre d'affaires du client.
     * @param nbrEmploye      Nombre d'employés du client.
     * @param commentaire     Commentaire sur le client.
     * @throws Exception si une erreur survient lors de l'insertion du client.
     */
    public static void inseretClient (String raisonSocial, String numRue, String nomRue, String cdPostal, String ville,
                                      String telephone, String mail,double chiffreAffaire, int nbrEmploye,
                                      String commentaire)
            throws Exception {
        Client client = new Client(1,raisonSocial,numRue,nomRue,cdPostal, ville,telephone,mail,
                commentaire,chiffreAffaire,nbrEmploye);
        DaoClient.creatClient(client);
    }

    /**
     * Insère un nouveau prospet dans la base de données.
     *
     * @param raisonSocial       Raison sociale du prospect.
     * @param numRue             Numéro de rue de l'adresse du prospect.
     * @param nomRue             Nom de la rue de l'adresse du prospect.
     * @param cdPostal           Code postal de l'adresse du prospect.
     * @param ville              Ville de l'adresse du prospect.
     * @param telephone          Numéro de téléphone du prospect.
     * @param mail               Adresse email du prospect.
     * @param dateProspection    Date de prospection du prospect.
     * @param prospectInteresse  Niveau d'intérêt du prospect.
     * @param commentaire        Commentaire sur le prospect.
     * @throws Exception si une erreur survient lors de l'insertion du prospect.
     */
    public static void insertProspect (String raisonSocial, String numRue, String nomRue, String cdPostal, String ville,
                                       String telephone, String mail,LocalDate dateProspection ,
                                       EnumProspectInteresse prospectInteresse,
                                       String commentaire)
            throws Exception {
        Prospect prospect = new Prospect(1,raisonSocial,numRue,nomRue,cdPostal, ville,telephone,mail,
                commentaire,dateProspection,prospectInteresse);
        DaoProspect.creatProspect(prospect);
    }

    /**
     * Modifie les informations d'un client dans la base de données.
     *
     * @param raisonSocial    Nouvelle raison sociale du client.
     * @param numRue          Nouveau numéro de rue de l'adresse du client.
     * @param nomRue          Nouveau nom de la rue de l'adresse du client.
     * @param cdPostal        Nouveau code postal de l'adresse du client.
     * @param ville           Nouvelle ville de l'adresse du client.
     * @param telephone       Nouveau numéro de téléphone du client.
     * @param mail            Nouvelle adresse email du client.
     * @param chiffreAffaire  Nouveau chiffre d'affaires du client.
     * @param nbrEmploye      Nouveau nombre d'employés du client.
     * @param commentaire     Nouveau commentaire sur le client.
     * @param idClient        Identifiant du client à modifier.
     * @throws Exception si une erreur survient lors de la modification du client.
     */
    public static void modifierClient (String raisonSocial, String numRue, String nomRue, String cdPostal, String ville,
                                       String telephone, String mail,double chiffreAffaire, int nbrEmploye,
                                       String commentaire, int idClient)
            throws Exception {
        Client client = new Client(idClient,raisonSocial,numRue,nomRue,cdPostal, ville,telephone,mail,
                commentaire,chiffreAffaire,nbrEmploye);
        DaoClient.updateClient(client);
    }
    /**
     * Modifie les informations d'un prospet dans la base de données.
     *
     * @param raisonSocial    Nouvelle raison sociale du prospet.
     * @param numRue          Nouveau numéro de rue de l'adresse du prospet.
     * @param nomRue          Nouveau nom de la rue de l'adresse du prospet.
     * @param cdPostal        Nouveau code postal de l'adresse du prospet.
     * @param ville           Nouvelle ville de l'adresse du prospet.
     * @param telephone       Nouveau numéro de téléphone du prospet.
     * @param mail            Nouvelle adresse email du prospet.
     * @param dateProspection  Nouveau chiffre d'affaires du prospet.
     * @param prospectInteresse      Nouveau nombre d'employés du prospet.
     * @param commentaire     Nouveau commentaire sur le prospet.
     * @param idProspect        Identifiant du prospet à modifier.
     * @throws Exception si une erreur survient lors de la modification du prospet.
     */
    public static void modifierProspect (String raisonSocial,String numRue,String nomRue,String cdPostal, String ville,
                                       String telephone, String mail,LocalDate dateProspection ,
                                       EnumProspectInteresse prospectInteresse,
                                       String commentaire, int idProspect)
            throws Exception {
        Prospect prospect = new Prospect(idProspect,raisonSocial,numRue,nomRue,cdPostal, ville,telephone,mail,
                commentaire,dateProspection,prospectInteresse);
        DaoProspect.updateProspect(prospect);
    }
    /**
     * Supprime un client de la base de données.
     *
     * @param idClient Identifiant du client à supprimer.
     * @throws Exception si une erreur survient lors de la suppression du client.
     */
    public static void  supprimerClient (int idClient) throws Exception {
        DaoClient.deletClient(idClient);
    }
    /**
     * Supprime un prospet de la base de données.
     *
     * @param idProspect Identifiant du prospet à supprimer.
     * @throws Exception si une erreur survient lors de la suppression du prospet.
     */
    public static void  supprimerProspect (int idProspect) throws Exception {
        DaoProspect.deletProspect(idProspect);
    }
    /**
     * Renvoie le code correspondant à l'opération en cours (création, modification, suppression).
     *
     * @return Le code de l'opération en cours.
     * @see ContAccueil#operation
     */
    public static int lblOperationManager (){
        return ContAccueil.operation;
    }
    /**
     * Récupère le client sélectionné.
     *
     * @return Le client sélectionné.
     * @see ContAccueil#clientSelectionne
     */
    public static Client recupClient (){
        return ContAccueil.clientSelectionne;
    }
    /**
     * Récupère le prospect sélectionné.
     *
     * @return Le prospect sélectionné.
     * @see ContAccueil#prospectSelectionne
     */

    public static Prospect recupProspect (){
        return ContAccueil.prospectSelectionne;
    }

}
