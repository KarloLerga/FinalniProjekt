/**
 * Ovaj paket sadrži klase za grafičko korisničko sučelje (GUI) aplikacije PC Builder.
 * Paket omogućava korisnicima interakciju s aplikacijom putem vizualnog sučelja koje omogućava odabir komponenti,
 * prijavu, registraciju i pregled narudžbi.
 *
 * Funkcionalnosti uključuju:
 * <ul>
 *   <li>Prikaz početnog ekrana s opcijama za prijavu i registraciju</li>
 *   <li>Forma za odabir komponenti računala i igara, uključujući opcije za izračun procijenjenog FPS-a</li>
 *   <li>Prikaz košarice s odabranim komponentama i ukupnom cijenom</li>
 *   <li>Alatna traka za navigaciju, uključujući mogućnosti pregleda narudžbi i odjave</li>
 *   <li>Prozor za prijavu korisnika i provjera vjerodajnica</li>
 *   <li>Prozor za registraciju novih korisnika s opcijom prihvaćanja uvjeta korištenja</li>
 * </ul>
 *
 * Glavne klase u ovom paketu su:
 * <ul>
 *   <li>{@link ui.MainFrame} - Glavni prozor aplikacije koji prikazuje formu za odabir komponenti, alatnu traku i pregled narudžbi.</li>
 *   <li>{@link ui.FormPanel} - Panel za unos komponenti računala i opcija za izračun FPS-a.</li>
 *   <li>{@link ui.ViewPanel} - Panel za prikaz odabranih komponenti u košarici, ukupne cijene i procijenjenog FPS-a.</li>
 *   <li>{@link ui.LoginScreen} - Prozor za prijavu korisnika.</li>
 *   <li>{@link ui.RegistrationScreen} - Prozor za registraciju novih korisnika.</li>
 *   <li>{@link ui.WelcomeScreen} - Početni ekran aplikacije s opcijama za prijavu i registraciju.</li>
 *   <li>{@link ui.ToolBarPanel} - Panel alatne trake s gumbima za pregled narudžbi i odjavu.</li>
 * </ul>
 *
 * Klase unutar ovog paketa koriste Swing biblioteku za izradu grafičkog sučelja aplikacije.
 * Sučelja poput {@link event.FormPanelListener} i {@link event.ToolBarListener} koriste se za upravljanje događajima.
 *
 * @since 1.0
 */
package ui;
