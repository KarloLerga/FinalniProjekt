package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.net.URL;

/**
 * Klasa WelcomeScreen predstavlja početni ekran aplikacije PC Builder.
 * Korisnici imaju opciju prijave (login) ili registracije (register).
 */
public class WelcomeScreen extends JFrame {
    private JButton loginButton, registerButton; // Gumbi za prijavu i registraciju
    private JLabel logoLabel; // Labela za prikaz logotipa

    /**
     * Konstruktor WelcomeScreen inicijalizira GUI komponente i postavlja izgled ekrana.
     */
    public WelcomeScreen() {
        setTitle("PC Builder - Welcome"); // Postavlja naslov prozora
        setSize(600, 400); // Postavlja veličinu prozora
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvara aplikaciju na klik gumba za zatvaranje

        // Panel za smještaj komponenti, koristi GridBagLayout za precizno pozicioniranje
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Postavlja margine između komponenata

        // Učitava logotip iz resursa
        URL logoUrl = getClass().getClassLoader().getResource("images/PCBuilder.png");
        if (logoUrl != null) {
            ImageIcon logoIcon = new ImageIcon(logoUrl);

            // Mijenja veličinu logotipa (npr. 200x200 piksela)
            Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(scaledLogo);  // Dodjeljuje skaliranu sliku natrag u ImageIcon
            logoLabel = new JLabel(logoIcon); // Prikazuje logotip
        } else {
            logoLabel = new JLabel("PC Builder Logo"); // Rezervni tekst ako slika nije pronađena
        }

        // Dodaje logotip na vrh panela
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(logoLabel, gbc);

        // Gumb za prijavu
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(loginButton, gbc);

        // Gumb za registraciju
        registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(registerButton, gbc);

        // Postavlja panel kao glavni sadržaj prozora
        setContentPane(panel);

        // Centriranje prozora na ekranu
        setLocationRelativeTo(null);

        // Action listener za gumb prijave
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Zatvara trenutni ekran
                new LoginScreen(); // Otvara ekran za prijavu
            }
        });

        // Action listener za gumb registracije
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Zatvara trenutni ekran
                new RegistrationScreen(); // Otvara ekran za registraciju
            }
        });

        setVisible(true); // Prikazuje prozor
    }
}
