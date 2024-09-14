package event;

/**
 * Sučelje LoginListener koje služi za obradu događaja uspješne prijave korisnika.
 * Ova metoda se poziva kada je prijava uspješna.
 */
public interface LoginListener {
    /**
     * Metoda koja se poziva kada je prijava uspješna.
     */
    void loginSuccessful();
}