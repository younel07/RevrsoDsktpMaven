package Controlleur;

import Dao.DaoClient;
import Dao.DaoProspect;
import Vues.AffichageListe;
import entites.Client;
import entites.Prospect;

import java.util.ArrayList;

/**
 * Cette classe contrôle l'affichage de la liste des clients ou des prospects dans l'application.
 *
 * La classe {@code ContAffichList} fournit des méthodes pour initialiser l'affichage de la liste,
 * récupérer la liste de tous les clients ou tous les prospects enregistrés.
 *
 * @author Younes
 * @version 1.0
 */
public class ContAffichList {

    /**
     * Initialise l'affichage de la liste des clients ou des prospects en fonction des paramètres fournis.
     *
     * @param isClientSelected    Indique si un client est sélectionné.
     * @param isProspectSelected  Indique si un prospect est sélectionné.
     * @throws Exception          Une exception en cas d'erreur lors de l'affichage.
     */
    public static void initAfficherList (boolean isClientSelected, boolean isProspectSelected) throws Exception{
        AffichageListe affichageListe = new AffichageListe(isClientSelected, isProspectSelected);
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

}
