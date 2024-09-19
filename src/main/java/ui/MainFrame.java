package ui;

import event.FormEvent;
import event.FormPanelListener;
import event.ToolBarEvent;
import event.ToolBarListener;
import manager.LoginManager;
import manager.OrderManager;
import model.Order;
import model.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Klasa MainFrame predstavlja glavni okvir aplikacije PC Builder.
 * Ovaj okvir prikazuje formu za odabir komponenti, prikaz košarice i alatnu traku.
 * Također upravlja događajima vezanim za dodavanje u košaricu, izračun FPS-a, pregled narudžbi i odjavu.
 */
public class MainFrame extends JFrame {
    private FormPanel formPanel;
    private ViewPanel viewPanel;
    private ToolBarPanel toolBarPanel;
    private OrderManager orderManager = new OrderManager();
    private LoginManager loginManager;
    private String loggedInUsername; // Pohranjuje korisničko ime prijavljenog korisnika

    /**
     * Konstruktor MainFrame koji prima korisničko ime prijavljenog korisnika.
     *
     * @param username Korisničko ime prijavljenog korisnika.
     */
    public MainFrame(String username) {
        super("PC Builder App");

        this.loggedInUsername = username;
        this.loginManager = new LoginManager();

        setLayout(new BorderLayout());

        formPanel = new FormPanel();
        viewPanel = new ViewPanel();
        toolBarPanel = new ToolBarPanel();

        // Postavljanje listenera za događaje na formi
        formPanel.setFormPanelListener(new FormPanelListener() {
            @Override
            public void formEventOccurred(FormEvent event, boolean isAddToCart) {
                String compatibilityMessage = orderManager.checkCompatibility(event.getSelectedComponents());

                // Provjera kompatibilnosti komponenti
                if (compatibilityMessage.equals("compatible")) {
                    if (isAddToCart) {
                        handleAddToCartEvent(event); // Dodavanje u košaricu
                    } else {
                        handleCalculateFPSEvent(event); // Izračun FPS-a
                    }
                } else {
                    // Prikaz poruke o grešci ako su komponente nekompatibilne
                    JOptionPane.showMessageDialog(MainFrame.this, compatibilityMessage, "Compatibility Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Postavljanje listenera za događaje na alatnoj traci
        toolBarPanel.setToolBarListener(new ToolBarListener() {
            @Override
            public void toolBarEventOccurred(ToolBarEvent event) {
                if (event.isLogoutEvent()) {
                    handleLogoutEvent(); // Rukovanje odjavom
                } else if (event.isViewOrdersEvent()) {
                    handleViewOrdersEvent(); // Pregled narudžbi za trenutnog korisnika
                }
            }
        });

        // Dodavanje panela u glavni okvir
        add(formPanel, BorderLayout.WEST);
        add(viewPanel, BorderLayout.CENTER);
        add(toolBarPanel, BorderLayout.NORTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centriranje prozora na ekranu
        setLocationRelativeTo(null);

        setVisible(true);
    }

    /**
     * Metoda za rukovanje događajem dodavanja u košaricu.
     *
     * @param event Objekt FormEvent koji sadrži odabrane komponente.
     */
    private void handleAddToCartEvent(FormEvent event) {
        // Čisti trenutnu košaricu kako bi se stvorila nova narudžba
        orderManager.clearCart();

        // Dohvaća odabrane komponente iz događaja
        Component motherboardComponent = event.getComponent(event.getSelectedComponents().get("motherboard"));
        Component processorComponent = event.getComponent(event.getSelectedComponents().get("processor"));
        Component ramComponent = event.getComponent(event.getSelectedComponents().get("ram"));
        Component storageComponent = event.getComponent(event.getSelectedComponents().get("storage"));
        Component psuComponent = event.getComponent(event.getSelectedComponents().get("psu"));
        Component graphicsCardComponent = event.getComponent(event.getSelectedComponents().get("graphicsCard"));

        // Dodaje komponente u košaricu putem OrderManagera
        orderManager.addComponentToCart(motherboardComponent);
        orderManager.addComponentToCart(processorComponent);
        orderManager.addComponentToCart(ramComponent);
        orderManager.addComponentToCart(storageComponent);
        orderManager.addComponentToCart(psuComponent);
        orderManager.addComponentToCart(graphicsCardComponent);

        // Stvara novu narudžbu i dodaje je narudžbama korisnika
        Order newOrder = new Order(orderManager.getCart(), orderManager.getTotalPrice());
        loginManager.addOrder(loggedInUsername, newOrder); // Sprema novu narudžbu za prijavljenog korisnika

        // Ažurira prikaz košarice i ukupnu cijenu u ViewPanelu
        viewPanel.updateCart(orderManager.getCart(), orderManager.getTotalPrice());
    }

    /**
     * Metoda za rukovanje događajem izračuna FPS-a.
     *
     * @param event Objekt FormEvent koji sadrži odabrane komponente.
     */
    private void handleCalculateFPSEvent(FormEvent event) {
        // Dohvaćanje odabrane rezolucije
        String resolution = "1080p";
        if (formPanel.getResolution1440p().isSelected()) {
            resolution = "1440p";
        } else if (formPanel.getResolution2160p().isSelected()) {
            resolution = "2160p";
        }

        // Dohvaća odabrane komponente za izračun FPS-a
        String processor = event.getSelectedComponents().get("processor");
        String graphicsCard = event.getSelectedComponents().get("graphicsCard");
        String ram = event.getSelectedComponents().get("ram");
        String game = event.getSelectedComponents().get("game");

        // Izračunava FPS na temelju odabranih komponenti i rezolucije
        double estimatedFPS = orderManager.calculateFPS(processor, graphicsCard, ram, game, resolution);

        // Ažurira prikaz FPS-a u ViewPanelu
        viewPanel.displayFPS(estimatedFPS);
    }

    /**
     * Metoda za rukovanje događajem odjave.
     */
    private void handleLogoutEvent() {
        dispose(); // Zatvara glavni prozor
        new WelcomeScreen(); // Vraća korisnika na ekran dobrodošlice
    }

    /**
     * Metoda za rukovanje događajem pregleda narudžbi.
     */
    private void handleViewOrdersEvent() {
        // Dohvaća narudžbe za prijavljenog korisnika
        List<Order> orders = loginManager.getUserOrders(loggedInUsername);
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders found for user: " + loggedInUsername);
        } else {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel userLabel = new JLabel("Orders for User: " + loggedInUsername);
            panel.add(userLabel);

            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> orderList = new JList<>(listModel);
            orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(orderList);
            scrollPane.setPreferredSize(new Dimension(500, 300));

            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                String orderHeader = "Order " + (i + 1) + " - Total: $" + String.format("%.2f", order.getTotalPrice());
                listModel.addElement(orderHeader);
            }

            JTextArea orderDetails = new JTextArea();
            orderDetails.setEditable(false);
            orderDetails.setVisible(false);

            orderList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && orderList.getSelectedIndex() != -1) {
                    int selectedIndex = orderList.getSelectedIndex();
                    Order selectedOrder = orders.get(selectedIndex);
                    orderDetails.setText(selectedOrder.toString());
                    orderDetails.setVisible(true);
                }
            });

            panel.add(scrollPane);
            panel.add(orderDetails);

            Object[] options = {"Cancel Order", "Close"};
            int result = JOptionPane.showOptionDialog(
                    this,
                    panel,
                    "View Orders",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            if (result == JOptionPane.YES_OPTION && orderList.getSelectedIndex() != -1) {
                int selectedIndex = orderList.getSelectedIndex();
                int confirmResult = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel Order " + (selectedIndex + 1) + "?",
                        "Confirm Cancel",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmResult == JOptionPane.YES_OPTION) {
                    loginManager.removeOrder(loggedInUsername, selectedIndex);
                    JOptionPane.showMessageDialog(this, "Order canceled successfully.");
                    listModel.remove(selectedIndex);
                }
            }
        }
    }
}
