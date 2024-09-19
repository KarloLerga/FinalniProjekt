package ui;

import manager.LoginManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Klasa RegistrationScreen predstavlja grafički prozor za registraciju novih korisnika.
 * Korisnici unose svoje korisničko ime i lozinku te mogu odabrati opciju za prikazivanje lozinke.
 */
public class RegistrationScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JCheckBox termsCheckBox; // Checkbox za prihvaćanje uvjeta korištenja
    private JButton registerButton, backButton;
    private LoginManager loginManager;
    private JLabel logoLabel;

    /**
     * Konstruktor RegistrationScreen inicijalizira sve komponente potrebne za registraciju korisnika.
     */
    public RegistrationScreen() {
        loginManager = new LoginManager(); // Kreira instancu LoginManager-a za provjeru i dodavanje korisnika

        setTitle("PC Builder - Register"); // Postavlja naslov prozora
        setSize(650, 450); // Postavlja veličinu prozora
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Postavlja način zatvaranja prozora

        JPanel panel = new JPanel(new GridBagLayout()); // Koristi GridBagLayout za precizno pozicioniranje komponenata
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Postavlja margine između komponenti
        gbc.anchor = GridBagConstraints.CENTER; // Komponente centrirano poravnava

        // Učitava logo za registraciju iz resursa i mijenja veličinu slike
        URL registerLogoUrl = getClass().getClassLoader().getResource("images/register.png");
        if (registerLogoUrl != null) {
            ImageIcon registerLogoIcon = new ImageIcon(registerLogoUrl);
            Image scaledRegisterLogo = registerLogoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Mijenja veličinu slike na 100x100 piksela
            registerLogoIcon = new ImageIcon(scaledRegisterLogo);
            logoLabel = new JLabel(registerLogoIcon); // Prikazuje sliku
        } else {
            logoLabel = new JLabel("Register"); // Prikazuje tekst ako slika nije dostupna
        }

        // Dodaje logo na vrh, centrirano
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Proširuje komponentu preko dva stupca kako bi bila centrirana
        panel.add(logoLabel, gbc);

        // Labela i polje za unos korisničkog imena
        gbc.gridwidth = 1; // Vraća širinu na jednu kolonu
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Poravnava labelu lijevo
        panel.add(new JLabel("Username: "), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Labela i polje za unos lozinke
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password: "), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Checkbox za prikazivanje ili skrivanje lozinke
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(showPasswordCheckBox = new JCheckBox("Show Password"), gbc);

        // Checkbox za prihvaćanje uvjeta korištenja
        gbc.gridx = 1;
        gbc.gridy = 4;
        termsCheckBox = new JCheckBox("I accept the Terms of Service");
        panel.add(termsCheckBox, gbc);

        // Gumb za registraciju
        gbc.gridx = 1;
        gbc.gridy = 5;
        registerButton = new JButton("Register");
        panel.add(registerButton, gbc);

        // Gumb za povratak
        gbc.gridx = 1;
        gbc.gridy = 6;
        backButton = new JButton("Back");
        panel.add(backButton, gbc);

        setContentPane(panel); // Postavlja panel kao glavni sadržaj prozora

        // Centriranje prozora na ekranu
        setLocationRelativeTo(null);

        // Action listeneri za gumbove i checkbox za prikaz lozinke
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Prikazuje lozinku
            } else {
                passwordField.setEchoChar('*'); // Sakriva lozinku
            }
        });

        // Gumb za registraciju korisnika
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Provjera je li korisničko ime, lozinka uneseno i jesu li uvjeti korištenja prihvaćeni
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(RegistrationScreen.this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!termsCheckBox.isSelected()) {
                JOptionPane.showMessageDialog(RegistrationScreen.this, "You must accept the Terms of Service to register.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (loginManager.userExists(username)) {
                JOptionPane.showMessageDialog(RegistrationScreen.this, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                loginManager.addNewUser(username, password);
                JOptionPane.showMessageDialog(RegistrationScreen.this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Zatvara prozor za registraciju
                new LoginScreen(); // Preusmjerava na ekran za prijavu nakon uspješne registracije
            }
        });

        // Gumb za povratak na početni ekran
        backButton.addActionListener(e -> {
            dispose(); // Zatvara trenutni prozor
            new WelcomeScreen(); // Vraća korisnika na ekran dobrodošlice
        });

        setVisible(true); // Prikazuje prozor
    }
}
