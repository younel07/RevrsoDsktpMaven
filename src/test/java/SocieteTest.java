/**
 * Classe de test pour la classe Societe.
 * Contient des méthodes de test pour vérifier le bon fonctionnement des fonctionnalités de la classe Societe.
 *
 * @author Younes
 * @version 1.0
 * @since 2024-03-24
 *//*

class SocieteTest extends Societe {

    */
/**
     * Teste la méthode setNumRue(String numRue).
     * Vérifie si l'exception EntitiesException est levée lorsque la valeur de numRue est invalide.
     *
     * @param numRueTest La valeur de numRue à tester.
     *//*

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {""})
    void testInvalidSetNumRue(String numRueTest) {
        assertThrows(EntitiesException.class,()->{setNumRue(numRueTest);});
    }

    */
/**
     * Teste la méthode setNumRue(String numRue).
     * Vérifie si aucun exception n'est levée lorsque la valeur de numRue est valide.
     *
     * @param numRueTest La valeur de numRue à tester.
     *//*

    @ParameterizedTest
    @ValueSource(strings = {"55ll"})
    void testSetNumRue(String numRueTest) {
        assertDoesNotThrow(()->{setNumRue(numRueTest);});
    }

    */
/**
     * Teste la méthode setMail(String mail).
     * Vérifie si l'exception EntitiesException est levée lorsque la valeur de mail est invalide.
     *
     * @param mailTest La valeur de mail à tester.
     *//*

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"", "mail", "email.com", "email@com", "email@com.'123"})
    void testInvalidSetMail(String mailTest) {
        assertThrows(EntitiesException.class,()->{setMail(mailTest);});
    }

    */
/**
     * Teste la méthode setMail(String mail).
     * Vérifie si aucun exception n'est levée lorsque la valeur de mail est valide.
     *
     * @param numRueTest La valeur de mail à tester.
     *//*

    @ParameterizedTest
    @ValueSource(strings = {"validemail123@example.com"})
    void testSetMail(String numRueTest) {
        assertDoesNotThrow(()->{setNumRue(numRueTest);});
    }
}
*/
