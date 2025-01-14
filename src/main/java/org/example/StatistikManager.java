package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Sammelt die Ergebnisse abgeschlossener Rennen.
 * Zeigt Siege pro Land und pro Motorradtyp an.
 */
public class StatistikManager {
    private List<Rennen> rennenListe;

    public StatistikManager() {
        this.rennenListe = new ArrayList<>();
    }

    /**
     * Fügt ein beendetes Rennen hinzu
     */
    public void addRennen(Rennen rennen) {
        rennenListe.add(rennen);
    }

    /**
     * Zeigt die Siege pro Land und Motorradtyp.
     * Wenn noch keine Rennen gestartet, leer.
     */
    public void zeigeStatistik() {
        if (rennenListe.isEmpty()) {
            System.out.println("\nKeine Rennen abgeschlossen, keine Statistik!");
            return;
        }
        HashMap<String, Integer> siegeProLand = new HashMap<>();
        HashMap<String, Integer> siegeProMoto = new HashMap<>();

        for (Rennen r : rennenListe) {
            Land sieger = r.getSieger();
            if (sieger == null) continue;

            // Land
            String landName = sieger.getName();
            siegeProLand.put(landName, siegeProLand.getOrDefault(landName, 0) + 1);

            // Motorrad
            Motorrad m = r.getMotorrad(sieger);
            if (m != null) {
                String motoName = m.getClass().getSimpleName();
                siegeProMoto.put(motoName, siegeProMoto.getOrDefault(motoName, 0) + 1);
            }
        }

        System.out.println("\n=== Statistik ===");
        System.out.println("Siege pro Land:");
        siegeProLand.forEach((l, anzahl) -> System.out.println("  " + l + " : " + anzahl));

        System.out.println("\nSiege pro Motorradtyp:");
        siegeProMoto.forEach((m, anzahl) -> System.out.println("  " + m + " : " + anzahl));
    }
}
