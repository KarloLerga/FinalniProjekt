import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Component> components;
    private double totalPrice;
    private String dateTime; // Store the date and time when the order was placed

    // DateTimeFormatter to format the date and time
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Order(List<Component> components, double totalPrice) {
        this.components = new ArrayList<>(components);
        this.totalPrice = totalPrice;
        this.dateTime = LocalDateTime.now().format(formatter); // Capture the current date and time
    }

    public List<Component> getComponents() {
        return components;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Date & Time: ").append(dateTime).append("\n");
        for (Component component : components) {
            sb.append(component.toString()).append("\n");
        }
        sb.append("Total Price: $").append(totalPrice).append("\n");
        return sb.toString();
    }
}
