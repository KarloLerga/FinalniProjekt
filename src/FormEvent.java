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
            case "ASUS ROG Z490":
                return 250.0; // Price for ASUS ROG Z490 motherboard
            case "MSI MAG B550M":
                return 150.0; // Price for MSI MAG B550M motherboard
            case "Intel Core i7":
                return 300.0; // Price for Intel Core i7 processor
            case "AMD Ryzen 5":
                return 200.0; // Price for AMD Ryzen 5 processor
            case "DDR4":
                return 100.0; // Price for DDR4 RAM
            case "DDR5":
                return 150.0; // Price for DDR5 RAM
            case "1TB SSD":
                return 120.0; // Price for 1TB SSD
            case "500GB SSD":
                return 80.0; // Price for 500GB SSD
            case "2TB HDD":
                return 70.0; // Price for 2TB HDD
            case "500W PSU":
                return 50.0; // Price for 500W PSU
            case "650W PSU":
                return 80.0; // Price for 650W PSU
            case "750W PSU":
                return 100.0; // Price for 750W PSU
            case "NVIDIA GeForce RTX 3080":
                return 700.0; // Price for NVIDIA GeForce RTX 3080 GPU
            case "AMD Radeon RX 6800 XT":
                return 600.0; // Price for AMD Radeon RX 6800 XT GPU
            case "NVIDIA GeForce GTX 1660 Super":
                return 400.0; // Price for NVIDIA GeForce GTX 1660 Super GPU
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
