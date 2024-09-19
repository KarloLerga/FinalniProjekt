package manager;

import model.Component;

import java.util.*;

/**
 * Klasa OrderManager upravlja košaricom i narudžbama, provjerava kompatibilnost komponenti i izračunava FPS za igre.
 */
public class OrderManager {
    // Lista komponenti u košarici
    private List<Component> cart = new ArrayList<>();
    // Ukupna cijena komponenti u košarici
    private double totalPrice = 0.0;

    /**
     * Dodaje komponentu u košaricu i ažurira ukupnu cijenu.
     *
     * @param component Komponenta koja se dodaje u košaricu.
     */
    public void addComponentToCart(Component component) {
        // Dodaje komponentu u košaricu
        cart.add(component);
        // Ažurira ukupnu cijenu
        totalPrice += component.getPrice();
    }

    /**
     * Provjerava kompatibilnost odabranih komponenti (PSU, GPU, matična ploča, CPU, RAM).
     * Vraća poruku o kompatibilnosti ili opis greške.
     *
     * @param selectedComponents Mapa odabranih komponenti.
     * @return String koji sadrži poruku o kompatibilnosti ili grešku.
     */
    public String checkCompatibility(Map<String, String> selectedComponents) {
        String gpu = selectedComponents.get("graphicsCard");
        String psu = selectedComponents.get("psu");
        String motherboard = selectedComponents.get("motherboard");
        String processor = selectedComponents.get("processor");
        String ram = selectedComponents.get("ram");

        // Provjera kompatibilnosti napajanja (PSU) s grafičkom karticom (GPU)
        int gpuPowerRequirement = getGpuPowerRequirement(gpu);
        int psuPowerCapacity = getPsuPowerCapacity(psu);

        if (psuPowerCapacity < gpuPowerRequirement) {
            return "Incompatible PSU for the selected GPU. The selected GPU requires at least " + gpuPowerRequirement + "W, but the PSU only provides " + psuPowerCapacity + "W.";
        }

        // Provjera kompatibilnosti procesora i matične ploče po socket tipu
        String motherboardSocketType = getMotherboardSocketType(motherboard);
        String cpuSocketType = getCpuSocketType(processor);

        if (!motherboardSocketType.equals(cpuSocketType)) {
            return "Incompatible motherboard and CPU socket types. The selected motherboard uses a " + motherboardSocketType + " socket, while the CPU uses a " + cpuSocketType + " socket.";
        }

        // Provjera kompatibilnosti RAM memorije s matičnom pločom
        if (!isRamCompatibleWithMotherboard(motherboardSocketType, ram)) {
            return "Incompatible RAM for the selected motherboard. The selected RAM is not compatible with the " + motherboardSocketType + " motherboard.";
        }

        return "compatible"; // Komponente su kompatibilne
    }

    /**
     * Izračunava FPS (frames per second) na temelju odabranih komponenti, igre i rezolucije.
     *
     * @param cpu Procesor.
     * @param gpu Grafička kartica.
     * @param ram RAM memorija.
     * @param game Igra.
     * @param resolution Odabrana rezolucija (1080p, 1440p, 2160p).
     * @return Procijenjeni FPS.
     */
    public double calculateFPS(String cpu, String gpu, String ram, String game, String resolution) {
        // Dohvaća faktor performansi za CPU, GPU i RAM te osnovni FPS za igru
        double cpuFactor = getCpuPerformanceFactor(cpu);
        double gpuFactor = getGpuPerformanceFactor(gpu);
        double ramFactor = getRamPerformanceFactor(ram);
        double baseFPS = getGameBaseFPS(game);

        // Smanjenje FPS-a ovisno o rezoluciji
        switch (resolution) {
            case "1440p":
                baseFPS *= 0.75; // Smanjenje FPS-a za 25% za 1440p rezoluciju
                break;
            case "2160p":
                baseFPS *= 0.5; // Smanjenje FPS-a za 50% za 2160p rezoluciju
                break;
        }

        // Konačni izračun FPS-a na temelju svih faktora
        return baseFPS * cpuFactor * gpuFactor * ramFactor;
    }

    // Pomoćne metode za izračun performansi različitih komponenti:

    /**
     * Vraća faktor performansi CPU-a na temelju odabranog modela.
     *
     * @param cpu Procesor.
     * @return Faktor performansi CPU-a.
     */
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
                return 0.8; // Default za slabije CPU-ove
        }
    }

    /**
     * Vraća faktor performansi GPU-a na temelju odabranog modela.
     *
     * @param gpu Grafička kartica.
     * @return Faktor performansi GPU-a.
     */
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
                return 0.8; // Default za slabije GPU-ove
        }
    }

    /**
     * Vraća faktor performansi RAM-a na temelju brzine memorije.
     *
     * @param ram RAM memorija.
     * @return Faktor performansi RAM-a.
     */
    private double getRamPerformanceFactor(String ram) {
        if (ram.contains("6400MHz")) {
            return 1.3;
        } else if (ram.contains("6000MHz")) {
            return 1.2;
        } else if (ram.contains("5200MHz")) {
            return 1.1;
        } else {
            return 1.0; // Default performanse za sporiji RAM
        }
    }

    /**
     * Vraća osnovni FPS za određenu igru.
     *
     * @param game Igra.
     * @return Osnovni FPS.
     */
    private double getGameBaseFPS(String game) {
        switch (game) {
            case "Cyberpunk 2077":
                return 50.0; // Vrlo zahtjevna igra
            case "Fortnite":
                return 120.0; // Srednje zahtjevna igra
            case "Minecraft":
                return 200.0; // Najmanje zahtjevna igra
            default:
                return 100.0; // Default FPS za nepoznate igre
        }
    }

    // Pomoćne metode za provjeru kompatibilnosti:

    /**
     * Dohvaća minimalnu snagu napajanja za odabranu grafičku karticu.
     *
     * @param gpu Grafička kartica.
     * @return Minimalna potrebna snaga PSU (u W).
     */
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
            return 450; // Default za manje zahtjevne grafičke kartice
        }
    }

    /**
     * Dohvaća snagu odabranog napajanja (PSU).
     *
     * @param psu Napajanje.
     * @return Snaga napajanja (u W).
     */
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
                return 0; // Default za nepoznato napajanje
        }
    }

    /**
     * Vraća socket tip odabrane matične ploče.
     *
     * @param motherboard Matična ploča.
     * @return Socket tip matične ploče.
     */
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
                return ""; // Nepoznati socket
        }
    }

    /**
     * Vraća socket tip procesora.
     *
     * @param cpu Procesor.
     * @return Socket tip procesora.
     */
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
                return ""; // Nepoznati socket
        }
    }

    /**
     * Provjerava je li RAM kompatibilan s odabranom matičnom pločom.
     *
     * @param motherboardSocket Socket tip matične ploče.
     * @param ram RAM memorija.
     * @return true ako je RAM kompatibilan s matičnom pločom, inače false.
     */
    private boolean isRamCompatibleWithMotherboard(String motherboardSocket, String ram) {
        return ram.startsWith("DDR5"); // Kompatibilnost za DDR5 RAM
    }

    /**
     * Vraća komponente iz košarice.
     *
     * @return Lista komponenti u košarici.
     */
    public List<Component> getCart() {
        return cart;
    }

    /**
     * Vraća ukupnu cijenu komponenti u košarici.
     *
     * @return Ukupna cijena.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Briše sadržaj košarice.
     */
    public void clearCart() {
        cart.clear();
        totalPrice = 0.0; // Resetira ukupnu cijenu
    }
}
