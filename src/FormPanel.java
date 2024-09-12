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

        motherboardComboBox = new JComboBox<>(new String[]{"Choose motherboard...", "ASUS ROG Z490", "MSI MAG B550M"});
        processorComboBox = new JComboBox<>(new String[]{"Choose processor...", "Intel Core i7", "AMD Ryzen 5"});
        ramComboBox = new JComboBox<>(new String[]{"Choose RAM...", "DDR4", "DDR5"});
        storageComboBox = new JComboBox<>(new String[]{"Choose storage...", "1TB SSD", "500GB SSD", "2TB HDD"});
        powerSupplyComboBox = new JComboBox<>(new String[]{"Choose PSU...", "500W PSU", "650W PSU", "750W PSU"});
        graphicsCardComboBox = new JComboBox<>(new String[]{"Choose GPU...", "NVIDIA GeForce RTX 3080", "AMD Radeon RX 6800 XT", "NVIDIA GeForce GTX 1660 Super"});
        gameComboBox = new JComboBox<>(new String[]{"Choose game...", "Cyberpunk 2077", "Fortnite", "Minecraft"});

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

        // Add Action Listeners for Buttons
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
                        ), true); // true means this is an add-to-cart event
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
                        ), false); // false means this is a calculate FPS event
                    } else {
                        JOptionPane.showMessageDialog(FormPanel.this, "Please select all components.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    private boolean allComponentsSelected() {
        return motherboardComboBox.getSelectedIndex() != 0 &&
                processorComboBox.getSelectedIndex() != 0 &&
                ramComboBox.getSelectedIndex() != 0 &&
                storageComboBox.getSelectedIndex() != 0 &&
                powerSupplyComboBox.getSelectedIndex() != 0 &&
                graphicsCardComboBox.getSelectedIndex() != 0 &&
                gameComboBox.getSelectedIndex() != 0;
    }

    public void setFormPanelListener(FormPanelListener listener) {
        this.formPanelListener = listener;
    }
}
