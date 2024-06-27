import java.io.Serializable;

public class Component implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    double price;

    public Component(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}
