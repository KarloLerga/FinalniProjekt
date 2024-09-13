import java.io.*;
import java.util.*;

public class LoginManager {
    private Map<String, String> userCredentials = new HashMap<>();
    private Map<String, List<Order>> userOrders = new HashMap<>(); // Stores orders per user

    public LoginManager() {
        loadUserCredentials();
        loadUserOrders(); // Load user orders from the file
    }

    // Add an order to the user's list and save it
    public void addOrder(String username, Order order) {
        userOrders.computeIfAbsent(username, k -> new ArrayList<>()).add(order);
        saveUserOrders(); // Save updated orders to the file
    }

    // Get the orders for a specific user
    public List<Order> getUserOrders(String username) {
        return userOrders.getOrDefault(username, new ArrayList<>());
    }

    // Remove a specific order by index
    public void removeOrder(String username, int orderIndex) {
        List<Order> orders = userOrders.get(username);
        if (orders != null && orderIndex >= 0 && orderIndex < orders.size()) {
            orders.remove(orderIndex);
            saveUserOrders(); // Save updated orders after removal
        }
    }

    // Load user credentials from the file
    private void loadUserCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userCredentials.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load user orders from the file
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

    // Save user orders to the file
    private void saveUserOrders() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/orders.txt"))) {
            oos.writeObject(userOrders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loginUser(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public boolean userExists(String username) {
        return userCredentials.containsKey(username);
    }

    public void addNewUser(String username, String password) {
        try (FileWriter writer = new FileWriter("src/users.txt", true)) {
            writer.write(username + "," + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        userCredentials.put(username, password); // Update in-memory map
    }
}
