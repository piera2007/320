package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;

/**
 * Speichert beendete Rennen als {@link Renndaten} und
 * zeigt eine kombinierte Statistik:
 * <ol>
 *   <li>Standard (Siege pro Fahrer, Motorrad, Land)</li>
 *   <li>Detaillierte Ergebnisse (Zeiten und Zeitdifferenzen)</li>
 * </ol>
 * <p>
 * Typische Nutzung:
 * <ul>
 *   <li>Rennen mit {@link #addRennen(Rennen)} hinzufügen</li>
 *   <li>{@link #zeigeStatistik()} aufrufen</li>
 * </ul>
 *
 * @author
 * @version 23.01.2025
 */
public class StatistikManager {

    private List<Renndaten> renndatenListe;

    /**
     * Erstellt einen leeren {@code StatistikManager}.
     */
    public StatistikManager() {
        renndatenListe = new ArrayList<>();
    }

    /**
     * Fügt ein beendetes Rennen hinzu, sofern es tatsächlich gestartet wurde.
     *
     * @param rennen das beendete {@link Rennen}
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
     * Zeigt die Standard-Statistik (Siege) und
     * detaillierte Ergebnisse (Zeiten) für alle gespeicherten Rennen.
     * <p>
     * Ist die Liste leer, erscheint ein Hinweis.
     */
    public void zeigeStatistik() {
        if (renndatenListe.isEmpty()) {
            System.out.println("\nKeine Rennen abgeschlossen -> Keine Statistik.");
            return;
        }

        // 1) Standard-Statistik
        HashMap<String, Integer> siegeProFahrer = new HashMap<>();
        HashMap<String, Integer> siegeProMoto = new HashMap<>();
        HashMap<String, Integer> siegeProLand = new HashMap<>();

        for (Renndaten rd : renndatenListe) {
            Fahrer sieger = rd.getSieger();
            if (sieger == null) continue; // unentschieden

            String fahrerName = sieger.getFahrerName();
            siegeProFahrer.put(fahrerName,
                    siegeProFahrer.getOrDefault(fahrerName, 0) + 1);

            Motorrad moto = rd.getRennen().getMotorrad(sieger);
            if (moto != null) {
                String mName = moto.getClass().getSimpleName();
                siegeProMoto.put(mName,
                        siegeProMoto.getOrDefault(mName, 0) + 1);
            }

            String land = sieger.getLand();
            siegeProLand.put(land,
                    siegeProLand.getOrDefault(land, 0) + 1);
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

        // 2) Detaillierte Ergebnisse
        System.out.println("\n=== Detaillierte Rennergebnisse ===");

        for (int i = 0; i < renndatenListe.size(); i++) {
            Renndaten rd = renndatenListe.get(i);
            Rennen r = rd.getRennen();
            System.out.println("\n--- Rennen Nr. " + (i + 1) + " ---");
            System.out.println("Strecke: " + r.getStrecke().getName()
                    + " (Länge=" + r.getStrecke().getLaenge()
                    + ", Schwierigkeit=" + r.getStrecke().getSchwierigkeitsgrad() + ")");

            HashMap<Fahrer, Double> times = rd.getZeiten();
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
