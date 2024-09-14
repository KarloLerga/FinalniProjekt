package event;

/**
 * Sučelje ToolBarListener koje služi za obradu događaja vezanih uz alatnu traku.
 * Ova metoda se poziva kada se dogodi neki događaj na alatnoj traci.
 */
public interface ToolBarListener {
    /**
     * Metoda koja se poziva kada se dogodi događaj na alatnoj traci.
     *
     * @param event Objekt ToolBarEvent koji sadrži informacije o događaju.
     */
    void toolBarEventOccurred(ToolBarEvent event);
}