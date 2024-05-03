package entites;

import outils.EmailPattern;

/**
 * Cette classe abstraite représente une entité générique de type société.
 * Elle contient les attributs communs à toutes les sociétés, tels que l'identifiant, la raison sociale, l'adresse, etc.
 *
 * @since 2024-03-24
 * @author Younes
 */

public abstract class Societe {

    private int idSociete;
    private String raisonSociale;
    private String numRue;
    private String nomRue;
    private String cdPostal;
    private String ville;
    private String telephone;
    private String mail;
    private String commentaire;
    /**
     * Constructeur pour créer une instance de la classe Societe avec les informations spécifiées.
     *
     * @param idSociete     L'identifiant de la société.
     * @param raisonSociale La raison sociale de la société.
     * @param numRue        Le numéro de rue de l'adresse de la société.
     * @param nomRue        Le nom de la rue de l'adresse de la société.
     * @param cdPostal      Le code postal de l'adresse de la société.
     * @param ville         La ville de l'adresse de la société.
     * @param telephone     Le numéro de téléphone de la société.
     * @param mail          L'adresse email de la société.
     * @param commentaire   Un commentaire sur la société.
     * @throws EntitiesException si une erreur survient lors de la création de la société.
     */

    public Societe(int idSociete, String raisonSociale,
                   String numRue, String nomRue, String cdPostal, String ville,
                   String telephone, String mail, String commentaire) throws EntitiesException{
        setId(idSociete);
        setRaisonSociale(raisonSociale);
        setNumRue(numRue);
        setNomRue(nomRue);
        setCdPostal(cdPostal);
        setVille(ville);
        setTelephone(telephone);
        setMail(mail);
        setCommentaire(commentaire);
    }

