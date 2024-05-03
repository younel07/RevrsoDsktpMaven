package Vues;

import Controlleur.ContAccueil;
import Dao.DaoException;
import entites.Client;
import entites.EntitiesException;
import entites.Prospect;
import logs.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.logging.Level;
/**
 * Cette classe représente la fenêtre d'accueil de l'application.
 * Elle permet à l'utilisateur d'accéder à différentes fonctionnalités telles que l'affichage, la création,
 * la modification et la suppression d'entités.
 *
 * @author Younes
 * @version 1.0
 * @since 2024-03-24
 */
public class Accueil extends JDialog {
    // Déclaration des composants de l'interface utilisateur
    private JPanel contentPane;
    private JPanel pnlBtns;
    private JPanel pnlTitre;
    private JLabel lblAccueil;
    private JButton btnAfficheList;
    private JButton btnCreat;
    private JButton btnAlter;
    private JButton btnDelete;
    private JButton btnQuit;
    private JPanel pnlClients;
    private JPanel pnlCentre;
    private JPanel pnlComboBox;
    private JPanel pnlProspects;
    private JCheckBox chkBxClients;
    private JLabel lblClients;
    private JLabel lblProspect;
    private JCheckBox chkBxProspect;
    private JComboBox comboBox1;
    private JButton buttonOK;
    private static boolean isProspectSelected;
    private static boolean isClientSelected;
    private String selectionEntite;

