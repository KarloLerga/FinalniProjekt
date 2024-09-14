package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Klasa ViewPanel služi za prikaz košarice s komponentama, ukupne cijene i procijenjenog FPS-a.
 * Koristi se za ažuriranje prikaza odabranih komponenti i prikazivanje rezultata simulacije.
 */
public class ViewPanel extends JPanel {
    private JTextArea cartArea; // Tekstualno polje za prikaz komponenti u košarici
    private JLabel totalPriceLabel; // Labela za prikaz ukupne cijene
    private JLabel fpsLabel; // Labela za prikaz procijenjenog FPS-a

    /**
     * Konstruktor ViewPanel inicijalizira layout i komponente panela.
     * Postavlja područje za prikaz košarice, ukupne cijene i procijenjenog FPS-a.
     */
    public ViewPanel() {
        setLayout(new BorderLayout()); // Postavljanje BorderLayout-a za raspored komponenata

        cartArea = new JTextArea(10, 50); // Tekstualno polje za prikaz košarice s 10 redaka i 50 stupaca
        cartArea.setEditable(false); // Onemogućava uređivanje teksta u polju
        JScrollPane cartScrollPane = new JScrollPane(cartArea); // Omotava tekstualno polje u scroll pane za pomicanje

        totalPriceLabel = new JLabel("Total Price: $0.0"); // Labela za prikaz početne cijene
        fpsLabel = new JLabel("Estimated FPS (1080p): N/A"); // Labela za prikaz početnog FPS-a

        // Dodaje komponente na panel: košaricu u centar, cijenu na jug, FPS na sjever
        add(cartScrollPane, BorderLayout.CENTER);
        add(totalPriceLabel, BorderLayout.SOUTH);
        add(fpsLabel, BorderLayout.NORTH);
    }

    /**
     * Ažurira prikaz košarice i ukupnu cijenu na temelju odabranih komponenti.
     *
     * @param cart Lista komponenti u košarici.
     * @param totalPrice Ukupna cijena komponenti.
     */
    public void updateCart(List<model.Component> cart, double totalPrice) {
        cartArea.setText(""); // Čisti trenutno prikazane komponente
        for (model.Component component : cart) {
            cartArea.append(component.toString() + "\n"); // Dodaje svaku komponentu u tekstualno polje
        }
        totalPriceLabel.setText("Total Price: $" + totalPrice); // Ažurira prikaz ukupne cijene
    }


    /**
     * Prikazuje procijenjeni FPS na temelju odabranih komponenti.
     *
     * @param fps Procijenjeni FPS.
     */
    public void displayFPS(double fps) {
        fpsLabel.setText("Estimated FPS (1080p): " + Math.round(fps) + " FPS"); // Ažurira prikaz FPS-a s zaokruženom vrijednošću
    }
}
