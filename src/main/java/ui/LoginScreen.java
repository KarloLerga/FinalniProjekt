package ui;

import event.LoginListener;
import manager.LoginManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Klasa LoginScreen predstavlja prozor za prijavu korisnika u aplikaciju.
 * Omogućava unos korisničkog imena i lozinke te prikazuje poruke o uspješnoj ili neuspješnoj prijavi.
 */
public class LoginScreen extends JFrame {
    private LoginManager loginManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox; // Checkbox za prikazivanje ili skrivanje lozinke
    private JButton loginButton, backButton; // Gumb za povratak na WelcomeScreen
    private LoginListener loginListener;
    private JLabel logoLabel;

    /**
     * Konstruktor LoginScreen inicijalizira sve elemente za prijavu, uključujući polja za unos i gumbe.
     */
    public LoginScreen() {
        loginManager = new LoginManager(); // Kreira instancu LoginManagera za provjeru prijave

        setTitle("PC Builder - Login"); // Postavlja naslov prozora
        setSize(650, 450); // Postavlja dimenzije prozora
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Postavlja način zatvaranja prozora

        // Kreira panel za prijavu s GridBagLayout rasporedom za precizno pozicioniranje elemenata
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Postavlja margine između komponenti
        gbc.anchor = GridBagConstraints.CENTER; // Poravnava komponente centrirano

        // Učitava logo za prijavu iz resursa i mijenja veličinu slike
        URL loginLogoUrl = getClass().getClassLoader().getResource("images/login.png");
        if (loginLogoUrl != null) {
            ImageIcon loginLogoIcon = new ImageIcon(loginLogoUrl);
            Image scaledLoginLogo = loginLogoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Mijenja veličinu slike na 100x100 piksela
            loginLogoIcon = new ImageIcon(scaledLoginLogo);
            logoLabel = new JLabel(loginLogoIcon); // Prikazuje sliku
        } else {
            logoLabel = new JLabel("Login"); // Rezervni tekst ako slika nije pronađena
        }

        // Dodaje logo na vrh, centrirano
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Proširuje komponentu preko dva stupca kako bi bila centrirana
        loginPanel.add(logoLabel, gbc);

        // Labela i polje za unos korisničkog imena
        gbc.gridwidth = 1; // Vraća širinu na jednu kolonu
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Poravnava labelu ulijevo
        loginPanel.add(new JLabel("Username: "), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        // Labela i polje za unos lozinke
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(new JLabel("Password: "), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        // Checkbox za prikazivanje ili skrivanje lozinke
        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPanel.add(showPasswordCheckBox = new JCheckBox("Pokaži lozinku"), gbc);

        // Gumb za prijavu
        gbc.gridx = 1;
        gbc.gridy = 4;
        loginButton = new JButton("Login");
        loginPanel.add(loginButton, gbc);

        // Gumb za povratak na prethodni ekran (WelcomeScreen)
        gbc.gridx = 1;
        gbc.gridy = 5;
        backButton = new JButton("Back");
        loginPanel.add(backButton, gbc);

        // Postavlja loginPanel kao glavni sadržaj prozora
        setContentPane(loginPanel);

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

        // Gumb za prijavu
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Provjera prijave putem LoginManagera
            if (loginManager.loginUser(username, password)) {
                JOptionPane.showMessageDialog(LoginScreen.this, "Prijava uspješna", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (loginListener != null) {
                    loginListener.loginSuccessful(); // Obavijest o uspješnoj prijavi
                }
                dispose(); // Zatvara prozor za prijavu
                new MainFrame(username); // Otvara glavni prozor s korisničkim imenom
            } else {
                JOptionPane.showMessageDialog(LoginScreen.this, "Prijava nije uspijela", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Gumb za povratak na WelcomeScreen
        backButton.addActionListener(e -> {
            dispose(); // Zatvara trenutni prozor
            new WelcomeScreen(); // Otvara ekran dobrodošlice (WelcomeScreen)
        });

        setVisible(true); // Prikazuje prozor
    }

    /**
     * Postavlja listener za uspješnu prijavu.
     *
     * @param listener LoginListener objekt koji prima obavijesti o uspješnoj prijavi.
     */
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }
}
