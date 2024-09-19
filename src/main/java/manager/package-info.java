/**
 * Ovaj paket sadrži klase koje upravljaju narudžbama i korisničkim vjerodajnicama u aplikaciji PC Builder.
 * Paket omogućava funkcionalnosti kao što su:
 * <ul>
 *   <li>Autentifikacija korisnika i upravljanje korisničkim vjerodajnicama</li>
 *   <li>Dodavanje, pregled i uklanjanje narudžbi po korisniku</li>
 *   <li>Serijalizacija i pohrana korisničkih podataka i narudžbi u datoteke</li>
 *   <li>Upravljanje košaricom i provjera kompatibilnosti komponenti</li>
 *   <li>Izračunavanje performansi sustava u smislu FPS-a (Frames Per Second) za odabrane igre</li>
 * </ul>
 *
 * Glavne klase unutar paketa su:
 * <ul>
 *   <li>{@link manager.LoginManager} - Upravljanje korisničkim vjerodajnicama i autentifikacija</li>
 *   <li>{@link manager.OrderManager} - Upravljanje narudžbama, košaricom i kompatibilnošću komponenti</li>
 * </ul>
 *
 * Klasa `LoginManager` rukuje prijavom korisnika, provjerom postojećih korisnika i narudžbi te serijalizacijom i pohranom tih podataka.
 * Klasa `OrderManager` upravlja košaricom s komponentama, provjerava njihovu kompatibilnost i izračunava FPS na temelju odabranih komponenti i rezolucije.
 *
 * @since 1.0
 */
package manager;
