import java.io.*;
import java.util.*;

public class LoginManager {
    private Map<String, String> userCredentials = new HashMap<>();
    private Map<String, List<Order>> userOrders = new HashMap<>();

    public LoginManager() {
        loadUserCredentials();
        loadUserOrders();
    }

    private void loadUserCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userCredentials.put(parts[0], parts[1]); // username, password
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loginUser(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public void addOrder(String username, Order order) {
        userOrders.computeIfAbsent(username, k -> new ArrayList<>()).add(order);
        saveUserOrders();
    }

    public List<Order> getUserOrders(String username) {
        return userOrders.getOrDefault(username, new ArrayList<>());
    }

    private void loadUserOrders() {
        File file = new File("src/orders.txt");
        if (file.exists() && file.length() != 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                userOrders = (Map<String, List<Order>>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveUserOrders() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/orders.txt"))) {
            oos.writeObject(userOrders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
