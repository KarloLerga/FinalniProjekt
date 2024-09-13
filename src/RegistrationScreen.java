import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class RegistrationScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton registerButton, backButton;
    private LoginManager loginManager;
    private JLabel logoLabel;

    public RegistrationScreen() {
        loginManager = new LoginManager();

        setTitle("PC Builder - Register");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER; // Align components centrally

        // Load the registration logo from the resources folder and resize it
        URL registerLogoUrl = getClass().getClassLoader().getResource("images/register.png");
        if (registerLogoUrl != null) {
            ImageIcon registerLogoIcon = new ImageIcon(registerLogoUrl);
            Image scaledRegisterLogo = registerLogoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resizing to 100x100 pixels
            registerLogoIcon = new ImageIcon(scaledRegisterLogo);
            logoLabel = new JLabel(registerLogoIcon);
        } else {
            logoLabel = new JLabel("Register"); // Fallback text if the image is not found
        }

        // Add the logo at the top, centrally aligned
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across both columns to center it
        panel.add(logoLabel, gbc);

        // Username label and field
        gbc.gridwidth = 1; // Reset grid width for the fields
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align labels and fields to the left
        panel.add(new JLabel("Username: "), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password: "), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Show/Hide Password Checkbox
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(showPasswordCheckBox = new JCheckBox("Show Password"), gbc);

        // Register button
        gbc.gridx = 1;
        gbc.gridy = 4;
        registerButton = new JButton("Register");
        panel.add(registerButton, gbc);

        // Back button
        gbc.gridx = 1;
        gbc.gridy = 5;
        backButton = new JButton("Back");
        panel.add(backButton, gbc);

        setContentPane(panel);

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

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(RegistrationScreen.this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (loginManager.userExists(username)) {
                JOptionPane.showMessageDialog(RegistrationScreen.this, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                loginManager.addNewUser(username, password);
                JOptionPane.showMessageDialog(RegistrationScreen.this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoginScreen(); // Redirect to login after successful registration
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new WelcomeScreen(); // Go back to the welcome screen
        });

        setVisible(true);
    }
}
