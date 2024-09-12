import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private LoginManager loginManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox; // Checkbox to toggle password visibility
    private JButton loginButton, backButton; // Back button to go to WelcomeScreen
    private LoginListener loginListener;

    public LoginScreen() {
        loginManager = new LoginManager();

        setTitle("PC Builder - Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username field
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Username: "), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Password: "), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        // Show/Hide Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(showPasswordCheckBox, gbc);

        // Login button
        loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPanel.add(loginButton, gbc);

        // Back button
        backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 4;
        loginPanel.add(backButton, gbc);

        setContentPane(loginPanel);

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

        // Login button action listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (loginManager.loginUser(username, password)) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    if (loginListener != null) {
                        loginListener.loginSuccessful();
                    }
                    dispose(); // Close login window

                    // Open the MainFrame after successful login
                    new MainFrame();
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Back button action listener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close current login window
                new WelcomeScreen(); // Go back to the welcome screen
            }
        });

        setVisible(true);
    }

    // Set the login listener to notify when the login is successful
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }
}
