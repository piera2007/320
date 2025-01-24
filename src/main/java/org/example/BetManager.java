package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Verwaltet alle {@link Bet} (Wetten) und das Guthaben
 * eines Benutzers.
 * <p>
 * - Platzieren einer Wette mit {@link #addBet(Bet)}
 * - Anzeigen mit {@link #showAllBets()}
 * - Auswerten mit {@link #checkBets(String)}
 * - Kontostand mit {@link #getUserBalance()}
 *
 * @author Piera Blum
 * @version 24.01.2025
 */
public class BetManager {

    private List<Bet> bets;
    private double userBalance;

    /**
     * Erzeugt einen {@code BetManager} mit Startguthaben.
     *
     * @param startBalance Anfangsguthaben
     */
    public BetManager(double startBalance) {
        this.bets = new ArrayList<>();
        this.userBalance = startBalance;
    }

    /**
     * Fügt eine Wette hinzu, falls genug Guthaben vorhanden.
     *
     * @param bet die Wette
     * @return true bei Erfolg, sonst false
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
     * Zeigt alle laufenden Wetten und das aktuelle Guthaben.
     * Gibt "Keine Wetten vorhanden." aus, wenn {@code bets} leer.
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
     * Prüft nach Rennende, ob ein eindeutiger Sieger existiert,
     * zahlt Gewinne aus (2-facher Einsatz) und leert die Wetten.
     *
     * @param siegerName Name des Siegers oder "" bei Unentschieden
     * @return Gesamte Gewinnsumme (oder 0.0)
     */
    public double checkBets(String siegerName) {
        if (bets.isEmpty()) {
            System.out.println("Keine Wetten vorhanden.");
            return 0.0;
        }
        // Unentschieden
        if (siegerName == null || siegerName.isEmpty()) {
            System.out.println("Unentschieden! Keine Gewinne.");
            bets.clear();
            return 0.0;
        }

        double totalWin = 0.0;
        boolean anyWin = false;
        for (Bet b : bets) {
            if (b.getFahrerName().equalsIgnoreCase(siegerName)) {
                anyWin = true;
                totalWin += (b.getEinsatz() * 2);
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
     * @return aktuelles Guthaben
     */
    public double getUserBalance() {
        return userBalance;
    }
}
