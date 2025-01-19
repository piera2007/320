package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet die Klasse {@link BetManager} mit Unit-Tests.
 * <p>
 * Folgende Methoden werden abgedeckt:
 * <ul>
 *   <li>{@link BetManager#addBet(Bet)}</li>
 *   <li>{@link BetManager#showAllBets()}</li>
 *   <li>{@link BetManager#checkBets(String)}</li>
 *   <li>{@link BetManager#getUserBalance()}</li>
 * </ul>
 */
class BetManagerTest {

    private BetManager betManager;

    /**
     * Wird vor jedem Test ausgeführt. Setzt das Startguthaben
     * auf 100.0 und erzeugt ein neues {@link BetManager}-Objekt.
     */
    @BeforeEach
    void setUp() {
        betManager = new BetManager(100.0);
    }

    /**
     * Testet {@link BetManager#addBet(Bet)} mit ausreichend Guthaben.
     */
    @Test
    void testAddBetSuccessful() {
        Bet bet = new Bet("Max", 30.0);
        boolean result = betManager.addBet(bet);

        // Der Einsatz sollte abgezogen und die Wette angenommen werden.
        assertTrue(result, "Die Wette sollte erfolgreich sein, da genug Guthaben vorhanden.");
        assertEquals(70.0, betManager.getUserBalance(), 0.0001,
                "Nach Abzug von 30 sollte das Guthaben 70 sein.");
    }

    /**
     * Testet {@link BetManager#addBet(Bet)} mit unzureichendem Guthaben.
     */
    @Test
    void testAddBetNotEnoughBalance() {
        Bet bet = new Bet("Max", 150.0);
        boolean result = betManager.addBet(bet);

        // Zu hoher Einsatz -> Wette sollte fehlschlagen.
        assertFalse(result, "Wette sollte abgelehnt werden, da Guthaben zu gering.");
        assertEquals(100.0, betManager.getUserBalance(), 0.0001,
                "Das Guthaben sollte unverändert bei 100.0 bleiben.");
    }

    /**
     * Testet {@link BetManager#showAllBets()} in einem einfachen Szenario,
     * um sicherzustellen, dass die Methode fehlerfrei ausgeführt wird.
     * <p>
     * Eine inhaltliche Prüfung der Konsolenausgabe wäre
     * aufwändiger (Capturing von System.out). Hier testen wir
     * nur, dass kein Fehler geworfen wird.
     */
    @Test
    void testShowAllBets() {
        // Vorher ein, zwei Wetten hinzufügen:
        betManager.addBet(new Bet("FahrerA", 20.0));
        betManager.addBet(new Bet("FahrerB", 10.0));

        // Die Methode sollte fehlerfrei ausgeführt werden.
        assertDoesNotThrow(() -> betManager.showAllBets());
    }

    /**
     * Testet {@link BetManager#checkBets(String)} im Unentschieden-Fall.
     */
    @Test
    void testCheckBetsUnentschieden() {
        // Einsatz abgezogen -> Guthaben = 80.0
        betManager.addBet(new Bet("Max", 20.0));

        // Unentschieden => siegerName = ""
        double gewinn = betManager.checkBets("");
        assertEquals(0.0, gewinn, 0.0001, "Bei Unentschieden gibt es keinen Gewinn.");
        // Guthaben bleibt bei 80.0, da Einsatz futsch
        assertEquals(80.0, betManager.getUserBalance(), 0.0001);
    }

    /**
     * Testet {@link BetManager#checkBets(String)} mit korrektem Sieger.
     */
    @Test
    void testCheckBetsCorrectWinner() {
        betManager.addBet(new Bet("Max", 20.0));

        // Richtiger Tipp: 2 * Einsatz = 40 werden gutgeschrieben.
        double gewinn = betManager.checkBets("Max");
        assertEquals(40.0, gewinn, 0.0001, "Gewinn sollte 40 sein (doppelter Einsatz).");

        // Start: 100 - 20 (Einsatz) + 40 (Gewinn) = 120
        assertEquals(120.0, betManager.getUserBalance(), 0.0001,
                "Guthaben sollte auf 120 steigen.");
    }

    /**
     * Testet {@link BetManager#checkBets(String)} mit falschem Sieger.
     */
    @Test
    void testCheckBetsWrongWinner() {
        betManager.addBet(new Bet("Max", 20.0));

        // Sieger ist jemand anders => kein Gewinn
        double gewinn = betManager.checkBets("Lisa");
        assertEquals(0.0, gewinn, 0.0001, "Kein Gewinn, da Tipp falsch.");

        // Guthaben: 100 - 20 = 80 (Einsatz weg).
        assertEquals(80.0, betManager.getUserBalance(), 0.0001);
    }

    /**
     * Testet den Fall, dass gar keine Wetten vorliegen,
     * wenn {@link BetManager#checkBets(String)} aufgerufen wird.
     */
    @Test
    void testCheckBetsWithNoBets() {
        double gewinn = betManager.checkBets("Max");
        // Keine Wetten

        assertEquals(0.0, gewinn, 0.0001, "Ohne Wetten kein Gewinn.");
        assertEquals(100.0, betManager.getUserBalance(), 0.0001,
                "Guthaben bleibt unverändert.");
    }

    /**
     * Testet {@link BetManager#getUserBalance()} bei einem neuen
     * {@link BetManager}-Objekt ohne Wetten.
     */
    @Test
    void testGetUserBalanceInitial() {
        // Direkt nach setUp() sollte das Guthaben 100 sein
        assertEquals(100.0, betManager.getUserBalance(), 0.0001);
    }
}
