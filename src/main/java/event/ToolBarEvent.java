package event;

/**
 * Klasa ToolBarEvent predstavlja događaj vezan uz alatnu traku (ToolBar) u aplikaciji.
 * Sadrži informacije o tome je li događaj odjava (logout) ili pregled narudžbi (view orders).
 */
public class ToolBarEvent {
    private boolean isLogoutEvent; // Oznaka za događaj odjave
    private boolean isViewOrdersEvent; // Oznaka za događaj pregleda narudžbi

    /**
     * Konstruktor ToolBarEvent koji prima dvije boolean vrijednosti za odjavu i pregled narudžbi.
     *
     * @param isLogoutEvent Označava je li događaj vezan uz odjavu.
     * @param isViewOrdersEvent Označava je li događaj vezan uz pregled narudžbi.
     */
    public ToolBarEvent(boolean isLogoutEvent, boolean isViewOrdersEvent) {
        this.isLogoutEvent = isLogoutEvent;
        this.isViewOrdersEvent = isViewOrdersEvent;
    }

    /**
     * Provjerava je li događaj odjava.
     *
     * @return true ako je događaj odjava, inače false.
     */
    public boolean isLogoutEvent() {
        return isLogoutEvent;
    }

    /**
     * Provjerava je li događaj pregled narudžbi.
     *
     * @return true ako je događaj pregled narudžbi, inače false.
     */
    public boolean isViewOrdersEvent() {
        return isViewOrdersEvent;
    }
}
