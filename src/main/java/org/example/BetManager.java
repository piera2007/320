package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Verwalter für Wetten (auf Fahrer).
 *
 * - userBalance = Geld des Benutzers
 * - addBet() zieht Einsatz vom Guthaben ab
 * - checkBets() gibt den Gesamtgewinn zurück,
 *   aktualisiert userBalance
 */
public class BetManager {
    private List<Bet> bets;
    private double userBalance;

    /**
     * Konstruktor
     *
     * @param startBalance Anfangsguthaben
     */
    public BetManager(double startBalance) {
        this.bets = new ArrayList<>();
        this.userBalance = startBalance;
    }

    /**
     * Fügt eine Wette hinzu, wenn genug Guthaben vorhanden ist.
     *
     * @param bet Wette
     * @return true bei Erfolg, false wenn zu wenig Guthaben
     */
    public boolean addBet(Bet bet) {
        if (bet.getEinsatz() > userBalance) {
            return false;
        }
        userBalance -= bet.getEinsatz();
        bets.add(bet);
        return true;
    }

    /**
     * Zeigt laufende Wetten gruppiert nach Fahrername,
     * plus aktuelles Guthaben.
     */
    public void showAllBets() {
        if (bets.isEmpty()) {
            System.out.println("Keine Wetten vorhanden.");
            return;
        }
        HashMap<String, Double> sumEinsatz = new HashMap<>();
        HashMap<String, Integer> countBet = new HashMap<>();

        for (Bet b : bets) {
            String fn = b.getFahrerName();
            sumEinsatz.put(fn, sumEinsatz.getOrDefault(fn, 0.0) + b.getEinsatz());
            countBet.put(fn, countBet.getOrDefault(fn, 0) + 1);
        }

        System.out.println("\nAktuelle Wetten:");
        for (String name : sumEinsatz.keySet()) {
            double sum = sumEinsatz.get(name);
            int c = countBet.get(name);
            System.out.println("  " + c + "x auf " + name + " (Summe Einsätze=" + sum + ")");
        }
        System.out.println("Guthaben: " + userBalance);
    }

    /**
     * Prüft Wetten bei eindeutigem Sieger (siegerName),
     * bei unentschieden => 0.0
     *
     * @param siegerName Name des Siegers, "" oder null bei Unentschieden
     * @return Gesamtgewinn (diese Summe kommt zum Guthaben dazu)
     */
    public double checkBets(String siegerName) {
        if (bets.isEmpty()) {
            System.out.println("Keine Wetten vorhanden.");
            return 0.0;
        }
        if (siegerName == null || siegerName.isEmpty()) {
            // unentschieden
            System.out.println("Unentschieden! Keine Gewinne.");
            bets.clear();
            return 0.0;
        }

        double totalWin = 0.0;
        boolean anyWin = false;
        for (Bet b : bets) {
            if (b.getFahrerName().equalsIgnoreCase(siegerName)) {
                anyWin = true;
                double plus = b.getEinsatz() * 2;
                totalWin += plus;
            }
        }

        if (anyWin) {
            userBalance += totalWin;
            System.out.println("Einige Wetten haben gewonnen! Ausbezahlter Gewinn: " + totalWin);
        } else {
            System.out.println("Keine Wette hat gewonnen.");
        }
        bets.clear();
        return anyWin ? totalWin : 0.0;
    }

    /**
     * @return aktuelles Benutzer-Guthaben
     */
    public double getUserBalance() {
        return userBalance;
    }
}
