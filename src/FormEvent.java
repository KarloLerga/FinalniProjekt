import java.util.Map;

public class FormEvent {
    private String motherboard;
    private String processor;
    private String ram;
    private String storage;
    private String psu;
    private String graphicsCard;
    private String game;

    public FormEvent(String motherboard, String processor, String ram, String storage, String psu, String graphicsCard, String game) {
        this.motherboard = motherboard;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.psu = psu;
        this.graphicsCard = graphicsCard;
        this.game = game;
    }

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

    // Method to return the price of a component based on its name
    public double getPriceForComponent(String componentName) {
        switch (componentName) {
            // AMD motherboards (AM5)
            case "ASUS A620M - $150":
                return 150.0;
            case "MSI B650 Tomahawk - $220":
                return 220.0;
            case "Gigabyte X670 Aorus Elite - $350":
                return 350.0;

            // Intel motherboards (LGA1700)
            case "ASUS Z790 Prime - $400":
                return 400.0;
            case "MSI Z690 Pro - $300":
                return 300.0;
            case "Gigabyte B660M DS3H - $180":
                return 180.0;

            // DDR5 RAM options with varying sizes and speeds
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

            // CPUs (14th Gen Intel and Ryzen 9000 series) prices
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


            // Storage prices
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

            // PSU prices (450W - 1200W)
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

            // GPUs (updated) prices
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

            default:
                return 0.0; // Return 0 if the component is not recognized
        }
    }

    // Return a Component object with its corresponding price
    public Component getComponent(String componentName) {
        double price = getPriceForComponent(componentName);
        return new Component(componentName, price);
    }
}
