package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Verwaltet alle gesetzten Wetten für EIN Rennen.
 * Nach dem Rennen wird ausgewertet und gelöscht.
 */
public class BetManager {
    private List<Bet> bets;

    public BetManager() {
        bets = new ArrayList<>();
    }

    /**
     * Neue Wette hinzufügen
     */
    public void addBet(Bet bet) {
        bets.add(bet);
    }

    /**
     * Zeigt alle Wetten (LandName -> Anzahl) und Einsätze
     */
    public void showAllBets() {
        if (bets.isEmpty()) {
            System.out.println("Keine Wetten vorhanden.");
            return;
        }
        // landName -> summe Einsätze, count
        HashMap<String, Double> summeEinsatzProLand = new HashMap<>();
        HashMap<String, Integer> countProLand = new HashMap<>();

        for (Bet b : bets) {
            summeEinsatzProLand.put(b.getLandName(),
                    summeEinsatzProLand.getOrDefault(b.getLandName(), 0.0) + b.getEinsatz());
            countProLand.put(b.getLandName(),
                    countProLand.getOrDefault(b.getLandName(), 0) + 1);
        }

        System.out.println("Aktuelle Wetten:");
        for (String land : countProLand.keySet()) {
            int anzahl = countProLand.get(land);
            double summe = summeEinsatzProLand.get(land);
            System.out.println("  " + anzahl + " Wetten auf " + land + ", Gesamt-Einsatz=" + summe);
        }
    }

    /**
     * Prüft, ob eine Wette gewonnen wurde,
     * wenn (siegerName) das Rennen gewinnt.
     * Dann leeren wir die bets, da Rennen abgeschlossen.
     */
    public void checkBets(String siegerName) {
        if (bets.isEmpty()) {
            System.out.println("Keine Wetten vorhanden.");
            return;
        }
        boolean anyWin = false;
        double sumWinnings = 0.0;
        for (Bet b : bets) {
            if (b.getLandName().equalsIgnoreCase(siegerName)) {
                anyWin = true;
                // Beispiel: 2xEinsatz als "Gewinn"
                sumWinnings += b.getEinsatz() * 2;
            }
        }
        if (anyWin) {
            System.out.println("Einige Wetten haben gewonnen! Gesamtgewinn: " + sumWinnings);
        } else {
            System.out.println("Keine Wette hat gewonnen.");
        }
        bets.clear();
    }
}
