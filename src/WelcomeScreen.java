import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.net.URL;

public class WelcomeScreen extends JFrame {
    private JButton loginButton, registerButton;
    private JLabel logoLabel;

    public WelcomeScreen() {
        setTitle("PC Builder - Welcome");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Load the logo image from the resources folder
        URL logoUrl = getClass().getClassLoader().getResource("images/PCBuilder.png");
        if (logoUrl != null) {
            ImageIcon logoIcon = new ImageIcon(logoUrl);

            // Scale the logo to a smaller size (e.g., 100x100 pixels)
            Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(scaledLogo);  // Assign the scaled image back to the ImageIcon
            logoLabel = new JLabel(logoIcon);
        } else {
            logoLabel = new JLabel("PC Builder Logo"); // Fallback if the image is not found
        }

        // Add the logo to the panel at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(logoLabel, gbc);

        // Login button
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(loginButton, gbc);

        // Register button
        registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(registerButton, gbc);

        // Set the panel as the content pane
        setContentPane(panel);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Button action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginScreen(); // Open Login screen
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistrationScreen(); // Open Registration screen
            }
        });

        setVisible(true);
    }
}
