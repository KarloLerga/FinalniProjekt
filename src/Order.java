import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Component> components;
    private double totalPrice;

    public Order(List<Component> components, double totalPrice) {
        this.components = new ArrayList<>(components);
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order: \n");
        for (Component component : components) {
            sb.append(component.toString()).append("\n");
        }
        sb.append("Total Price: $").append(totalPrice).append("\n");
        return sb.toString();
    }
}
