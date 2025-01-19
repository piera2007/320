package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;

/**
 * Der {@code StatistikManager} speichert alle beendeten Rennen in Form
 * von {@link Renndaten} und ermöglicht eine kombinierte Darstellung von:
 * <ol>
 *   <li>Standard-Statistik (Siege pro Fahrer, Motorradtyp, Land)</li>
 *   <li>Detaillierten Ergebnissen (Zeiten, Zeitdifferenzen zum Sieger)</li>
 * </ol>
 * <p>
 * Diese Klasse demonstriert, wie man sowohl einfache Statistiken
 * (z. B. Anzahl Siege) als auch ausführlichere Renndetails in einem
 * Schritt ausgeben kann.
 * <p>
 * Typische Verwendung:
 * <ul>
 *   <li>Erstelle einen {@code StatistikManager}.</li>
 *   <li>Füge mit {@link #addRennen(Rennen)} nach Beendigung eines Rennens
 *       ein {@code Rennen} hinzu.</li>
 *   <li>Rufe {@link #zeigeStatistik()} auf, um sowohl die
 *       Standard-Statistik als auch die detaillierten Rennergebnisse
 *       aller gespeicherten Rennen zu sehen.</li>
 * </ul>
 *
 * @author Piera Blum
 * @version 1.0
 */
public class StatistikManager {

    /**
     * Eine Liste, die für jedes beendete Rennen
     * ein {@link Renndaten}-Objekt speichert.
     */
    private List<Renndaten> renndatenListe;

    /**
     * Erstellt einen neuen {@code StatistikManager} mit
     * einer anfänglich leeren Liste von Renndaten.
     */
    public StatistikManager() {
        renndatenListe = new ArrayList<>();
    }

    /**
     * Fügt ein beendetes Rennen der Statistik hinzu, sofern
     * das Rennen tatsächlich gestartet wurde.
     * <p>
     * Bei einem noch nicht gestarteten Rennen wird eine
     * Meldung ausgegeben und das Rennen nicht gespeichert.
     *
     * @param rennen das beendete Rennen
     */
    public void addRennen(Rennen rennen) {
        if (!rennen.isStarted()) {
            System.out.println("Rennen wurde nie gestartet, kommt nicht in Statistik.");
            return;
        }
        Renndaten rd = new Renndaten(rennen);
        renndatenListe.add(rd);
    }

    /**
     * Zeigt in einem Durchgang sowohl die Standard-Statistik
     * als auch detaillierte Ergebnisse aller Rennen an.
     * <p>
     * <strong>Standard-Statistik</strong>: Anzahl Siege
     * (pro Fahrername, Motorradtyp, Land).
     * <p>
     * <strong>Detaillierte Rennergebnisse</strong>:
     * Zeit pro Fahrer, Zeitdifferenz zum Sieger, Streckeninfos,
     * und Anzeige, ob ein Rennen mit Unentschieden endete.
     * <p>
     * Falls keine Rennen vorhanden sind, wird ein Hinweis
     * ausgegeben.
     */
    public void zeigeStatistik() {
        if (renndatenListe.isEmpty()) {
            System.out.println("\nKeine Rennen abgeschlossen -> Keine Statistik.");
            return;
        }

        // =========== TEIL 1: Standard-Statistik ===========
        HashMap<String, Integer> siegeProFahrer = new HashMap<>();
        HashMap<String, Integer> siegeProMoto = new HashMap<>();
        HashMap<String, Integer> siegeProLand = new HashMap<>();

        for (Renndaten rd : renndatenListe) {
            Fahrer sieger = rd.getSieger();
            if (sieger == null) continue; // unentschieden => kein Sieger

            String fahrerName = sieger.getFahrerName();
            siegeProFahrer.put(fahrerName, siegeProFahrer.getOrDefault(fahrerName, 0) + 1);

            Motorrad moto = rd.getRennen().getMotorrad(sieger);
            if (moto != null) {
                String mName = moto.getClass().getSimpleName();
                siegeProMoto.put(mName, siegeProMoto.getOrDefault(mName, 0) + 1);
            }

            String land = sieger.getLand();
            siegeProLand.put(land, siegeProLand.getOrDefault(land, 0) + 1);
        }

        System.out.println("\n=== Standard-Statistik (Siege) ===");
        System.out.println("\nSiege pro Fahrer:");
        siegeProFahrer.forEach((f, c) ->
                System.out.println("  " + f + ": " + c)
        );

        System.out.println("\nSiege pro Motorradtyp:");
        siegeProMoto.forEach((m, c) ->
                System.out.println("  " + m + ": " + c)
        );

        System.out.println("\nSiege pro Land:");
        siegeProLand.forEach((l, c) ->
                System.out.println("  " + l + ": " + c)
        );

        // =========== TEIL 2: Detaillierte Ergebnisse ===========
        System.out.println("\n=== Detaillierte Rennergebnisse ===");

        for (int i = 0; i < renndatenListe.size(); i++) {
            Renndaten rd = renndatenListe.get(i);
            Rennen r = rd.getRennen();
            System.out.println("\n--- Rennen Nr. " + (i + 1) + " ---");
            System.out.println("Strecke: " + r.getStrecke().getName()
                    + " (Länge=" + r.getStrecke().getLaenge()
                    + ", Schwierigkeit=" + r.getStrecke().getSchwierigkeitsgrad() + ")");

            // Fahrer -> Zeit
            HashMap<Fahrer, Double> times = rd.getZeiten();
            // Sortieren nach Zeit
            List<Fahrer> sortedFahrer = new ArrayList<>(times.keySet());
            sortedFahrer.sort(Comparator.comparingDouble(times::get));

            if (sortedFahrer.isEmpty()) {
                System.out.println("Keine Teilnehmer!?");
                continue;
            }
            double bestTime = times.get(sortedFahrer.get(0));

            for (Fahrer f : sortedFahrer) {
                double t = times.get(f);
                double diff = t - bestTime;
                String diffStr = (Math.abs(diff) < 0.000001)
                        ? " (Sieger)"
                        : String.format(" (+%.2f)", diff);
                System.out.println(String.format(
                        "%s (%s), Zeit= %.2f%s",
                        f.getFahrerName(), f.getLand(), t, diffStr
                ));
            }

            if (rd.getSieger() == null) {
                System.out.println("ERGEBNIS: UNENTSCHIEDEN");
            } else {
                System.out.println("SIEGER: " + rd.getSieger().getFahrerName());
            }
        }
    }
}
