package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Order predstavlja narudžbu i implementira Serializable kako bi omogućila serijalizaciju objekta.
 * Sadrži listu komponenti, ukupnu cijenu i vrijeme kada je narudžba izvršena.
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L; // Serijski identifikator klase za serijalizaciju
    private List<Component> components; // Lista komponenti u narudžbi
    private double totalPrice; // Ukupna cijena narudžbe
    private String dateTime; // Pohranjuje datum i vrijeme kada je narudžba izvršena

    // DateTimeFormatter za formatiranje datuma i vremena
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Konstruktor Order kreira novu narudžbu s danim komponentama i ukupnom cijenom.
     * Također bilježi trenutni datum i vrijeme narudžbe.
     *
     * @param components Lista komponenti koje su dio narudžbe.
     * @param totalPrice Ukupna cijena narudžbe.
     */
    public Order(List<Component> components, double totalPrice) {
        this.components = new ArrayList<>(components); // Stvara kopiju liste komponenti
        this.totalPrice = totalPrice;
        this.dateTime = LocalDateTime.now().format(formatter); // Bilježi trenutni datum i vrijeme
    }

    /**
     * Vraća listu komponenti narudžbe.
     *
     * @return Lista komponenti.
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Vraća ukupnu cijenu narudžbe.
     *
     * @return Ukupna cijena narudžbe.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Vraća datum i vrijeme kada je narudžba izvršena.
     *
     * @return Datum i vrijeme narudžbe u obliku stringa.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Metoda toString vraća tekstualni prikaz narudžbe, uključujući datum, komponente i ukupnu cijenu.
     *
     * @return Tekstualni prikaz narudžbe.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Datum i vrijeme narudžbe: ").append(dateTime).append("\n"); // Dodaje datum i vrijeme
        for (Component component : components) {
            sb.append(component.toString()).append("\n"); // Dodaje informacije o svakoj komponenti
        }
        sb.append("Ukupna cijena: $").append(totalPrice).append("\n"); // Dodaje ukupnu cijenu
        return sb.toString();
    }
}
