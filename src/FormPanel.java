import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {
    private JComboBox<String> motherboardComboBox, processorComboBox, ramComboBox, storageComboBox, powerSupplyComboBox, graphicsCardComboBox, gameComboBox;
    private JButton addToCartButton, calculateFPSButton;
    private FormPanelListener formPanelListener;

    public FormPanel() {
        setLayout(new GridLayout(9, 2));

        // Updated Motherboards (AM5 & LGA1700)
        motherboardComboBox = new JComboBox<>(new String[]{
            "Choose motherboard...",
            "ASUS A620M - $150",
            "MSI B650 Tomahawk - $220",
            "Gigabyte X670 Aorus Elite - $350",
            "ASUS Z790 Prime - $400",
            "MSI Z690 Pro - $300",
            "Gigabyte B660M DS3H - $180"
        });

        // Updated CPUs (14th Gen Intel and Ryzen 9000 series)
        processorComboBox = new JComboBox<>(new String[]{
            "Choose processor...",
            "Intel Core i3-14100 - $150",
            "Intel Core i5-14600K - $300",
            "Intel Core i7-14700K - $450",
            "Intel Core i9-14900K - $600",
            "AMD Ryzen 5 9600X - $300",
            "AMD Ryzen 7 9700X - $450",
            "AMD Ryzen 9 7900X - $550",
            "AMD Ryzen 9 7950X - $700"
        });

        // Updated DDR5 RAM options with more sizes and speeds
        ramComboBox = new JComboBox<>(new String[]{
            "Choose RAM...",
            "DDR5 32GB 6400MHz - $400",
            "DDR5 32GB 6000MHz - $350",
            "DDR5 16GB 6400MHz - $250",
            "DDR5 16GB 6000MHz - $200",
            "DDR5 16GB 5200MHz - $180"
        });

        // Updated Storage options
        storageComboBox = new JComboBox<>(new String[]{
            "Choose storage...",
            "Samsung 980 PRO 2TB NVMe - $300",
            "WD Black SN850X 1TB NVMe - $180",
            "Seagate FireCuda 530 2TB NVMe - $270",
            "Crucial P3 1TB NVMe - $120",
            "WD Blue 1TB HDD - $50",
            "Seagate Barracuda 2TB HDD - $70"
        });

        // Updated PSU options with a wider range of power capacity
        powerSupplyComboBox = new JComboBox<>(new String[]{
            "Choose PSU...",
            "450W - $100",
            "550W - $120",
            "650W - $140",
            "800W - $160",
            "1000W - $200",
            "1200W - $250"
        });

        // Updated GPUs with additional models
        graphicsCardComboBox = new JComboBox<>(new String[]{
            "Choose GPU...",
            "NVIDIA GeForce RTX 4090 - $1800",
            "NVIDIA GeForce RTX 4080 - $1200",
            "NVIDIA GeForce RTX 4070 Ti - $900",
            "AMD Radeon RX 7900 XTX - $1000",
            "AMD Radeon RX 7900 XT - $850",
            "AMD Radeon RX 7800 XT - $700",
            "AMD Radeon RX 7700 XT - $600",
            "AMD Radeon RX 7600 XT - $500",
            "NVIDIA GeForce RTX 3080 - $700",
            "NVIDIA GeForce RTX 3070 Ti - $600",
            "NVIDIA GeForce RTX 3060 - $400",
            "NVIDIA GeForce RTX 2060 - $350"
        });

        // Game selection
        gameComboBox = new JComboBox<>(new String[]{"Choose game...", "Cyberpunk 2077", "Fortnite", "Minecraft"});

        // Add labels and components to the panel
        add(new JLabel("Motherboard:"));
        add(motherboardComboBox);
        add(new JLabel("Processor:"));
        add(processorComboBox);
        add(new JLabel("RAM:"));
        add(ramComboBox);
        add(new JLabel("Storage:"));
        add(storageComboBox);
        add(new JLabel("Power Supply:"));
        add(powerSupplyComboBox);
        add(new JLabel("Graphics Card:"));
        add(graphicsCardComboBox);
        add(new JLabel("Game:"));
        add(gameComboBox);

        // Add to Cart Button
        addToCartButton = new JButton("Add to Cart");
        add(addToCartButton);

        // Calculate FPS Button
        calculateFPSButton = new JButton("Calculate FPS");
        add(calculateFPSButton);

        // Action Listeners for the buttons
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (formPanelListener != null) {
                    if (allComponentsSelected()) {
                        formPanelListener.formEventOccurred(new FormEvent(
                                motherboardComboBox.getSelectedItem().toString(),
                                processorComboBox.getSelectedItem().toString(),
                                ramComboBox.getSelectedItem().toString(),
                                storageComboBox.getSelectedItem().toString(),
                                powerSupplyComboBox.getSelectedItem().toString(),
                                graphicsCardComboBox.getSelectedItem().toString(),
                                gameComboBox.getSelectedItem().toString()
                        ), true); // true means add-to-cart event
                    } else {
                        JOptionPane.showMessageDialog(FormPanel.this, "Please select all components.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        calculateFPSButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (formPanelListener != null) {
                    if (allComponentsSelected()) {
                        formPanelListener.formEventOccurred(new FormEvent(
                                motherboardComboBox.getSelectedItem().toString(),
                                processorComboBox.getSelectedItem().toString(),
                                ramComboBox.getSelectedItem().toString(),
                                storageComboBox.getSelectedItem().toString(),
                                powerSupplyComboBox.getSelectedItem().toString(),
                                graphicsCardComboBox.getSelectedItem().toString(),
                                gameComboBox.getSelectedItem().toString()
                        ), false); // false means calculate FPS event
                    } else {
                        JOptionPane.showMessageDialog(FormPanel.this, "Please select all components.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    // Ensure all components are selected
    private boolean allComponentsSelected() {
        return motherboardComboBox.getSelectedIndex() != 0 &&
               processorComboBox.getSelectedIndex() != 0 &&
               ramComboBox.getSelectedIndex() != 0 &&
               storageComboBox.getSelectedIndex() != 0 &&
               powerSupplyComboBox.getSelectedIndex() != 0 &&
               graphicsCardComboBox.getSelectedIndex() != 0 &&
               gameComboBox.getSelectedIndex() != 0;
    }

    // Set the listener for the form events
    public void setFormPanelListener(FormPanelListener listener) {
        this.formPanelListener = listener;
    }
}
