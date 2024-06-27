import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class PCBuilderApp extends JFrame implements ActionListener {
    private JComboBox<String> motherboardComboBox, processorComboBox, ramComboBox, storageComboBox, powerSupplyComboBox, graphicsCardComboBox, gameComboBox;
    private JTextArea cartArea;
    private JLabel totalPriceLabel;
    private List<Component> cart = new ArrayList<>();
    private double totalPrice = 0.0;
    private LoginManager loginManager = new LoginManager();
    private String currentUser = null;

    public PCBuilderApp() {
        setTitle("PC Builder App");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup components selection area
        JPanel selectionPanel = new JPanel(new GridLayout(8, 2));
        add(selectionPanel, BorderLayout.NORTH);

        motherboardComboBox = new JComboBox<>(new String[]{"ASUS ROG Z490", "MSI MAG B550M", "Gigabyte Z390 Aorus Ultra"});
        processorComboBox = new JComboBox<>(new String[]{"Intel Core i7-10700K", "AMD Ryzen 5 3600", "Intel Core i5-9600K"});
        ramComboBox = new JComboBox<>(new String[]{"DDR4", "DDR5"});
        storageComboBox = new JComboBox<>(new String[]{"1TB SSD", "500GB SSD", "2TB HDD"});
        powerSupplyComboBox = new JComboBox<>(new String[]{"500W PSU", "650W PSU", "750W PSU"});
        graphicsCardComboBox = new JComboBox<>(new String[]{"NVIDIA GeForce RTX 3080", "AMD Radeon RX 6800 XT", "NVIDIA GeForce GTX 1660 Super"});
        gameComboBox = new JComboBox<>(new String[]{"Cyberpunk 2077", "Fortnite", "Minecraft"});

        selectionPanel.add(new JLabel("Select Motherboard:"));
        selectionPanel.add(motherboardComboBox);
        selectionPanel.add(new JLabel("Select Processor:"));
        selectionPanel.add(processorComboBox);
        selectionPanel.add(new JLabel("Select RAM:"));
        selectionPanel.add(ramComboBox);
        selectionPanel.add(new JLabel("Select Storage:"));
        selectionPanel.add(storageComboBox);
        selectionPanel.add(new JLabel("Select Power Supply:"));
        selectionPanel.add(powerSupplyComboBox);
        selectionPanel.add(new JLabel("Select Graphics Card:"));
        selectionPanel.add(graphicsCardComboBox);
        selectionPanel.add(new JLabel("Select Game:"));
        selectionPanel.add(gameComboBox);

        // Setup cart area
        cartArea = new JTextArea(10, 50);
        cartArea.setEditable(false);
        JScrollPane cartScrollPane = new JScrollPane(cartArea);
        add(cartScrollPane, BorderLayout.CENTER);

        // Setup total price label
        totalPriceLabel = new JLabel("Total Price: $0.0");
        add(totalPriceLabel, BorderLayout.SOUTH);

        // Setup buttons and action listeners
        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(this);
        selectionPanel.add(addButton);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> loginUser());
        selectionPanel.add(loginButton);

        JButton viewOrdersButton = new JButton("View Orders");
        viewOrdersButton.addActionListener(e -> viewOrders());
        selectionPanel.add(viewOrdersButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!checkCompatibility()) {
            JOptionPane.showMessageDialog(this, "Components are not compatible!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Please login first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selectedItem = (String) motherboardComboBox.getSelectedItem();
        cart.add(new Component(selectedItem, 100)); // Example price
        cartArea.append(selectedItem + " added to cart. Estimated FPS: " + calculateFPS() + "\n");
        updateTotalPrice(100); // Example price update
        saveOrder();
    }

    private void loginUser() {
        String username = JOptionPane.showInputDialog("Username:");
        String password = JOptionPane.showInputDialog("Password:");
        if (loginManager.loginUser(username, password)) {
            currentUser = username;
            JOptionPane.showMessageDialog(this, "Login successful", "Login", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Login failed", "Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewOrders() {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Please login first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Order> orders = loginManager.getUserOrders(currentUser);
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders found.", "Orders", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder ordersText = new StringBuilder();
            for (Order order : orders) {
                ordersText.append(order).append("\n");
            }
            JOptionPane.showMessageDialog(this, ordersText.toString(), "Orders", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveOrder() {
        Order order = new Order(cart, totalPrice);
        loginManager.addOrder(currentUser, order);
    }

    private boolean checkCompatibility() {
        String motherboard = (String) motherboardComboBox.getSelectedItem();
        String processor = (String) processorComboBox.getSelectedItem();
        String ram = (String) ramComboBox.getSelectedItem();
        String psu = (String) powerSupplyComboBox.getSelectedItem();
        String graphicsCard = (String) graphicsCardComboBox.getSelectedItem();

        // Map processors to their sockets
        Map<String, String> processorToSocket = new HashMap<>();
        processorToSocket.put("Intel Core i7-10700K", "LGA1200");
        processorToSocket.put("AMD Ryzen 5 3600", "AM4");
        processorToSocket.put("Intel Core i5-9600K", "LGA1151");

        // Map motherboards to their sockets and RAM types
        Map<String, String[]> motherboardToSpecs = new HashMap<>();
        motherboardToSpecs.put("ASUS ROG Z490", new String[]{"LGA1200", "DDR4"});
        motherboardToSpecs.put("MSI MAG B550M", new String[]{"AM4", "DDR4"});
        motherboardToSpecs.put("Gigabyte Z390 Aorus Ultra", new String[]{"LGA1151", "DDR4"});

        // Map graphics cards to minimum PSU power requirements
        Map<String, Integer> graphicsCardToPower = new HashMap<>();
        graphicsCardToPower.put("NVIDIA GeForce RTX 3080", 750);
        graphicsCardToPower.put("AMD Radeon RX 6800 XT", 650);
        graphicsCardToPower.put("NVIDIA GeForce GTX 1660 Super", 450);

        String[] specs = motherboardToSpecs.get(motherboard);
        int requiredPower = graphicsCardToPower.get(graphicsCard);
        int psuPower = Integer.parseInt(psu.replaceAll("[^0-9]", "")); // Extract number from PSU string

        return processorToSocket.get(processor).equals(specs[0]) &&
                specs[1].equals(ram) &&
                psuPower >= requiredPower;
    }

    private double calculateFPS() {
        String graphicsCard = (String) graphicsCardComboBox.getSelectedItem();
        String cpu = (String) processorComboBox.getSelectedItem();
        String ram = (String) ramComboBox.getSelectedItem();
        String game = (String) gameComboBox.getSelectedItem();

        // Base FPS for games
        Map<String, Integer> gameBaseFPS = new HashMap<>();
        gameBaseFPS.put("Cyberpunk 2077", 30);
        gameBaseFPS.put("Fortnite", 60);
        gameBaseFPS.put("Minecraft", 120);

        // Multipliers for graphics cards, CPUs, and RAM
        Map<String, Double> graphicsCardMultiplier = new HashMap<>();
        graphicsCardMultiplier.put("NVIDIA GeForce RTX 3080", 1.5);
        graphicsCardMultiplier.put("AMD Radeon RX 6800 XT", 1.3);
        graphicsCardMultiplier.put("NVIDIA GeForce GTX 1660 Super", 1.1);

        Map<String, Double> cpuMultiplier = new HashMap<>();
        cpuMultiplier.put("Intel Core i7-10700K", 1.2);
        cpuMultiplier.put("AMD Ryzen 5 3600", 1.1);
        cpuMultiplier.put("Intel Core i5-9600K", 1.05);

        Map<String, Double> ramMultiplier = new HashMap<>();
        ramMultiplier.put("DDR4", 1.05);
        ramMultiplier.put("DDR5", 1.1);

        double baseFPS = gameBaseFPS.get(game);
        double gpuBoost = graphicsCardMultiplier.get(graphicsCard);
        double cpuBoost = cpuMultiplier.get(cpu);
        double ramBoost = ramMultiplier.get(ram);

        return baseFPS * gpuBoost * cpuBoost * ramBoost;
    }

    private void updateTotalPrice(double price) {
        totalPrice += price;
        totalPriceLabel.setText("Total Price: $" + totalPrice);
    }

    public static void main(String[] args) {
        new PCBuilderApp();
    }
}
