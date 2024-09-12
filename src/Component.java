import java.io.Serializable;

public class Component implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double price;

    public Component(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", name, price);
    }
}
