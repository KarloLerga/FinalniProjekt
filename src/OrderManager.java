import java.util.*;

public class OrderManager {
    private List<Component> cart = new ArrayList<>();
    private double totalPrice = 0.0;

    public void addComponentToCart(Component component) {
        cart.add(component);
        totalPrice += component.getPrice();
    }

    // Compatibility checks for PSU, GPU, Motherboard, CPU, and RAM with error messages
    public String checkCompatibility(Map<String, String> selectedComponents) {
        String gpu = selectedComponents.get("graphicsCard");
        String psu = selectedComponents.get("psu");
        String motherboard = selectedComponents.get("motherboard");
        String processor = selectedComponents.get("processor");
        String ram = selectedComponents.get("ram");

        // GPU and PSU compatibility check
        int gpuPowerRequirement = getGpuPowerRequirement(gpu);
        int psuPowerCapacity = getPsuPowerCapacity(psu);

        // Ensure that PSU wattage meets or exceeds the GPU's minimum requirement
        if (psuPowerCapacity < gpuPowerRequirement) {
            return "Incompatible PSU for the selected GPU. The selected GPU requires at least " + gpuPowerRequirement + "W, but the PSU only provides " + psuPowerCapacity + "W.";
        }

        // Motherboard and CPU socket types compatibility check
        String motherboardSocketType = getMotherboardSocketType(motherboard);
        String cpuSocketType = getCpuSocketType(processor);

        if (!motherboardSocketType.equals(cpuSocketType)) {
            return "Incompatible motherboard and CPU socket types. The selected motherboard uses a " + motherboardSocketType + " socket, while the CPU uses a " + cpuSocketType + " socket.";
        }

        // RAM compatibility with motherboard
        if (!isRamCompatibleWithMotherboard(motherboardSocketType, ram)) {
            return "Incompatible RAM for the selected motherboard. The selected RAM is not compatible with the " + motherboardSocketType + " motherboard.";
        }

        return "compatible"; // All components are compatible
    }

    // FPS calculation based on CPU, GPU, RAM, and game
    public double calculateFPS(String cpu, String gpu, String ram, String game) {
        double cpuFactor = getCpuPerformanceFactor(cpu);
        double gpuFactor = getGpuPerformanceFactor(gpu);
        double ramFactor = getRamPerformanceFactor(ram);
        double baseFPS = getGameBaseFPS(game);

        return baseFPS * cpuFactor * gpuFactor * ramFactor;
    }

    // Helper methods for FPS calculation
    private double getCpuPerformanceFactor(String cpu) {
        switch (cpu) {
            case "Intel Core i9-14900K - $600":
                return 1.6;
            case "Intel Core i7-14700K - $450":
                return 1.4;
            case "Intel Core i5-14600K - $300":
                return 1.2;
            case "Intel Core i3-14100 - $150":
                return 1.0;
            case "AMD Ryzen 9 7950X - $700":
                return 1.5;
            case "AMD Ryzen 9 7900X - $550":
                return 1.4;
            case "AMD Ryzen 7 9700X - $450":
                return 1.2;
            case "AMD Ryzen 5 9600X - $300":
                return 1.0;
            default:
                return 0.8;
        }
    }

    private double getGpuPerformanceFactor(String gpu) {
        switch (gpu) {
            case "NVIDIA GeForce RTX 4090 - $1800":
                return 2.0;
            case "NVIDIA GeForce RTX 4080 - $1200":
                return 1.8;
            case "NVIDIA GeForce RTX 4070 Ti - $900":
                return 1.5;
            case "AMD Radeon RX 7900 XTX - $1000":
                return 1.6;
            case "AMD Radeon RX 7900 XT - $850":
                return 1.4;
            case "AMD Radeon RX 7800 XT - $700":
                return 1.3;
            case "AMD Radeon RX 7700 XT - $600":
                return 1.2;
            case "AMD Radeon RX 7600 XT - $500":
            case "NVIDIA GeForce RTX 3060 - $400":
                return 1.0;
            case "NVIDIA GeForce RTX 2060 - $350":
                return 0.9;
            default:
                return 0.8;
        }
    }

    private double getRamPerformanceFactor(String ram) {
        if (ram.contains("6400MHz")) {
            return 1.3;
        } else if (ram.contains("6000MHz")) {
            return 1.2;
        } else if (ram.contains("5200MHz")) {
            return 1.1;
        } else {
            return 1.0;
        }
    }

    private double getGameBaseFPS(String game) {
        switch (game) {
            case "Cyberpunk 2077":
                return 50.0; // Very demanding
            case "Fortnite":
                return 120.0; // Medium demanding
            case "Minecraft":
                return 200.0; // Least demanding
            default:
                return 100.0; // Default base FPS
        }
    }

    // Helper methods for compatibility checks

    // Get GPU power requirement
    private int getGpuPowerRequirement(String gpu) {
        if (gpu.contains("NVIDIA GeForce RTX 4090")) {
            return 1000;
        } else if (gpu.contains("NVIDIA GeForce RTX 4080")) {
            return 850;
        } else if (gpu.contains("AMD Radeon RX 7900 XTX") || gpu.contains("AMD Radeon RX 7800 XT")) {
            return 750;
        } else if (gpu.contains("NVIDIA GeForce RTX 4070 Ti") || gpu.contains("AMD Radeon RX 7700 XT") || gpu.contains("NVIDIA GeForce RTX 3060")) {
            return 550;
        } else if (gpu.contains("AMD Radeon RX 7600 XT")) {
            return 500;
        } else {
            return 450; // Default minimum for unknown GPUs
        }
    }

    // Get PSU power capacity
    private int getPsuPowerCapacity(String psu) {
        switch (psu) {
            case "1200W - $250":
                return 1200;
            case "1000W - $200":
                return 1000;
            case "800W - $160":
                return 800;
            case "650W - $140":
                return 650;
            case "550W - $120":
                return 550;
            case "450W - $100":
                return 450;
            default:
                return 0;
        }
    }

    // Get motherboard socket type
    private String getMotherboardSocketType(String motherboard) {
        switch (motherboard) {
            case "ASUS A620M - $150":
            case "MSI B650 Tomahawk - $220":
            case "Gigabyte X670 Aorus Elite - $350":
                return "AM5"; // AMD socket
            case "ASUS Z790 Prime - $400":
            case "MSI Z690 Pro - $300":
            case "Gigabyte B660M DS3H - $180":
                return "LGA1700"; // Intel socket
            default:
                return ""; // Unknown socket type
        }
    }

    // Get CPU socket type
    private String getCpuSocketType(String cpu) {
        switch (cpu) {
            case "Intel Core i9-14900K - $600":
            case "Intel Core i7-14700K - $450":
            case "Intel Core i5-14600K - $300":
            case "Intel Core i3-14100 - $150":
                return "LGA1700"; // Intel socket
            case "AMD Ryzen 9 7950X - $700":
            case "AMD Ryzen 9 7900X - $550":
            case "AMD Ryzen 7 9700X - $450":
            case "AMD Ryzen 5 9600X - $300":
                return "AM5"; // AMD socket
            default:
                return ""; // Unknown socket type
        }
    }

    // RAM compatibility with motherboard
    private boolean isRamCompatibleWithMotherboard(String motherboardSocket, String ram) {
        return ram.startsWith("DDR5"); // Ensure DDR5 RAM is compatible with AM5 and LGA1700
    }

    public List<Component> getCart() {
        return cart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Clear the cart
    public void clearCart() {
        cart.clear();
        totalPrice = 0.0;
    }
}
