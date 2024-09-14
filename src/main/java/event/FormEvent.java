package event;

import model.Component;

import java.util.Map;

/**
 * Klasa FormEvent predstavlja događaj forme gdje se biraju komponente.
 * Sadrži informacije o odabranim komponentama i metodama za dobivanje cijena.
 */
public class FormEvent {

    // Privatni atributi koji predstavljaju odabrane komponente
    private String motherboard;
    private String processor;
    private String ram;
    private String storage;
    private String psu;
    private String graphicsCard;
    private String game;

    /**
     * Konstruktor koji inicijalizira sve odabrane komponente.
     *
     * @param motherboard Matična ploča.
     * @param processor Procesor.
     * @param ram RAM memorija.
     * @param storage Pohrana.
     * @param psu Napajanje.
     * @param graphicsCard Grafička kartica.
     * @param game Igra.
     */
    public FormEvent(String motherboard, String processor, String ram, String storage, String psu, String graphicsCard, String game) {
        this.motherboard = motherboard;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.psu = psu;
        this.graphicsCard = graphicsCard;
        this.game = game;
    }

    /**
     * Metoda koja vraća mapu odabranih komponenti, gdje je ključ naziv komponente, a vrijednost odabrana opcija.
     *
     * @return Mapa odabranih komponenti.
     */
    public Map<String, String> getSelectedComponents() {
        return Map.of(
                "motherboard", motherboard,
                "processor", processor,
                "ram", ram,
                "storage", storage,
                "psu", psu,
                "graphicsCard", graphicsCard,
                "game", game
        );
    }

    /**
     * Metoda koja vraća cijenu komponente na temelju njezina naziva.
     *
     * @param componentName Naziv komponente čija se cijena traži.
     * @return Cijena komponente.
     */
    public double getPriceForComponent(String componentName) {
        switch (componentName) {
            // AMD matične ploče (AM5)
            case "ASUS A620M - $150":
                return 150.0;
            case "MSI B650 Tomahawk - $220":
                return 220.0;
            case "Gigabyte X670 Aorus Elite - $350":
                return 350.0;

            // Intel matične ploče (LGA1700)
            case "ASUS Z790 Prime - $400":
                return 400.0;
            case "MSI Z690 Pro - $300":
                return 300.0;
            case "Gigabyte B660M DS3H - $180":
                return 180.0;

            // DDR5 RAM opcije s različitim brzinama i kapacitetima
            case "DDR5 32GB 6400MHz - $400":
                return 400.0;
            case "DDR5 32GB 6000MHz - $350":
                return 350.0;
            case "DDR5 16GB 6400MHz - $250":
                return 250.0;
            case "DDR5 16GB 6000MHz - $200":
                return 200.0;
            case "DDR5 16GB 5200MHz - $180":
                return 180.0;

            // Procesori (Intel 14. generacija i Ryzen 9000 serija)
            case "Intel Core i3-14100 - $150":
                return 150.0;
            case "Intel Core i5-14600K - $300":
                return 300.0;
            case "Intel Core i7-14700K - $450":
                return 450.0;
            case "Intel Core i9-14900K - $600":
                return 600.0;
            case "AMD Ryzen 5 9600X - $300":
                return 300.0;
            case "AMD Ryzen 7 9700X - $450":
                return 450.0;
            case "AMD Ryzen 9 7900X - $550":
                return 550.0;
            case "AMD Ryzen 9 7950X - $700":
                return 700.0;

            // Pohrana (NVMe, SSD, HDD)
            case "Samsung 980 PRO 2TB NVMe - $300":
                return 300.0;
            case "WD Black SN850X 1TB NVMe - $180":
                return 180.0;
            case "Seagate FireCuda 530 2TB NVMe - $270":
                return 270.0;
            case "Crucial P3 1TB NVMe - $120":
                return 120.0;
            case "Samsung 970 EVO Plus 2TB NVMe - $250":
                return 250.0;
            case "WD Blue 1TB HDD - $50":
                return 50.0;
            case "Seagate Barracuda 2TB HDD - $70":
                return 70.0;
            case "Toshiba X300 4TB HDD - $150":
                return 150.0;
            case "Crucial MX500 1TB SSD - $100":
                return 100.0;
            case "Samsung 860 EVO 1TB SSD - $150":
                return 150.0;

            // Cijene napajanja (450W - 1200W)
            case "450W - $100":
                return 100.0;
            case "550W - $120":
                return 120.0;
            case "650W - $140":
                return 140.0;
            case "800W - $160":
                return 160.0;
            case "1000W - $200":
                return 200.0;
            case "1200W - $250":
                return 250.0;

            // Cijene grafičkih kartica (ažurirane)
            case "NVIDIA GeForce RTX 4090 - $1800":
                return 1800.0;
            case "NVIDIA GeForce RTX 4080 - $1200":
                return 1200.0;
            case "NVIDIA GeForce RTX 4070 Ti - $900":
                return 900.0;
            case "AMD Radeon RX 7900 XTX - $1000":
                return 1000.0;
            case "AMD Radeon RX 7900 XT - $850":
                return 850.0;
            case "AMD Radeon RX 7800 XT - $700":
                return 700.0;
            case "AMD Radeon RX 7700 XT - $600":
                return 600.0;
            case "AMD Radeon RX 7600 XT - $500":
                return 500.0;
            case "NVIDIA GeForce RTX 3060 - $400":
                return 400.0;
            case "NVIDIA GeForce RTX 2060 - $350":
                return 350.0;

            // Vraća 0.0 ako komponenta nije prepoznata
            default:
                return 0.0;
        }
    }

    /**
     * Metoda koja vraća objekt Component s odgovarajućom cijenom na temelju imena komponente.
     *
     * @param componentName Naziv komponente.
     * @return Objekt Component.
     */
    public Component getComponent(String componentName) {
        double price = getPriceForComponent(componentName);
        return new Component(componentName, price);
    }
}
