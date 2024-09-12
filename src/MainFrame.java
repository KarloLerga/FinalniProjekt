import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private FormPanel formPanel;
    private ViewPanel viewPanel;
    private ToolBarPanel toolBarPanel;
    private OrderManager orderManager = new OrderManager();
    private LoginManager loginManager = new LoginManager();

    public MainFrame() {
        super("PC Builder App");

        setLayout(new BorderLayout());

        formPanel = new FormPanel();
        viewPanel = new ViewPanel();
        toolBarPanel = new ToolBarPanel();

        formPanel.setFormPanelListener(new FormPanelListener() {
            @Override
            public void formEventOccurred(FormEvent event, boolean isAddToCart) {
                if (isAddToCart) {
                    handleAddToCartEvent(event);
                } else {
                    handleCalculateFPSEvent(event);
                }
            }
        });

        toolBarPanel.setToolBarListener(new ToolBarListener() {
            @Override
            public void toolBarEventOccurred(ToolBarEvent event) {
                handleToolBarEvent(event);
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
        // Add components to cart
        Component motherboardComponent = event.getComponent(event.getSelectedComponents().get("motherboard"));
        Component processorComponent = event.getComponent(event.getSelectedComponents().get("processor"));
        Component ramComponent = event.getComponent(event.getSelectedComponents().get("ram"));
        Component storageComponent = event.getComponent(event.getSelectedComponents().get("storage"));
        Component psuComponent = event.getComponent(event.getSelectedComponents().get("psu"));
        Component graphicsCardComponent = event.getComponent(event.getSelectedComponents().get("graphicsCard"));

        // Add components to the order manager's cart
        orderManager.addComponentToCart(motherboardComponent);
        orderManager.addComponentToCart(processorComponent);
        orderManager.addComponentToCart(ramComponent);
        orderManager.addComponentToCart(storageComponent);
        orderManager.addComponentToCart(psuComponent);
        orderManager.addComponentToCart(graphicsCardComponent);

        // Update the cart and total price in the view
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

    private void handleToolBarEvent(ToolBarEvent event) {
        if (event.isViewOrdersEvent()) {
            List<Order> orders = loginManager.getUserOrders("testUser"); // Placeholder username
            if (((java.util.List<?>) orders).isEmpty()) {
                JOptionPane.showMessageDialog(this, "No orders found.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Order order : orders) {
                    sb.append(order).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString());
            }
        }
    }
}
