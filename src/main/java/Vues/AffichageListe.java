package Vues;

import Controlleur.ContAccueil;
import Controlleur.ContAffichList;
import Dao.DaoException;
import entites.Client;
import entites.EntitiesException;
import entites.Prospect;
import logs.MyLogger;
import outils.DateFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Cette classe représente une fenêtre d'affichage des détails des clients ou des prospects.
 * Elle affiche les détails des entités sélectionnées (clients ou prospects) sous forme de tableau.
 *
 * @author Younes
 * @version 1.0
 * @since 2024-03-24
 */
public class AffichageListe extends JDialog {
    private JPanel pnlTritre;
    private JPanel pnlBtns;
    private JPanel pnlCenter;
    private JLabel lblAffichageList;
    private JButton btnRetour;
    private JButton btnQuitter;
    private JPanel pnlLableCltPros;
    private JTable bddList;
    private JLabel lblListeClient;
    private JLabel lblListeProspect;
    private JPanel contentPane;
    private JScrollPane scrlTable;

    /**
     * Constructeur de la classe AffichageListe.
     * Initialise la fenêtre et affiche les détails des clients ou des prospects selon les paramètres spécifiés.
     *
     * @param isClientSelected   Indique si les clients sont sélectionnés.
     * @param isProspectSelected Indique si les prospects sont sélectionnés.
     */
    public AffichageListe(boolean isClientSelected, boolean isProspectSelected){
        setTitle("Affichage des details");
        setContentPane(contentPane);
        setSize(1000,500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        lblListeClient.setVisible(isClientSelected);
        lblListeProspect.setVisible(isProspectSelected);
        if (isClientSelected) {
            remplirBddClient();
        }
        else if (isProspectSelected) {
            remplirBddProspect();
        }
    }
    /**
     * Cette méthode remplit la JTable avec les détails des clients récupérés depuis la base de données.
     * Elle utilise la méthode mettreListClient() de la classe ContAffichList pour récupérer les données des clients.
     * Les détails des clients sont récupérés et stockés dans un tableau bidimensionnel.
     * En cas d'erreur lors de la récupération des données ou lors du remplissage de la JTable,
     * des messages d'erreur sont affichés à l'utilisateur.
     */
    private void remplirBddClient (){
        try {
            // Appel de la méthode qui récupère les données des clients
            ArrayList<Client> clients = ContAffichList.mettreListClient();

            // Création d'un tableau bidimensionnel pour stocker les détails des clients
            Object[][] tableau = new Object[clients.size()][10];
            for (int i = 0; i< clients.size(); i++){
                Client client = clients.get(i);
                tableau[i][0] = client.getId();
                tableau[i][1] = client.getRaisonSociale();
                tableau[i][2] = client.getNumRue();
                tableau[i][3] = client.getNomRue();
                tableau[i][4] = client.getCdPostal();
                tableau[i][5] = client.getVille();
                tableau[i][6] = client.getTelephone();
                tableau[i][7] = client.getMail();
                tableau[i][8] = client.getChiffreAffaire();
                tableau[i][9] = client.getNbrEmploye();
            }
            // Définition des noms de colonnes
            String [] columsName ={"ID","Raison Social","N° Rue","Nom Rue","Code Postal","Ville","Téléphone","Email"
                                    ,"Chiffre d'affaire", "Nombre d'emplpyes"};
            // Création d'un modèle de tableau par défaut avec les données récupérées
            DefaultTableModel model = new DefaultTableModel(tableau, columsName);
            // Application du modèle à la JTable
            bddList.setModel(model);
            scrlTable.setViewportView(bddList);

        } catch (DaoException e){
            if (e.getSeverityLevel() >2) {
                // Gestion des exceptions liées à l'accès aux données
                MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ e.getMessage(), e);
                JOptionPane.showMessageDialog(null, e.getMessage(), "Connexion",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }else JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (EntitiesException e) {
            // Gestion des exceptions liées aux entités
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            // Gestion des exceptions générales
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
            JOptionPane.showMessageDialog(null,
                    "Erreur est survenu veuiller réssayer ultérieurement",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    /**
     * Cette méthode remplit la JTable avec les détails des prospects récupérés depuis la base de données.
     * Elle utilise la méthode mettreListProspect() de la classe ContAffichList pour récupérer les données des prospects.
     * Les détails des prospects sont récupérés et stockés dans un tableau bidimensionnel.
     * En cas d'erreur lors de la récupération des données ou lors du remplissage de la JTable,
     * des messages d'erreur sont affichés à l'utilisateur.
     */
    private void remplirBddProspect (){
        try {
            // Appel de la méthode qui récupère les données des prospects
            ArrayList<Prospect> prospects = ContAffichList.mettreListProspect();

            // Création d'un tableau bidimensionnel pour stocker les détails des prospects
            Object[][] tableau = new Object[prospects.size()][11];
            for (int i = 0; i< prospects.size(); i++){
                Prospect prospect = prospects.get(i);
                tableau[i][0] = prospect.getId();
                tableau[i][1] = prospect.getRaisonSociale();
                tableau[i][2] = prospect.getNumRue();
                tableau[i][3] = prospect.getNomRue();
                tableau[i][4] = prospect.getCdPostal();
                tableau[i][5] = prospect.getVille();
                tableau[i][6] = prospect.getTelephone();
                tableau[i][7] = prospect.getMail();
                tableau[i][8] = DateFormatter.afficherDate(prospect.getDateProspection());
                tableau[i][9] = prospect.getProspectInteresse();
            }
            // Définition des noms de colonnes
            String [] columsName ={"ID","Raison Social","N° Rue","Nom Rue","Code Postal","Ville","Téléphone","Email"
                    ,"Date prospection", "Prospect interessé"};
            // Création d'un modèle de tableau par défaut avec les données récupérées
            DefaultTableModel model = new DefaultTableModel(tableau, columsName);

            // Application du modèle à la JTable
            bddList.setModel(model);
            scrlTable.setViewportView(bddList);

        } catch (DaoException e){
            // Gestion des exceptions liées à l'accès aux données
            if (e.getSeverityLevel() >2) {
                MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ e.getMessage(), e);
                JOptionPane.showMessageDialog(null, e.getMessage(), "Connexion",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }else JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (EntitiesException e) {
            // Gestion des exceptions liées aux entités
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            // Gestion des exceptions générales
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
            JOptionPane.showMessageDialog(null,
                    "Erreur est survenu veuiller réssayer ultérieurement",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    //Boutton retour
    {
        btnRetour.addActionListener(new ActionListener() {
            /**
             * Méthode appelée lorsqu'un clic est effectué sur le bouton "Retour".
             * Cette méthode ferme la fenêtre d'affichage de la liste et réinitialise l'accueil.
             *
             * @param e L'événement ActionEvent déclenché par le clic sur le bouton "Retour".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AffichageListe.this.dispose();
                ContAccueil.initAccueil();
            }
        });
    }
    //Boutton Quitter
    {
        btnQuitter.addActionListener(new ActionListener() {
            /**
             * Méthode appelée lorsqu'un clic est effectué sur le bouton "Quitter".
             * Cette méthode ferme la fenêtre d'affichage de la liste.
             *
             * @param e L'événement ActionEvent déclenché par le clic sur le bouton "Quitter".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
