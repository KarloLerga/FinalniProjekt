package ui;

import event.FormEvent;
import event.FormPanelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa FormPanel predstavlja grafički panel za unos i odabir komponenti računala.
 * Koristi se za stvaranje forme u kojoj korisnik može odabrati različite komponente te igre.
 */
public class FormPanel extends JPanel {
    // Kombinirane liste za odabir komponenti
    private JComboBox<String> motherboardComboBox, processorComboBox, ramComboBox, storageComboBox, powerSupplyComboBox, graphicsCardComboBox, gameComboBox;
    private JButton addToCartButton, calculateFPSButton;
    private FormPanelListener formPanelListener;

    // Radio buttoni za odabir rezolucije
    private JRadioButton resolution1080p, resolution1440p, resolution2160p;
    private ButtonGroup resolutionGroup;

    /**
     * Konstruktor koji inicijalizira FormPanel i sve njegove elemente.
     * Kreira grafičko sučelje s opcijama za odabir komponenti.
     */
    public FormPanel() {
        setLayout(new GridLayout(11, 2)); // Raspored s 11 redova i 2 stupca

        // Ažurirane opcije matičnih ploča (AM5 & LGA1700)
        motherboardComboBox = new JComboBox<>(new String[]{
                "Odaberite matičnu ploču...",
                "ASUS A620M - $150",
                "MSI B650 Tomahawk - $220",
                "Gigabyte X670 Aorus Elite - $350",
                "ASUS Z790 Prime - $400",
                "MSI Z690 Pro - $300",
                "Gigabyte B660M DS3H - $180"
        });

        // Ažurirane opcije procesora (14. generacija Intel i Ryzen 9000 serija)
        processorComboBox = new JComboBox<>(new String[]{
                "Odaberite procesor...",
                "Intel Core i3-14100 - $150",
                "Intel Core i5-14600K - $300",
                "Intel Core i7-14700K - $450",
                "Intel Core i9-14900K - $600",
                "AMD Ryzen 5 9600X - $300",
                "AMD Ryzen 7 9700X - $450",
                "AMD Ryzen 9 7900X - $550",
                "AMD Ryzen 9 7950X - $700"
        });

        // Ažurirane opcije DDR5 RAM memorije s različitim brzinama i veličinama
        ramComboBox = new JComboBox<>(new String[]{
                "Odaberite RAM...",
                "DDR5 32GB 6400MHz - $400",
                "DDR5 32GB 6000MHz - $350",
                "DDR5 16GB 6400MHz - $250",
                "DDR5 16GB 6000MHz - $200",
                "DDR5 16GB 5200MHz - $180"
        });

        // Ažurirane opcije pohrane
        storageComboBox = new JComboBox<>(new String[]{
                "Odaberite pohranu...",
                "Samsung 980 PRO 2TB NVMe - $300",
                "WD Black SN850X 1TB NVMe - $180",
                "Seagate FireCuda 530 2TB NVMe - $270",
                "Crucial P3 1TB NVMe - $120",
                "WD Blue 1TB HDD - $50",
                "Seagate Barracuda 2TB HDD - $70"
        });

        // Ažurirane opcije napajanja s različitim kapacitetima
        powerSupplyComboBox = new JComboBox<>(new String[]{
                "Odaberite napajanje...",
                "450W - $100",
                "550W - $120",
                "650W - $140",
                "800W - $160",
                "1000W - $200",
                "1200W - $250"
        });

        // Ažurirane opcije grafičkih kartica s dodatnim modelima
        graphicsCardComboBox = new JComboBox<>(new String[]{
                "Odaberite grafičku karticu...",
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

        // Odabir igre
        gameComboBox = new JComboBox<>(new String[]{"Odaberite igru...", "Cyberpunk 2077", "Fortnite", "Minecraft"});

        // Dodavanje labela i komponenti na panel
        add(new JLabel("Matična ploča:"));
        add(motherboardComboBox);
        add(new JLabel("Procesor:"));
        add(processorComboBox);
        add(new JLabel("RAM:"));
        add(ramComboBox);
        add(new JLabel("Pohrana:"));
        add(storageComboBox);
        add(new JLabel("Napajanje:"));
        add(powerSupplyComboBox);
        add(new JLabel("Grafička kartica:"));
        add(graphicsCardComboBox);
        add(new JLabel("Igra:"));
        add(gameComboBox);

        // Radio buttoni za odabir rezolucije
        resolution1080p = new JRadioButton("1080p", true);
        resolution1440p = new JRadioButton("1440p");
        resolution2160p = new JRadioButton("2160p");

        // Grupiraj radio buttone
        resolutionGroup = new ButtonGroup();
        resolutionGroup.add(resolution1080p);
        resolutionGroup.add(resolution1440p);
        resolutionGroup.add(resolution2160p);

        // Panel za rezoluciju s horizontalnim prikazom (FlowLayout)
        JPanel resolutionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resolutionPanel.add(resolution1080p);
        resolutionPanel.add(resolution1440p);
        resolutionPanel.add(resolution2160p);

        // Dodaj panel za rezoluciju
        add(new JLabel("Resolution:"));
        add(resolutionPanel);

        // Gumb za dodavanje u košaricu
        addToCartButton = new JButton("Dodaj u košaricu");
        add(addToCartButton);

        // Gumb za izračunavanje FPS-a
        calculateFPSButton = new JButton("Izračunaj FPS");
        add(calculateFPSButton);

        // ActionListener za gumb Dodaj u košaricu
        addToCartButton.addActionListener(e -> {
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
                    ), true); // true označava dodavanje u košaricu
                } else {
                    JOptionPane.showMessageDialog(FormPanel.this, "Molimo odaberite sve komponente.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // ActionListener za gumb Izračunaj FPS
        calculateFPSButton.addActionListener(e -> {
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
                    ), false); // false označava izračun FPS-a
                } else {
                    JOptionPane.showMessageDialog(FormPanel.this, "Molimo odaberite sve komponente.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    /**
     * Provjerava jesu li sve komponente odabrane.
     *
     * @return true ako su sve komponente odabrane, inače false.
     */
    private boolean allComponentsSelected() {
        return motherboardComboBox.getSelectedIndex() != 0 &&
                processorComboBox.getSelectedIndex() != 0 &&
                ramComboBox.getSelectedIndex() != 0 &&
                storageComboBox.getSelectedIndex() != 0 &&
                powerSupplyComboBox.getSelectedIndex() != 0 &&
                graphicsCardComboBox.getSelectedIndex() != 0 &&
                gameComboBox.getSelectedIndex() != 0;
    }

    /**
     * Getter za radio gumb za 1440p rezoluciju
     * @return JRadioButton za 1440p
     */
    public JRadioButton getResolution1440p() {
        return resolution1440p;
    }

    /**
     * Getter za radio gumb za 2160p rezoluciju
     * @return JRadioButton za 2160p
     */
    public JRadioButton getResolution2160p() {
        return resolution2160p;
    }

    /**
     * Postavlja listener za događaje forme.
     *
     * @param listener Objekt koji implementira FormPanelListener sučelje.
     */
    public void setFormPanelListener(FormPanelListener listener) {
        this.formPanelListener = listener;
    }
}
