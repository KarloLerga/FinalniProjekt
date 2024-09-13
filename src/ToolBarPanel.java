import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarPanel extends JPanel {
    private JButton viewOrdersButton, logoutButton;
    private ToolBarListener toolBarListener;

    public ToolBarPanel() {
        viewOrdersButton = new JButton("View Orders");
        logoutButton = new JButton("Logout"); // Add Logout button

        // View Orders action listener
        viewOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toolBarListener != null) {
                    toolBarListener.toolBarEventOccurred(new ToolBarEvent(false, true));
                }
            }
        });

        // Logout action listener
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toolBarListener != null) {
                    toolBarListener.toolBarEventOccurred(new ToolBarEvent(true, false)); // Trigger logout event
                }
            }
        });

        // Add buttons to the toolbar
        add(viewOrdersButton);
        add(logoutButton);
    }

    public void setToolBarListener(ToolBarListener listener) {
        this.toolBarListener = listener;
    }
}
