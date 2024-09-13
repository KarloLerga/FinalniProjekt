import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class LoginScreen extends JFrame {
    private LoginManager loginManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox; // Checkbox to toggle password visibility
    private JButton loginButton, backButton; // Back button to go to WelcomeScreen
    private LoginListener loginListener;
    private JLabel logoLabel;

    public LoginScreen() {
        loginManager = new LoginManager();

        setTitle("PC Builder - Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER; // Align components centrally

        // Load the login logo from the resources folder and resize it
        URL loginLogoUrl = getClass().getClassLoader().getResource("images/login.png");
        if (loginLogoUrl != null) {
            ImageIcon loginLogoIcon = new ImageIcon(loginLogoUrl);
            Image scaledLoginLogo = loginLogoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resizing to 100x100 pixels
            loginLogoIcon = new ImageIcon(scaledLoginLogo);
            logoLabel = new JLabel(loginLogoIcon);
        } else {
            logoLabel = new JLabel("Login"); // Fallback text if the image is not found
        }

        // Add the logo at the top, centrally aligned
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across both columns to center it
        loginPanel.add(logoLabel, gbc);

        // Username label and field
        gbc.gridwidth = 1; // Reset grid width for the fields
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align labels and fields to the left
        loginPanel.add(new JLabel("Username: "), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(new JLabel("Password: "), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        // Show/Hide Password Checkbox
        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPanel.add(showPasswordCheckBox = new JCheckBox("Show Password"), gbc);

        // Login button
        gbc.gridx = 1;
        gbc.gridy = 4;
        loginButton = new JButton("Login");
        loginPanel.add(loginButton, gbc);

        // Back button
        gbc.gridx = 1;
        gbc.gridy = 5;
        backButton = new JButton("Back");
        loginPanel.add(backButton, gbc);

        setContentPane(loginPanel);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Action listeners for buttons and show/hide password
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('*'); // Hide password
            }
        });

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (loginManager.loginUser(username, password)) {
                JOptionPane.showMessageDialog(LoginScreen.this, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (loginListener != null) {
                    loginListener.loginSuccessful();
                }
                dispose(); // Close login window
                new MainFrame(username); // Pass the username to the main screen
            } else {
                JOptionPane.showMessageDialog(LoginScreen.this, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new WelcomeScreen(); // Go back to the welcome screen
        });

        setVisible(true);
    }

    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }
}
