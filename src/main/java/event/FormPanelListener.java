package event;

/**
 * Sučelje FormPanelListener koje služi za obradu događaja vezanih uz formu.
 * Ova metoda se poziva kada korisnik dovrši odabir komponenti ili izvrši neku akciju na formi.
 */
public interface FormPanelListener {
    /**
     * Metoda koja se poziva kada se dogodi događaj forme.
     *
     * @param event Objekt FormEvent koji sadrži informacije o odabranim komponentama.
     * @param isAddToCart Boolean vrijednost koja označava je li događaj vezan uz dodavanje u košaricu (true) ili izračun FPS-a (false).
     */
    void formEventOccurred(FormEvent event, boolean isAddToCart);
}