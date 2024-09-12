import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {
    private JButton loginButton, registerButton;

    public WelcomeScreen() {
        setTitle("PC Builder - Welcome");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Login button
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(loginButton, gbc);

        // Register button
        registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 0;
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
