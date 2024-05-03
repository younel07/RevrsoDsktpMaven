package Controlleur;

import Dao.DaoClient;
import Dao.DaoProspect;
import Vues.Accueil;
import entites.Client;
import entites.Prospect;

import java.util.ArrayList;

/**
 * Cette classe contrôle les actions effectuées depuis la fenêtre d'accueil de l'application.
 * Elle gère le lancement des différentes fonctionnalités et l'interaction avec les données.
 *
 * @author Younes
 * @version 1.0
 */
public class ContAccueil {

    /**
     * Opération en cours : 1 pour suppression, 2 pour modification, 3 pour création.
     */
    public static int operation;

    /**
     * Client sélectionné pour l'opération en cours.
     */
    public static Client clientSelectionne;

    /**
     * Prospect sélectionné pour l'opération en cours.
     */
    public static Prospect prospectSelectionne;

    /**
     * Initialise la fenêtre d'accueil de l'application.
     */
    public static void initAccueil (){
        Accueil accueil = new Accueil();
    }

    /**
     * Lance la gestion des clients ou des prospects en fonction des paramètres fournis.
     *
     * @param isClientSelected    Indique si un client est sélectionné.
     * @param isProspectSelected  Indique si un prospect est sélectionné.
     */
    public static void launchGestion (boolean isClientSelected, boolean isProspectSelected){
        ContGestion.initGestion(isClientSelected, isProspectSelected);
    }

    /**
     * Lance l'affichage de la liste des clients ou des prospects en fonction des paramètres fournis.
     *
     * @param isClientSelected    Indique si un client est sélectionné.
     * @param isProspectSelected  Indique si un prospect est sélectionné.
     * @throws Exception          Une exception en cas d'erreur lors de l'affichage.
     */
    public static void launchAffichageList (boolean isClientSelected, boolean isProspectSelected) throws Exception {
        ContAffichList.initAfficherList(isClientSelected, isProspectSelected);
    }

    /**
     * Recherche un prospect par son nom de raison sociale.
     *
     * @param filtreRaisonSocial  Le nom de raison sociale à rechercher.
     * @throws Exception          Une exception en cas d'erreur lors de la recherche.
     */
    public static void findByNameProspect (String filtreRaisonSocial) throws Exception{
        prospectSelectionne = DaoProspect.findByNameProspect(filtreRaisonSocial);
    }

    /**
     * Recherche un client par son nom de raison sociale.
     *
     * @param filtreRaisonSocial  Le nom de raison sociale à rechercher.
     * @throws Exception          Une exception en cas d'erreur lors de la recherche.
     */
    public static void findByNameClient (String filtreRaisonSocial) throws Exception{
        clientSelectionne = DaoClient.findByNameClient(filtreRaisonSocial);
    }

    /**
     * Récupère la liste de tous les clients enregistrés.
     *
     * @return                      La liste de tous les clients.
     * @throws Exception            Une exception en cas d'erreur lors de la récupération.
     */
    public static ArrayList<Client> mettreListClient () throws Exception {
        return DaoClient.findAllClient();
    }

    /**
     * Récupère la liste de tous les prospects enregistrés.
     *
     * @return                      La liste de tous les prospects.
     * @throws Exception            Une exception en cas d'erreur lors de la récupération.
     */
    public static ArrayList<Prospect> mettreListProspect () throws Exception{
        return DaoProspect.findAllProspects();
    }

    /**
     * Gère l'opération à effectuer en fonction du label d'opération fourni.
     *
     * @param operationLbl  Le label d'opération : 1 pour suppression, 2 pour modification, 3 pour création.
     */
    public static void lblOperationManager (int operationLbl ){
        switch (operationLbl){
            //suppression
            case 1:operation = 1; break;
            //modification
            case 2:operation = 2; break;
            //creation
            default: operation = 3;
        }
    }
}