    /**
     * Constructeur de la classe Accueil.
     * Initialise la fenêtre d'accueil avec ses composants et définit les paramètres de la fenêtre.
     */
    public Accueil() {
        setTitle("Accueil");
        setContentPane(contentPane);
        setSize(800, 800);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    //check box Clients
    {
        chkBxClients.addItemListener(new ItemListener() {
            /**
             * Méthode appelée lorsqu'il y a un changement d'état dans la case à cocher Clients.
             * Si la case est cochée, elle remplit la liste déroulante avec les clients et désélectionne la case Prospect le cas échéant.
             * Si la case est décochée et aucune case Prospect n'est sélectionnée, elle vide la liste déroulante.
             * En cas d'erreur, affiche un message d'erreur et termine l'application.
             *
             * @param e L'événement ItemEvent déclenché par le changement d'état de la case à cocher.
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        remplirComboBoxClients();
                        chkBxProspect.setSelected(false);
                    } catch (Exception ex) {
                        MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
                        JOptionPane.showMessageDialog(null,
                                "Erreur est survenu veuiller réssayer ultérieurement");
                        System.exit(1);
                    }
                } else if (!chkBxProspect.isSelected()) {
                    comboBox1.removeAllItems();
                }
            }
        });
    }

    //check box Prospcts
    {
        chkBxProspect.addItemListener(new ItemListener() {
            /**
             * Méthode appelée lorsqu'il y a un changement d'état dans la case à cocher Prospects.
             * Si la case est cochée, elle remplit la liste déroulante avec les prospects et désélectionne la case Clients le cas échéant.
             * Si la case est décochée et aucune case Clients n'est sélectionnée, elle vide la liste déroulante.
             * En cas d'erreur, affiche un message d'erreur et termine l'application.
             *
             * @param e L'événement ItemEvent déclenché par le changement d'état de la case à cocher.
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        remplirComboBoxProspects();
                        chkBxClients.setSelected(false);
                    } catch (Exception ex) {
                        MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
                        JOptionPane.showMessageDialog(null,
                                "Erreur est survenu veuiller réssayer ultérieurement");
                        System.exit(1);
                    }
                } else if (!chkBxClients.isSelected()) {
                    comboBox1.removeAllItems();
                }
            }
        });
    }
    //selectionner par la sourie une entité a modifier ou a supprimer
    {
        comboBox1.addActionListener(new ActionListener() {
            /**
             * Méthode appelée lorsqu'un élément est sélectionné dans la liste déroulante.
             * Cette méthode récupère la sélection de l'utilisateur et effectue une action en fonction de l'entité sélectionnée (client ou prospect).
             * Elle affiche également un message indiquant quelle entité a été sélectionnée.
             *
             * @param e L'événement ActionEvent déclenché par la sélection d'un élément dans la liste déroulante.
             */
            @Override
            public void actionPerformed (ActionEvent e){
                //je donne la valeur boolean au check box souhaité
                isClientSelected = chkBxClients.isSelected();
                isProspectSelected = chkBxProspect.isSelected();

                if (isProspectSelected) {
                    selectionEntite = String.valueOf(comboBox1.getSelectedItem());
                    try {
                        ContAccueil.findByNameProspect(selectionEntite);
                        JOptionPane.showMessageDialog(null, "Vous avez choisi le prospect:\n "
                                + selectionEntite);
                    } catch (Exception ex) {
                        MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
                        JOptionPane.showMessageDialog(null,
                                "Erreur est survenu veuiller réssayer ultérieurement");
                        System.exit(1);
                    }
                } else if (isClientSelected) {
                    selectionEntite = String.valueOf(comboBox1.getSelectedItem());
                    try {
                        ContAccueil.findByNameClient(selectionEntite);
                        JOptionPane.showMessageDialog(null, "Vous avez choisi le client:\n "
                                + selectionEntite);
                    } catch (Exception ex) {
                        MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
                        JOptionPane.showMessageDialog(null,
                                "Erreur est survenu veuiller réssayer ultérieurement");
                        System.exit(1);
                    }
                }
            }
        });
    }

    //Boutton Afficher List
    {
        btnAfficheList.addActionListener(new ActionListener() {
            /**
             * Méthode appelée lorsqu'un clic est effectué sur le bouton "Afficher Liste".
             * Cette méthode vérifie si un type d'entité (client ou prospect) est sélectionné.
             * Si aucun type n'est sélectionné, affiche un message d'erreur.
             * Sinon, ferme la fenêtre d'accueil et lance l'affichage de la liste correspondante.
             *
             * @param e L'événement ActionEvent déclenché par le clic sur le bouton "Afficher Liste".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!chkBxClients.isSelected() && !chkBxProspect.isSelected()) {
                        JOptionPane.showMessageDialog(null,
                                "Sélectionnez client ou prospect pour afficher leurs détails.",
                                "Obligatoire",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        Accueil.this.dispose();
                        isClientSelected = chkBxClients.isSelected();
                        isProspectSelected = chkBxProspect.isSelected();
                        ContAccueil.launchAffichageList(isClientSelected, isProspectSelected);
                    }
                } catch (EntitiesException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Afficher liste", JOptionPane.WARNING_MESSAGE);
                }catch (Exception ex) {
                    MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
                    JOptionPane.showMessageDialog(null,
                            "Erreur est survenu veuiller réssayer ultérieurement");
                    System.exit(1);
                }
            }
        });
    }

    //Boutton New
    {
        btnCreat.addActionListener(new ActionListener() {
            /**
             * Méthode appelée lorsqu'un clic est effectué sur le bouton "New".
             * Cette méthode vérifie si un type d'entité (client ou prospect) est sélectionné.
             * Si aucun type n'est sélectionné, affiche un message d'avertissement.
             * Sinon, ferme la fenêtre d'accueil, prépare l'opération de création et lance la gestion correspondante.
             *
             * @param e L'événement ActionEvent déclenché par le clic sur le bouton "New".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!chkBxClients.isSelected() && !chkBxProspect.isSelected()) {
                        JOptionPane.showMessageDialog(null,
                                "Sélectionnez client ou prospect pour la création.",
                                "Créer client", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Accueil.this.dispose();
                        isClientSelected = chkBxClients.isSelected();
                        isProspectSelected = chkBxProspect.isSelected();
                        ContAccueil.lblOperationManager(3);
                        ContAccueil.launchGestion(isClientSelected, isProspectSelected);
                    }
                } catch (Exception ex) {
                    MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
                    JOptionPane.showMessageDialog(null,
                            "Erreur est survenu veuiller réssayer ultérieurement",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }
        });
    }

    //Boutton Modifier
    {
        btnAlter.addActionListener(new ActionListener() {
            /**
             * Méthode appelée lorsqu'un clic est effectué sur le bouton "Modifier".
             * Cette méthode vérifie si un type d'entité (client ou prospect) est sélectionné et si une entité est sélectionnée dans la liste déroulante.
             * Si aucun type n'est sélectionné, affiche un message d'avertissement.
             * Si aucune entité n'est sélectionnée, affiche un message d'avertissement.
             * Sinon, ferme la fenêtre d'accueil, prépare l'opération de modification et lance la gestion correspondante.
             *
             * @param e L'événement ActionEvent déclenché par le clic sur le bouton "Modifier".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!chkBxClients.isSelected() && !chkBxProspect.isSelected()) {
                        JOptionPane.showMessageDialog(null,
                                "Sélectionnez client ou prospect pour modifier ces cordonnées.",
                                "Modifier entreprise", JOptionPane.WARNING_MESSAGE);
                    } else if (!(selectionEntite == null)) {
                        Accueil.this.dispose();
                        isClientSelected = chkBxClients.isSelected();
                        isProspectSelected = chkBxProspect.isSelected();
                        ContAccueil.lblOperationManager(2);
                        ContAccueil.launchGestion(isClientSelected, isProspectSelected);
                    } else JOptionPane.showMessageDialog(null,
                            "Vous devez choisir une entreprise pour modifier ces cordonnées",
                            "Modifier entreprise", JOptionPane.WARNING_MESSAGE);
                }catch (Exception ex) {
                    MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
                    JOptionPane.showMessageDialog(null,
                            "Erreur est survenu veuiller réssayer ultérieurement",
                            "Erreur", JOptionPane.WARNING_MESSAGE);
                    System.exit(1);
                }
            }
        });
    }

    //Boutton Supprimer
    {
        btnDelete.addActionListener(new ActionListener() {
            /**
             * Méthode appelée lorsqu'un clic est effectué sur le bouton "Supprimer".
             * Cette méthode vérifie si un type d'entité (client ou prospect) est sélectionné et si une entité est sélectionnée dans la liste déroulante.
             * Si aucun type n'est sélectionné, affiche un message d'avertissement.
             * Si aucune entité n'est sélectionnée, affiche un message d'avertissement.
             * Sinon, ferme la fenêtre d'accueil, prépare l'opération de suppression et lance la gestion correspondante.
             *
             * @param e L'événement ActionEvent déclenché par le clic sur le bouton "Supprimer".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!chkBxClients.isSelected() && !chkBxProspect.isSelected()) {
                        JOptionPane.showMessageDialog(null,
                                "Sélectionnez client ou prospect pour supprimer ces cordonnées.",
                                "Supprimer entreprise", JOptionPane.WARNING_MESSAGE);
                    } else if (!(selectionEntite == null)) {
                        Accueil.this.dispose();
                        isClientSelected = chkBxClients.isSelected();
                        isProspectSelected = chkBxProspect.isSelected();
                        ContAccueil.lblOperationManager(1);
                        ContAccueil.launchGestion(isClientSelected, isProspectSelected);
                    } else JOptionPane.showMessageDialog(null,
                            "Vous devez choisir une entreprise pour supprimer ces cordonnées.",
                            "Supprimer entreprise", JOptionPane.WARNING_MESSAGE);
                }catch (Exception ex) {
                    MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
                    JOptionPane.showMessageDialog(null,
                            "Erreur est survenu veuiller réssayer ultérieurement",
                            "Erreur", JOptionPane.WARNING_MESSAGE);
                    System.exit(1);
                }
            }
        });
    }

    //Boutton Quitter
    {
        btnQuit.addActionListener(new ActionListener() {
            /**
             * Méthode appelée lorsqu'un clic est effectué sur le bouton "Quitter".
             * Cette méthode ferme la fenêtre d'accueil.
             *
             * @param e L'événement ActionEvent déclenché par le clic sur le bouton "Quitter".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    //combobox

    /**
     * Cette méthode remplit la combobox avec les clients récupérés depuis la base de données.
     * Elle utilise la méthode mettreListClient() de la classe ContAccueil pour récupérer les données des clients.
     * Les noms des clients sont ajoutés à la combobox.
     * En cas d'erreur lors de la récupération des données ou lors du remplissage de la combobox,
     * des messages d'erreur sont affichés à l'utilisateur.
     */
    private void remplirComboBoxClients() {
        try {
            ArrayList<Client> clients = ContAccueil.mettreListClient();//appel methode qui récupre les données

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (Client client : clients) {
                model.addElement(client.getRaisonSociale());
            }
            comboBox1.setModel(model);
        } catch (DaoException e) {
            if (e.getSeverityLevel() >2) {
                MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ e.getMessage(), e);
                JOptionPane.showMessageDialog(null, e.getMessage(), "Connexion",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }else JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (EntitiesException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
            JOptionPane.showMessageDialog(null,
                    "Erreur est survenu veuiller réssayer ultérieurement",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Cette méthode remplit la combobox avec les prospects récupérés depuis la base de données.
     * Elle utilise la méthode mettreListProspect() de la classe ContAccueil pour récupérer les données des prospects.
     * Les noms des prospects sont ajoutés à la combobox.
     * En cas d'erreur lors de la récupération des données ou lors du remplissage de la combobox,
     * des messages d'erreur sont affichés à l'utilisateur.
     */
    private void remplirComboBoxProspects() {
        try {
            ArrayList<Prospect> prospects = ContAccueil.mettreListProspect();//appel methode qui récupre les données

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (Prospect prospect : prospects) {
                model.addElement(prospect.getRaisonSociale());
            }
            comboBox1.setModel(model);
        } catch (DaoException e) {
            if (e.getSeverityLevel() >2) {
                MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ e.getMessage(), e);
                JOptionPane.showMessageDialog(null, e.getMessage(), "Connexion",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }else JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (EntitiesException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur: "+ ex.getMessage(), ex);
            JOptionPane.showMessageDialog(null,
                    "Erreur est survenu veuiller réssayer ultérieurement",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
