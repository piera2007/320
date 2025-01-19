package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Sammelt beendete Rennen und ermöglicht
 * - Standard-Statistik (Siege pro Fahrer, pro Moto, pro Land)
 * - Detaillierte Ergebnisse mit Endzeit und Zeitdifferenz
 *   für jeden Teilnehmer.
 */
public class StatistikManager {

    // anstelle von Rennen speichern wir Renndaten,
    // damit wir ausführliche Infos pro Rennen anzeigen können
    private List<Renndaten> renndatenListe;

    public StatistikManager() {
        this.renndatenListe = new ArrayList<>();
    }

    /**
     * Fügt ein beendetes Rennen hinzu
     *
     * @param rennen beendetes Rennen
     */
    public void addRennen(Rennen rennen) {
        if (!rennen.isStarted()) {
            System.out.println("Rennen nicht gestartet, kommt nicht in Statistik.");
            return;
        }
        Renndaten rd = new Renndaten(rennen);
        renndatenListe.add(rd);
    }

    /**
     * Zeigt eine Standardübersicht (Siege pro Fahrer,
     * pro Motorradtyp, pro Land).
     */
    public void zeigeStatistik() {
        if (renndatenListe.isEmpty()) {
            System.out.println("\nKeine Rennen abgeschlossen -> Keine Statistik.");
            return;
        }
        HashMap<String, Integer> siegeProFahrer = new HashMap<>();
        HashMap<String, Integer> siegeProMoto = new HashMap<>();
        HashMap<String, Integer> siegeProLand = new HashMap<>();

        for (Renndaten rd : renndatenListe) {
            Fahrer sieger = rd.getSieger();
            if (sieger == null) continue; // unentschieden

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

        System.out.println("\n=== Standard Statistik ===");
        System.out.println("Siege pro Fahrer:");
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
    }

    /**
     * Zeigt eine detaillierte Ergebnisliste
     * für jedes beendete Rennen:
     * - Streckenname
     * - Liste aller Fahrer mit Zeit und Zeitdifferenz
     * - Sieger oder Unentschieden
     */
    public void zeigeDetaillierteErgebnisse() {
        if (renndatenListe.isEmpty()) {
            System.out.println("\nKeine Rennen abgeschlossen -> Keine Details verfügbar.");
            return;
        }
        System.out.println("\n=== Detaillierte Rennergebnisse ===");

        for (int i = 0; i < renndatenListe.size(); i++) {
            Renndaten rd = renndatenListe.get(i);
            Rennen r = rd.getRennen();
            System.out.println("\n--- Rennen Nr. " + (i + 1) + " ---");
            System.out.println("Strecke: " + r.getStrecke().getName()
                    + " (Länge=" + r.getStrecke().getLaenge()
                    + ", Schwierigkeit=" + r.getStrecke().getSchwierigkeitsgrad() + ")");

            // HasMap <Fahrer, Zeit>
            HashMap<Fahrer, Double> times = rd.getZeiten();
            // Wir sortieren das nach Zeit
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
                String diffStr = (diff < 0.000001) ? " (Sieger)" : String.format(" (+%.2f)", diff);
                System.out.println(f.getFahrerName() + " (" + f.getLand() + "), Zeit= "
                        + String.format("%.2f", t) + diffStr);
            }

            if (rd.getSieger() == null) {
                System.out.println("ERGEBNIS: UNENTSCHIEDEN");
            } else {
                System.out.println("SIEGER: " + rd.getSieger().getFahrerName());
            }
        }
    }
}
