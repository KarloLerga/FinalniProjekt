package model;

import java.io.Serializable;

/**
 * Klasa Component predstavlja komponentu koja sadrži naziv i cijenu.
 * Implementira Serializable sučelje kako bi omogućila serijalizaciju objekata.
 */
public class Component implements Serializable {

    // Serijski identifikator verzije klase, koristi se za provjeru kompatibilnosti prilikom serijalizacije.
    private static final long serialVersionUID = 1L;

    // Privatni atributi klase: ime komponente i njezina cijena.
    private String name;
    private double price;

    /**
     * Konstruktor koji inicijalizira objekt Component s nazivom i cijenom.
     *
     * @param name Naziv komponente.
     * @param price Cijena komponente.
     */
    public Component(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Metoda koja vraća naziv komponente.
     *
     * @return Naziv komponente.
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda koja vraća cijenu komponente.
     *
     * @return Cijena komponente.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Metoda koja vraća tekstualni prikaz objekta Component.
     * Formatira naziv i cijenu u obliku: "naziv - $cijena".
     *
     * @return String koji predstavlja komponentu.
     */
    @Override
    public String toString() {
        return String.format("%s - $%.2f", name, price);
    }
}
