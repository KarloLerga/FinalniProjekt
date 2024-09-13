import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private FormPanel formPanel;
    private ViewPanel viewPanel;
    private ToolBarPanel toolBarPanel;
    private OrderManager orderManager = new OrderManager();
    private LoginManager loginManager;
    private String loggedInUsername; // Store the username of the logged-in user

    public MainFrame(String username) { // Pass the logged-in username
        super("PC Builder App");

        this.loggedInUsername = username;
        this.loginManager = new LoginManager();

        setLayout(new BorderLayout());

        formPanel = new FormPanel();
        viewPanel = new ViewPanel();
        toolBarPanel = new ToolBarPanel();

        formPanel.setFormPanelListener(new FormPanelListener() {
            @Override
            public void formEventOccurred(FormEvent event, boolean isAddToCart) {
                String compatibilityMessage = orderManager.checkCompatibility(event.getSelectedComponents());

                if (compatibilityMessage.equals("compatible")) {
                    if (isAddToCart) {
                        handleAddToCartEvent(event);
                    } else {
                        handleCalculateFPSEvent(event);
                    }
                } else {
                    // Show detailed error message for incompatible components
                    JOptionPane.showMessageDialog(MainFrame.this, compatibilityMessage, "Compatibility Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        toolBarPanel.setToolBarListener(new ToolBarListener() {
            @Override
            public void toolBarEventOccurred(ToolBarEvent event) {
                if (event.isLogoutEvent()) {
                    handleLogoutEvent(); // Handle logout
                } else if (event.isViewOrdersEvent()) {
                    handleViewOrdersEvent(); // View orders for the current user
                }
            }
        });

        add(formPanel, BorderLayout.WEST);
        add(viewPanel, BorderLayout.CENTER);
        add(toolBarPanel, BorderLayout.NORTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void handleAddToCartEvent(FormEvent event) {
        // Clear the current cart to create a new order
        orderManager.clearCart();

        // Get the selected components from the event
        Component motherboardComponent = event.getComponent(event.getSelectedComponents().get("motherboard"));
        Component processorComponent = event.getComponent(event.getSelectedComponents().get("processor"));
        Component ramComponent = event.getComponent(event.getSelectedComponents().get("ram"));
        Component storageComponent = event.getComponent(event.getSelectedComponents().get("storage"));
        Component psuComponent = event.getComponent(event.getSelectedComponents().get("psu"));
        Component graphicsCardComponent = event.getComponent(event.getSelectedComponents().get("graphicsCard"));

        // Add the selected components to the cart in the OrderManager
        orderManager.addComponentToCart(motherboardComponent);
        orderManager.addComponentToCart(processorComponent);
        orderManager.addComponentToCart(ramComponent);
        orderManager.addComponentToCart(storageComponent);
        orderManager.addComponentToCart(psuComponent);
        orderManager.addComponentToCart(graphicsCardComponent);

        // Create a new order and add it to the user's orders
        Order newOrder = new Order(orderManager.getCart(), orderManager.getTotalPrice());
        loginManager.addOrder(loggedInUsername, newOrder); // Save the new order for the logged-in user

        // Update the cart and total price in the view panel
        viewPanel.updateCart(orderManager.getCart(), orderManager.getTotalPrice());
    }

    private void handleCalculateFPSEvent(FormEvent event) {
        // Calculate FPS based on the selected components
        String processor = event.getSelectedComponents().get("processor");
        String graphicsCard = event.getSelectedComponents().get("graphicsCard");
        String ram = event.getSelectedComponents().get("ram");
        String game = event.getSelectedComponents().get("game");

        // Calculate FPS
        double estimatedFPS = orderManager.calculateFPS(processor, graphicsCard, ram, game);

        // Update the FPS display
        viewPanel.displayFPS(estimatedFPS);
    }

    private void handleLogoutEvent() {
        dispose(); // Close the MainFrame
        new WelcomeScreen(); // Go back to the WelcomeScreen after logout
    }

    private void handleViewOrdersEvent() {
        // Get the orders for the logged-in user
        List<Order> orders = loginManager.getUserOrders(loggedInUsername);
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders found for user: " + loggedInUsername);
        } else {
            // Create a JPanel to display the orders with expandable/collapsible details
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel userLabel = new JLabel("Orders for User: " + loggedInUsername);
            panel.add(userLabel);

            // Create a DefaultListModel to store order headers
            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> orderList = new JList<>(listModel);
            orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(orderList);
            scrollPane.setPreferredSize(new Dimension(500, 300));

            // Add orders to the list with only the total price (without date)
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                String orderHeader = "Order " + (i + 1) + " - Total: $" + String.format("%.2f", order.getTotalPrice());
                listModel.addElement(orderHeader);
            }

            // Create a JTextArea to display detailed order info (components and prices)
            JTextArea orderDetails = new JTextArea();
            orderDetails.setEditable(false);
            orderDetails.setVisible(false); // Initially hidden

            // Add a listener to expand and show order details when an order is clicked
            orderList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && orderList.getSelectedIndex() != -1) {
                    int selectedIndex = orderList.getSelectedIndex();
                    Order selectedOrder = orders.get(selectedIndex);
                    orderDetails.setText(selectedOrder.toString());
                    orderDetails.setVisible(true);
                }
            });

            // Add the JTextArea for order details to the panel
            panel.add(scrollPane);
            panel.add(orderDetails);

            // Create buttons for dialog
            Object[] options = {"Cancel Order", "Close"};
            int result = JOptionPane.showOptionDialog(
                this,
                panel,
                "View Orders",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]  // Set the default focus on the "Close" button
            );

            // If the user presses "Cancel Order", give an option to cancel the selected order
            if (result == JOptionPane.YES_OPTION && orderList.getSelectedIndex() != -1) {
                int selectedIndex = orderList.getSelectedIndex();
                // Confirm the cancellation
                int confirmResult = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to cancel Order " + (selectedIndex + 1) + "?",
                    "Confirm Cancel",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirmResult == JOptionPane.YES_OPTION) {
                    loginManager.removeOrder(loggedInUsername, selectedIndex); // Remove the order
                    JOptionPane.showMessageDialog(this, "Order canceled successfully.");
                    listModel.remove(selectedIndex); // Update the list model
                }
            }
        }
    }
}
