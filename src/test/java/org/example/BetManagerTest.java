package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BetManagerTest {

    private BetManager betManager;

    @BeforeEach
    void setUp() {
        betManager = new BetManager(100.0);
    }

    @Test
    void testAddBetSuccessful() {
        Bet bet = new Bet("Max", 30.0);
        boolean result = betManager.addBet(bet);

        assertTrue(result, "Die Wette sollte erfolgreich sein, da genug Guthaben vorhanden.");
        assertEquals(70.0, betManager.getUserBalance(), 0.0001,
                "Nach Abzug von 30 sollte das Guthaben 70 sein.");
    }

    @Test
    void testAddBetNotEnoughBalance() {
        Bet bet = new Bet("Max", 150.0);
        boolean result = betManager.addBet(bet);

        assertFalse(result, "Wette sollte abgelehnt werden, da Guthaben zu gering.");
        assertEquals(100.0, betManager.getUserBalance(), 0.0001,
                "Das Guthaben sollte unverändert bei 100.0 bleiben.");
    }


    @Test
    void testShowAllBets() {
        betManager.addBet(new Bet("FahrerA", 20.0));
        betManager.addBet(new Bet("FahrerB", 10.0));

        assertDoesNotThrow(() -> betManager.showAllBets());
    }

    @Test
    void testCheckBetsUnentschieden() {
        betManager.addBet(new Bet("Max", 20.0));

        double gewinn = betManager.checkBets("");
        assertEquals(0.0, gewinn, 0.0001, "Bei Unentschieden gibt es keinen Gewinn.");
        assertEquals(80.0, betManager.getUserBalance(), 0.0001);
    }

    @Test
    void testCheckBetsCorrectWinner() {
        betManager.addBet(new Bet("Max", 20.0));

        double gewinn = betManager.checkBets("Max");
        assertEquals(40.0, gewinn, 0.0001, "Gewinn sollte 40 sein (doppelter Einsatz).");

        assertEquals(120.0, betManager.getUserBalance(), 0.0001,
                "Guthaben sollte auf 120 steigen.");
    }

    @Test
    void testCheckBetsWrongWinner() {
        betManager.addBet(new Bet("Max", 20.0));

        double gewinn = betManager.checkBets("Lisa");
        assertEquals(0.0, gewinn, 0.0001, "Kein Gewinn, da Tipp falsch.");

        assertEquals(80.0, betManager.getUserBalance(), 0.0001);
    }


    @Test
    void testCheckBetsWithNoBets() {
        double gewinn = betManager.checkBets("Max");

        assertEquals(0.0, gewinn, 0.0001, "Ohne Wetten kein Gewinn.");
        assertEquals(100.0, betManager.getUserBalance(), 0.0001,
                "Guthaben bleibt unverändert.");
    }

    @Test
    void testGetUserBalanceInitial() {
        assertEquals(100.0, betManager.getUserBalance(), 0.0001);
    }
}
