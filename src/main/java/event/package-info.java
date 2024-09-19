/**
 * Ovaj paket sadrži klase i sučelja vezana uz događaje unutar aplikacije PC Builder.
 * Paket pruža podršku za različite vrste događaja koji se javljaju tijekom interakcije korisnika s grafičkim korisničkim sučeljem,
 * kao i metode za rukovanje tim događajima.
 *
 * Funkcionalnosti uključuju:
 * <ul>
 *   <li>Obradu događaja vezanih uz odabir komponenti putem forme (FormPanel)</li>
 *   <li>Obradu događaja za prijavu korisnika i provjeru vjerodajnica</li>
 *   <li>Obradu događaja alatne trake, uključujući odjavu i pregled narudžbi</li>
 * </ul>
 *
 * Glavne klase i sučelja u ovom paketu su:
 * <ul>
 *   <li>{@link event.FormEvent} - Klasa koja predstavlja događaj vezan uz odabir komponenti putem forme.</li>
 *   <li>{@link event.FormPanelListener} - Sučelje za rukovanje događajima na FormPanelu.</li>
 *   <li>{@link event.LoginListener} - Sučelje za obradu događaja prijave korisnika.</li>
 *   <li>{@link event.ToolBarEvent} - Klasa koja predstavlja događaj vezan uz alatnu traku, uključujući odjavu i pregled narudžbi.</li>
 *   <li>{@link event.ToolBarListener} - Sučelje za rukovanje događajima alatne trake.</li>
 * </ul>
 *
 * Ovaj paket omogućuje modularan pristup upravljanju događajima unutar aplikacije, omogućujući korisnicima interakciju s različitim elementima sučelja.
 *
 * @since 1.0
 */
package event;
