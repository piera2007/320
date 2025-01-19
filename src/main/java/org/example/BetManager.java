package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Verwalter für Wetten (auf Fahrer).
 *
 * Nun mit Benutzer-Guthaben-Verwaltung:
 * - Wenn eine Wette gewonnen wird, erhält der Benutzer
 *   den doppelten Einsatz zurück.
 * - Bei Verlust oder Unentschieden geht der Einsatz verloren.
 *
 * @author
 * @version 1.0
 */
public class BetManager {
    private List<Bet> bets;
    private double userBalance; // Guthaben des Benutzers

    /**
     * Konstruktor: Start-Guthaben kann man festlegen.
     *
     * @param startBalance Anfangsguthaben
     */
    public BetManager(double startBalance) {
        this.bets = new ArrayList<>();
        this.userBalance = startBalance;
    }

    /**
     * Neue Wette hinzufügen (sofern userBalance >= Einsatz).
     *
     * @param bet Wette
     * @return true, wenn Wette erfolgreich platziert, false wenn nicht genug Guthaben
     */
    public boolean addBet(Bet bet) {
        if (bet.getEinsatz() > userBalance) {
            return false; // nicht genug Geld
        }
        // Einsatz direkt abziehen
        userBalance -= bet.getEinsatz();
        bets.add(bet);
        return true;
    }

    /**
     * Zeigt alle Wetten, gruppiert nach Fahrername.
     */
    public void showAllBets() {
        if (bets.isEmpty()) {
            System.out.println("Keine Wetten vorhanden.");
            return;
        }
        HashMap<String, Double> sumEinsatz = new HashMap<>();
        HashMap<String, Integer> countWetten = new HashMap<>();

        for (Bet b : bets) {
            String fn = b.getFahrerName();
            sumEinsatz.put(fn, sumEinsatz.getOrDefault(fn, 0.0) + b.getEinsatz());
            countWetten.put(fn, countWetten.getOrDefault(fn, 0) + 1);
        }

        System.out.println("\nAktuelle Wetten:");
        for (String name : sumEinsatz.keySet()) {
            double sum = sumEinsatz.get(name);
            int c = countWetten.get(name);
            System.out.println("  " + c + "x auf " + name + " (Summe Einsätze=" + sum + ")");
        }
        System.out.println("Aktuelles Guthaben: " + userBalance);
    }

    /**
     * Prüft, ob es einen eindeutigen Sieger gibt.
     * - Wenn unentschieden => alle verlieren
     * - Wenn eindeutig => Gewinner bekommen 2xEinsatz zurück
     *
     * @param siegerName Name des Siegers oder null/Leerstring bei unentschieden
     */
    public void checkBets(String siegerName) {
        if (bets.isEmpty()) {
            System.out.println("Keine Wetten vorhanden.");
            return;
        }
        // unentschieden => siegerName == null oder ""
        if (siegerName == null || siegerName.isEmpty()) {
            System.out.println("Unentschieden! Keine Wetten gewinnen.");
            bets.clear();
            return;
        }

        double totalWin = 0.0;
        boolean anyWin = false;

        for (Bet b : bets) {
            if (b.getFahrerName().equalsIgnoreCase(siegerName)) {
                anyWin = true;
                double plus = b.getEinsatz() * 2;
                userBalance += plus;
                totalWin += plus;
            }
        }
        if (anyWin) {
            System.out.println("Einige Wetten haben gewonnen! Gesamt-Auszahlung: " + totalWin);
        } else {
            System.out.println("Keine Wette hat gewonnen.");
        }
        bets.clear();
    }

    /**
     * @return aktuelles Benutzer-Guthaben
     */
    public double getUserBalance() {
        return userBalance;
    }
}