    //constructeur pour le test
    public Societe() {
    }
    /**
     * Définit l'identifiant de la société.
     *
     * @param p_id L'identifiant à définir.
     * @throws EntitiesException si l'identifiant est inférieur ou égal à zéro.
     */
    public void setId(int p_id) throws EntitiesException{
        if (p_id>0)
        {
            idSociete = p_id;
        }else throw new EntitiesException("Id doit etre superieur a zero",1);
    }
    /**
     * Renvoie l'identifiant de la société.
     *
     * @return L'identifiant de la société.
     */
    public int getId() {
        return idSociete;
    }
    /**
     * Définit la raison sociale de la société.
     *
     * @param p_raisonSocial La raison sociale à définir.
     * @throws EntitiesException si la raison sociale est nulle, vide ou dépasse 40 caractères.
     */
    public void setRaisonSociale(String p_raisonSocial) throws EntitiesException {
        if (p_raisonSocial != null && !p_raisonSocial.isEmpty() && p_raisonSocial.length() < 40){
        raisonSociale = p_raisonSocial;
        }else throw new EntitiesException("Raison social erroné ",1);

    }
    /**
     * Renvoie la raison sociale de la société.
     *
     * @return La raison sociale de la société.
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }
    /**
     * Définit le numéro de rue de l'adresse de la société.
     *
     * @param p_numRue Le numéro de rue à définir.
     * @throws EntitiesException si le numéro de rue est nul, vide ou dépasse 10 caractères.
     */
    public void setNumRue(String p_numRue) throws EntitiesException{
        if (p_numRue != null && !p_numRue.isEmpty() && p_numRue.length()< 10){
            numRue = p_numRue;
        }else throw new EntitiesException("Numero de rue erroné ",1);
    }
    /**
     * Renvoie le numéro de rue de l'adresse de la société.
     *
     * @return Le numéro de rue de l'adresse de la société.
     */
    public String getNumRue() {
        return numRue;
    }
    /**
     * Définit le nom de la rue de l'adresse de la société.
     *
     * @param p_nomRue Le nom de la rue à définir.
     * @throws EntitiesException si le nom de la rue est nul ou vide.
     */
    public void setNomRue(String p_nomRue) throws EntitiesException {
        if (p_nomRue != null && !p_nomRue.isEmpty() ){
            nomRue = p_nomRue;
        }else throw new EntitiesException("Nom de la rue erroné ",1);;
    }
    /**
     * Renvoie le nom de la rue de l'adresse de la société.
     *
     * @return Le nom de la rue de l'adresse de la société.
     */
    public String getNomRue() {
        return nomRue;
    }
    /**
     * Définit le code postal de l'adresse de la société.
     *
     * @param p_cdPostal Le code postal à définir.
     * @throws EntitiesException si le code postal est nul ou vide.
     */
    public void setCdPostal(String p_cdPostal) throws EntitiesException {
        if (p_cdPostal != null && !p_cdPostal.isEmpty() ){
            cdPostal = p_cdPostal;
        }else throw new EntitiesException("Code postal erroné ",1);
    }
    /**
     * Renvoie le code postal de l'adresse de la société.
     *
     * @return Le code postal de l'adresse de la société.
     */
    public String getCdPostal() {
        return cdPostal;
    }
    /**
     * Définit la ville de l'adresse de la société.
     *
     * @param p_ville La ville à définir.
     * @throws EntitiesException si la ville est nulle ou vide.
     */
    public void setVille(String p_ville) throws EntitiesException {
        if (p_ville != null && !p_ville.isEmpty() ){
            ville = p_ville;
        }else throw new EntitiesException("Ville erroné ",1);
    }
    /**
     * Renvoie la ville de l'adresse de la société.
     *
     * @return La ville de l'adresse de la société.
     */
    public String getVille() {
        return ville;
    }
    /**
     * Définit le numéro de téléphone de la société.
     *
     * @param p_telephone Le numéro de téléphone à définir.
     * @throws EntitiesException si le numéro de téléphone est nul ou contient moins de 10 caractères.
     */
    public void setTelephone(String p_telephone) throws EntitiesException{
        if (p_telephone != null && p_telephone.length() >= 10) {
            telephone = p_telephone;
        } else {
            throw new EntitiesException("Le numéro de téléphone doit avoir au moins 10 caractères",1);
        }
    }
    /**
     * Renvoie le numéro de téléphone de la société.
     *
     * @return Le numéro de téléphone de la société.
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * Définit l'adresse e-mail de la société.
     *
     * @param p_mail L'adresse e-mail à définir.
     * @throws EntitiesException si l'adresse e-mail est nulle ou invalide.
     */
    public void setMail(String p_mail) throws EntitiesException {
        if (p_mail !=null && EmailPattern.isValidEmail(p_mail)) {
            mail = p_mail;
        } else {
            throw new EntitiesException("Email invalide ou manquant",1);
        }
    }
    /**
     * Renvoie l'adresse e-mail de la société.
     *
     * @return L'adresse e-mail de la société.
     */
    public String getMail() {
        return mail;
    }
    /**
     * Définit le commentaire associé à la société.
     *
     * @param p_commentaire Le commentaire à définir.
     */
    public void setCommentaire(String p_commentaire) {
        commentaire = p_commentaire;
    }
    /**
     * Renvoie le commentaire associé à la société.
     *
     * @return Le commentaire associé à la société.
     */
    public String getCommentaire() {
        return commentaire;
    }
    /**
     * Renvoie une représentation textuelle de l'objet Societe.
     *
     * @return La représentation textuelle de l'objet Societe.
     */
    public String stToString(){
        return "id Societe: " + getId() + '\'' +
                ", Raison Sociale: " + getRaisonSociale() + '\'' +
                "Numero rue: "+getNumRue()+ '\'' +
                "Nom rue: "+getNomRue()+ '\'' +
                "Code postal: "+getCdPostal()+ '\'' +
                "Ville: "+getVille()+ '\'' +
                "Telephone: "+getTelephone()+ '\'' +
                "Email: "+getMail()+ '\'' +
                "Commentaire: "+getCommentaire()+ '\'' ;
    }
}

