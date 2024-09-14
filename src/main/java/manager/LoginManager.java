package manager;

import model.Order;

import java.io.*;
import java.util.*;

/**
 * Klasa LoginManager upravlja korisničkim vjerodajnicama i narudžbama.
 * Sadrži metode za autentifikaciju korisnika, upravljanje narudžbama i čitanje/spremanje podataka u datoteke.
 */
public class LoginManager {
    // Mapa za pohranu korisničkih vjerodajnica (korisničko ime i lozinka)
    private Map<String, String> userCredentials = new HashMap<>();

    // Mapa koja pohranjuje narudžbe po korisniku (ključ je korisničko ime, vrijednost je lista narudžbi)
    private Map<String, List<Order>> userOrders = new HashMap<>();

    /**
     * Konstruktor koji učitava korisničke vjerodajnice i narudžbe iz datoteka prilikom inicijalizacije objekta.
     */
    public LoginManager() {
        loadUserCredentials(); // Učitava vjerodajnice korisnika iz datoteke
        loadUserOrders(); // Učitava narudžbe korisnika iz datoteke
    }

    /**
     * Dodaje novu narudžbu korisniku i sprema ažurirane narudžbe u datoteku.
     *
     * @param username Korisničko ime.
     * @param order Narudžba koju treba dodati.
     */
    public void addOrder(String username, Order order) {
        // Ako korisnik nema narudžbi, stvara novu listu, inače dodaje narudžbu u postojeću listu
        userOrders.computeIfAbsent(username, k -> new ArrayList<>()).add(order);
        saveUserOrders(); // Sprema ažurirane narudžbe u datoteku
    }

    /**
     * Vraća listu narudžbi za određenog korisnika.
     *
     * @param username Korisničko ime korisnika.
     * @return Lista narudžbi korisnika ili prazna lista ako nema narudžbi.
     */
    public List<Order> getUserOrders(String username) {
        return userOrders.getOrDefault(username, new ArrayList<>()); // Vraća praznu listu ako korisnik nema narudžbi
    }

    /**
     * Uklanja narudžbu iz korisničke liste na temelju indeksa.
     *
     * @param username Korisničko ime korisnika.
     * @param orderIndex Indeks narudžbe koja se uklanja.
     */
    public void removeOrder(String username, int orderIndex) {
        List<Order> orders = userOrders.get(username);
        // Provjerava je li indeks valjan i uklanja narudžbu ako je moguće
        if (orders != null && orderIndex >= 0 && orderIndex < orders.size()) {
            orders.remove(orderIndex);
            saveUserOrders(); // Sprema ažurirane narudžbe nakon uklanjanja
        }
    }

    /**
     * Učitava korisničke vjerodajnice iz tekstualne datoteke.
     */
    private void loadUserCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userCredentials.put(parts[0], parts[1]); // Dodaje korisničko ime i lozinku u mapu
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Ispisuje grešku ako se dogodi problem pri čitanju datoteke
        }
    }

    /**
     * Učitava narudžbe korisnika iz datoteke.
     * Datoteka koristi serijalizaciju objekata kako bi spremila mapu narudžbi.
     */
    private void loadUserOrders() {
        File file = new File("src/main/resources/data/orders.txt");
        if (file.exists() && file.length() != 0) { // Provjerava postoji li datoteka i nije li prazna
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                userOrders = (Map<String, List<Order>>) ois.readObject(); // Učitava serijalizirane narudžbe
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace(); // Ispisuje grešku ako se dogodi problem pri čitanju datoteke
            }
        }
    }

    /**
     * Sprema korisničke narudžbe u datoteku koristeći serijalizaciju objekata.
     */
    private void saveUserOrders() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/data/orders.txt"))) {
            oos.writeObject(userOrders); // Serijalizira i sprema mapu narudžbi u datoteku
        } catch (IOException e) {
            e.printStackTrace(); // Ispisuje grešku ako se dogodi problem pri pisanju u datoteku
        }
    }

    /**
     * Provjerava prijavu korisnika na temelju korisničkog imena i lozinke.
     *
     * @param username Korisničko ime.
     * @param password Lozinka.
     * @return true ako su vjerodajnice ispravne, inače false.
     */
    public boolean loginUser(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password); // Provjerava odgovaraju li lozinka i korisničko ime
    }

    /**
     * Provjerava postoji li korisnik u sustavu.
     *
     * @param username Korisničko ime.
     * @return true ako korisnik postoji, inače false.
     */
    public boolean userExists(String username) {
        return userCredentials.containsKey(username); // Provjerava postoji li korisničko ime u mapi vjerodajnica
    }

    /**
     * Dodaje novog korisnika i njegovu lozinku u datoteku i mapu u memoriji.
     *
     * @param username Korisničko ime.
     * @param password Lozinka.
     */
    public void addNewUser(String username, String password) {
        try (FileWriter writer = new FileWriter("src/main/resources/data/users.txt", true)) {
            writer.write(username + "," + password + "\n"); // Dodaje korisničko ime i lozinku u datoteku
        } catch (IOException e) {
            e.printStackTrace(); // Ispisuje grešku ako se dogodi problem pri pisanju u datoteku
        }
        userCredentials.put(username, password); // Ažurira mapu vjerodajnica u memoriji
    }
}
