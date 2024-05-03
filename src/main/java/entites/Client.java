package entites;
/**
 * Représente un client dans le système.
 * Un client est une entité commerciale avec une raison sociale, une adresse, un numéro de téléphone,
 * une adresse e-mail, un commentaire, un chiffre d'affaires et un nombre d'employés.
 *
 * @author Younes
 * @since 2024-03-24
 */

public class Client extends Societe {
    private double chiffreAffaire;
    private int nbrEmploye;
    /**
     * Constructeur pour créer un nouveau client.
     *
     * @param idSociete        L'identifiant unique du client.
     * @param raisonSociale    La raison sociale du client.
     * @param numRue           Le numéro de rue de l'adresse du client.
     * @param nomRue           Le nom de la rue de l'adresse du client.
     * @param cdPostal         Le code postal de l'adresse du client.
     * @param ville            La ville de l'adresse du client.
     * @param telephone        Le numéro de téléphone du client.
     * @param mail             L'adresse e-mail du client.
     * @param commentaire      Un commentaire associé au client.
     * @param p_chiffreAffaire Le chiffre d'affaires du client.
     * @param p_nbrEmployes    Le nombre d'employés du client.
     * @throws EntitiesException si l'une des valeurs fournies est invalide.
     */

    public Client(int idSociete, String raisonSociale,
                  String numRue, String nomRue, String cdPostal, String ville,
                  String telephone, String mail, String commentaire,
                  double p_chiffreAffaire, int p_nbrEmployes) throws EntitiesException {
        super(idSociete, raisonSociale, numRue, nomRue, cdPostal, ville, telephone, mail, commentaire);
        setChiffreAffaire(p_chiffreAffaire);
        setNbrEmploye(p_nbrEmployes);
    }
    /**
     * Définit le chiffre d'affaires du client.
     *
     * @param p_chiffreAffaire Le chiffre d'affaires à définir.
     * @throws EntitiesException si le chiffre d'affaires est inférieur ou égal à 200.
     */
    public void setChiffreAffaire (double p_chiffreAffaire) throws EntitiesException {
        if (p_chiffreAffaire > 200 ) {
            chiffreAffaire = p_chiffreAffaire;
        } else {
            throw new EntitiesException("Le chiffre d'affaires doit être supérieur à 200",1);
        }
    }
    /**
     * Renvoie le chiffre d'affaires du client.
     *
     * @return Le chiffre d'affaires du client.
     */
    public double getChiffreAffaire() {
        return chiffreAffaire;
    }
    /**
     * Définit le nombre d'employés du client.
     *
     * @param p_nbrEmployes Le nombre d'employés à définir.
     * @throws EntitiesException si le nombre d'employés est inférieur ou égal à zéro.
     */
    public void setNbrEmploye (int p_nbrEmployes) throws EntitiesException{
        if (p_nbrEmployes > 0) {
            nbrEmploye = p_nbrEmployes;
        } else {
            throw new EntitiesException("Le nombre d'employés doit être strictement supérieur à zéro",1);
        }
    }
    /**
     * Renvoie le nombre d'employés du client.
     *
     * @return Le nombre d'employés du client.
     */
    public int getNbrEmploye() {
        return nbrEmploye;
    }
    /**
     * Renvoie une représentation textuelle du client.
     *
     * @return Une représentation textuelle du client.
     */

    public String toString(){
        return "Client { "+
                " id Societe: " + getId() +
                ", Raison Sociale: '" + getRaisonSociale() + '\'' +
                " ,Chiffre D'affaire: '"+ getChiffreAffaire() + '\''+
                " ,Nombre d'employés: '"+ getNbrEmploye()+'\''+
                " }";
    }
}
