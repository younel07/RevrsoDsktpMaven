package entites;

import outils.EnumProspectInteresse;

import java.time.LocalDate;
/**
 * Représente un prospect, une entité commerciale en cours de prospection.
 * Étend la classe Societe.
 *
 * @author Younes
 * @since 2024-03-24
 */
public class Prospect extends Societe{
    /**
     * Date de prospection du prospect.
     */
    private LocalDate dateProspection;
    /**
     * Intérêt du prospect.
     */
    private EnumProspectInteresse prospectInteresse;
    /**
     * Constructeur de la classe Prospect.
     *
     * @param idSociete              L'identifiant du prospect.
     * @param raisonSociale          La raison sociale du prospect.
     * @param numRue                 Le numéro de rue du prospect.
     * @param nomRue                 Le nom de la rue du prospect.
     * @param cdPostal               Le code postal du prospect.
     * @param ville                  La ville du prospect.
     * @param telephone              Le numéro de téléphone du prospect.
     * @param mail                   L'adresse e-mail du prospect.
     * @param commentaire            Le commentaire associé au prospect.
     * @param p_dateProspection      La date de prospection du prospect.
     * @param p_prospectInteresse    L'intérêt du prospect.
     * @throws EntitiesException si les valeurs passées en paramètres sont invalides.
     */

    public Prospect(int idSociete, String raisonSociale,
                    String numRue, String nomRue, String cdPostal, String ville,
                    String telephone, String mail, String commentaire,
                    LocalDate p_dateProspection, EnumProspectInteresse p_prospectInteresse)
            throws EntitiesException
    {
        super(idSociete, raisonSociale, numRue, nomRue, cdPostal, ville, telephone, mail, commentaire);
        setDateProspection(p_dateProspection);
        setProspectInteresse(p_prospectInteresse);
    }
    //utilisation dans test
    public Prospect(){}
    /**
     * Définit la date de prospection du prospect.
     *
     * @param p_dateProspection La date de prospection à définir.
     * @throws EntitiesException si la date de prospection est nulle.
     */
    public void setDateProspection(LocalDate p_dateProspection) throws EntitiesException {
        if (p_dateProspection !=null)
        {
        dateProspection = p_dateProspection;
        }else throw new EntitiesException("Champ date obligatoir ",1);
    }
    /**
     * Renvoie la date de prospection du prospect.
     *
     * @return La date de prospection du prospect.
     */
    public LocalDate getDateProspection(){
        return dateProspection;
    }
    /**
     * Définit l'intérêt du prospect.
     *
     * @param p_prospectInteresse L'intérêt du prospect à définir (enum oui ou non).
     * @throws EntitiesException si l'intérêt du prospect est nul.
     */
    public void setProspectInteresse(EnumProspectInteresse p_prospectInteresse) throws EntitiesException {
        if (p_prospectInteresse != null){
        prospectInteresse = p_prospectInteresse;
        }else throw new EntitiesException("Champ date obligatoir ",1);
    }
    /**
     * Renvoie l'intérêt du prospect.
     *
     * @return L'intérêt du prospect.
     */
    public EnumProspectInteresse getProspectInteresse() {
        return prospectInteresse;
    }
    /**
     * Renvoie une représentation textuelle de l'objet Prospect.
     *
     * @return La représentation textuelle de l'objet Prospect.
     */

    public String toString() {
        return "Prospect { " + stToString()+
                ", Date de la prospection: " + getDateProspection() +
                ", Prospect Interessé: " + getProspectInteresse() + '\'' +
                "}";
    }
}
