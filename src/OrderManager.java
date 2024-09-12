import java.util.*;

public class OrderManager {
    private List<Component> cart = new ArrayList<>();
    private double totalPrice = 0.0;

    public void addComponentToCart(Component component) {
        cart.add(component);
        totalPrice += component.getPrice();
    }

    // Compatibility checks for PSU, GPU, Motherboard, and CPU
    public boolean checkCompatibility(Map<String, String> selectedComponents) {
        String gpu = selectedComponents.get("graphicsCard");
        String psu = selectedComponents.get("psu");
        String motherboard = selectedComponents.get("motherboard");
        String processor = selectedComponents.get("processor");

        // PSU and GPU power requirements check
        int gpuPowerRequirement = getGpuPowerRequirement(gpu);
        int psuPowerCapacity = getPsuPowerCapacity(psu);

        // Motherboard and CPU socket types compatibility check
        String motherboardSocketType = getMotherboardSocketType(motherboard);
        String cpuSocketType = getCpuSocketType(processor);

        // PSU compatibility with GPU
        if (psuPowerCapacity < gpuPowerRequirement) {
            System.out.println("Incompatible PSU for selected GPU.");
            return false;
        }

        // Motherboard and CPU socket types must match
        if (!motherboardSocketType.equals(cpuSocketType)) {
            System.out.println("Incompatible motherboard and CPU socket types.");
            return false;
        }

        return true; // All components are compatible
    }

    // FPS calculation based on CPU, GPU, RAM, and game
    public double calculateFPS(String cpu, String gpu, String ram, String game) {
        double cpuFactor = 0.0;
        double gpuFactor = 0.0;
        double ramFactor = 0.0;
        double baseFPS = 0.0;

        // CPU performance factor
        if (cpu.equals("Intel Core i7")) {
            cpuFactor = 1.2;
        } else if (cpu.equals("AMD Ryzen 5")) {
            cpuFactor = 1.0;
        }

        // GPU performance factor
        if (gpu.equals("NVIDIA GeForce RTX 3080")) {
            gpuFactor = 1.5;
        } else if (gpu.equals("AMD Radeon RX 6800 XT")) {
            gpuFactor = 1.4;
        } else if (gpu.equals("NVIDIA GeForce GTX 1660 Super")) {
            gpuFactor = 1.0;
        }

        // RAM performance factor
        if (ram.equals("DDR5")) {
            ramFactor = 1.1;
        } else {
            ramFactor = 1.0;
        }

        // Base FPS depending on the game
        switch (game) {
            case "Cyberpunk 2077":
                baseFPS = 50.0; // Very demanding
                break;
            case "Fortnite":
                baseFPS = 100.0; // Medium demanding
                break;
            case "Minecraft":
                baseFPS = 200.0; // Least demanding
                break;
        }

        // Final FPS calculation based on component factors
        return baseFPS * cpuFactor * gpuFactor * ramFactor;
    }

    // Helper methods for compatibility checks
    private int getGpuPowerRequirement(String gpu) {
        switch (gpu) {
            case "NVIDIA GeForce RTX 3080":
                return 750; // Requires 750W
            case "AMD Radeon RX 6800 XT":
                return 650; // Requires 650W
            case "NVIDIA GeForce GTX 1660 Super":
                return 450; // Requires 450W
            default:
                return 0; // Unknown GPU
        }
    }

    private int getPsuPowerCapacity(String psu) {
        switch (psu) {
            case "500W PSU":
                return 500;
            case "650W PSU":
                return 650;
            case "750W PSU":
                return 750;
            default:
                return 0; // Unknown PSU
        }
    }

    private String getMotherboardSocketType(String motherboard) {
        switch (motherboard) {
            case "ASUS ROG Z490":
                return "LGA1200"; // Intel-based socket
            case "MSI MAG B550M":
                return "AM4"; // AMD-based socket
            default:
                return ""; // Unknown socket type
        }
    }

    private String getCpuSocketType(String cpu) {
        switch (cpu) {
            case "Intel Core i7":
                return "LGA1200"; // Intel socket
            case "AMD Ryzen 5":
                return "AM4"; // AMD socket
            default:
                return ""; // Unknown socket type
        }
    }

    public List<Component> getCart() {
        return cart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
