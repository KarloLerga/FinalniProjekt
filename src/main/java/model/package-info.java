/**
 * Ovaj paket sadrži modele koji predstavljaju osnovne entitete u aplikaciji PC Builder.
 * Uključuje klase koje modeliraju pojedinačne komponente računala i narudžbe korisnika.
 *
 * Funkcionalnosti paketa uključuju:
 * <ul>
 *   <li>Modeliranje komponenti koje se koriste u izgradnji računala (npr. procesor, matična ploča, grafička kartica)</li>
 *   <li>Modeliranje narudžbi koje sadrže odabrane komponente, ukupnu cijenu i vrijeme narudžbe</li>
 *   <li>Podršku za serijalizaciju podataka radi spremanja i učitavanja narudžbi</li>
 * </ul>
 *
 * Glavne klase u ovom paketu su:
 * <ul>
 *   <li>{@link model.Component} - Predstavlja pojedinačnu komponentu računala s nazivom i cijenom.</li>
 *   <li>{@link model.Order} - Predstavlja narudžbu koja sadrži listu komponenti, ukupnu cijenu i vrijeme narudžbe.</li>
 * </ul>
 *
 * Klasa `Component` modelira pojedinačne komponente računala, poput procesora ili grafičke kartice, te sadrži informacije o njihovim nazivima i cijenama.
 * Klasa `Order` upravlja narudžbama, prateći odabrane komponente, ukupnu cijenu i vrijeme kada je narudžba kreirana.
 *
 * @since 1.0
 */
package model;
