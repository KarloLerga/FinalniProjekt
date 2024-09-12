import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewPanel extends JPanel {
    private JTextArea cartArea;
    private JLabel totalPriceLabel;
    private JLabel fpsLabel;

    public ViewPanel() {
        setLayout(new BorderLayout());

        cartArea = new JTextArea(10, 50);
        cartArea.setEditable(false);
        JScrollPane cartScrollPane = new JScrollPane(cartArea);

        totalPriceLabel = new JLabel("Total Price: $0.0");
        fpsLabel = new JLabel("Estimated FPS: N/A");

        add(cartScrollPane, BorderLayout.CENTER);
        add(totalPriceLabel, BorderLayout.SOUTH);
        add(fpsLabel, BorderLayout.NORTH);
    }

    public void updateCart(List<Component> cart, double totalPrice) {
        cartArea.setText("");
        for (Component component : cart) {
            cartArea.append(component.toString() + "\n");
        }
        totalPriceLabel.setText("Total Price: $" + totalPrice);
    }

    public void displayFPS(double fps) {
        fpsLabel.setText("Estimated FPS: " + Math.round(fps) + " FPS");
    }
}
