package app;

import ui.WelcomeScreen;

/**
 * Klasa PCBuilderApp predstavlja početnu točku aplikacije PC Builder.
 * Metoda main pokreće aplikaciju prikazom Welcome ekrana ili Login ekrana.
 */
public class PCBuilderApp {
    public static void main(String[] args) {
        // Pokreće aplikaciju prikazivanjem Welcome ekrana ili Login ekrana
        new WelcomeScreen(); // Ili može biti: new LoginScreen(); ovisno o logici aplikacije
    }
}
