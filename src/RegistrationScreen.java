import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox; // Checkbox to toggle password visibility
    private JButton registerButton, backButton; // Back button to return to WelcomeScreen
    private LoginManager loginManager;

    public RegistrationScreen() {
        loginManager = new LoginManager(); // Load existing users

        setTitle("PC Builder - Register");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username: "), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password: "), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Show/Hide Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(showPasswordCheckBox, gbc);

        // Register button
        registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(registerButton, gbc);

        // Back button
        backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(backButton, gbc);

        setContentPane(panel);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Add action listener for the showPasswordCheckBox
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('*'); // Hide password
                }
            }
        });

        // Register button action listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        // Back button action listener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close registration window
                new WelcomeScreen(); // Go back to welcome screen
            }
        });

        setVisible(true);
    }
}
