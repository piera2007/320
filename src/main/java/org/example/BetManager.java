package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Die Klasse {@code BetManager} verwaltet die vom Benutzer
 * platzierten Wetten und das Guthaben des Benutzers.
 * <p>
 * Beim Platzieren einer Wette wird der Einsatz vom Guthaben
 * abgezogen. Wenn das Rennen beendet ist, wird der Sieger
 * ermittelt und die Methode {@link #checkBets(String)} zahlt
 * Gewinne aus und aktualisiert das Guthaben.
 * <p>
 * Folgende Hauptaufgaben werden hier erledigt:
 * <ul>
 *   <li>Verwaltung des Benutzer-Guthabens ({@code userBalance})</li>
 *   <li>Speichern aller Wetten in einer Liste ({@code bets})</li>
 *   <li>Abziehen des Wetteinsatzes beim Hinzufügen einer Wette</li>
 *   <li>Auszahlung von Gewinnen bei eindeutigem Sieger</li>
 *   <li>Anzeigen aller platzierten Wetten und deren Einsätze</li>
 * </ul>
 *
 * @author Piera Blum
 * @version 1.0
 */
public class BetManager {

    /**
     * Eine Liste aller {@link Bet}-Objekte, die der Benutzer
     * vor Start des Rennens platziert hat.
     */
    private List<Bet> bets;

    /**
     * Das aktuelle Guthaben des Benutzers.
     */
    private double userBalance;

    /**
     * Erstellt einen neuen {@code BetManager}, der die Wetten
     * des Benutzers und dessen Guthaben verwaltet.
     *
     * @param startBalance das Anfangsguthaben des Benutzers
     */
    public BetManager(double startBalance) {
        this.bets = new ArrayList<>();
        this.userBalance = startBalance;
    }

    /**
     * Fügt eine neue Wette hinzu, sofern ausreichend Guthaben vorhanden ist.
     * <p>
     * Der Einsatz wird sofort vom Guthaben abgezogen. Falls das Guthaben
     * nicht ausreicht, wird die Wette nicht gespeichert.
     *
     * @param bet die zu platzierende Wette
     * @return {@code true}, wenn die Wette erfolgreich hinzugefügt wurde,
     *         {@code false}, wenn nicht genug Guthaben vorhanden ist
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
     * Gibt alle laufenden Wetten zusammen mit den jeweiligen
     * Einsätzen und dem aktuellen Guthaben auf der Konsole aus.
     * <p>
     * Falls keine Wetten vorliegen, wird dies entsprechend
     * ausgegeben.
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
     * Überprüft nach Ende eines Rennens, ob es einen eindeutigen
     * Sieger gibt und zahlt eventuelle Gewinne aus.
     * <p>
     * Wenn {@code siegerName} leer oder {@code null} ist, gilt das Rennen
     * als unentschieden, und alle Einsätze sind verloren. Wenn ein
     * eindeutiger Sieger existiert, erhält der Benutzer für jede
     * richtige Wette das Doppelte des Einsatzes.
     *
     * @param siegerName der Name des siegreichen Fahrers (oder {@code ""}/{@code null} bei Unentschieden)
     * @return die Gesamtsumme, die dem Benutzer als Gewinn
     *         gutgeschrieben wird (falls keine richtige Wette
     *         existiert, wird {@code 0.0} zurückgegeben)
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
     * Liefert das aktuelle Guthaben des Benutzers zurück.
     *
     * @return der aktuelle Kontostand
     */
    public double getUserBalance() {
        return userBalance;
    }
}
