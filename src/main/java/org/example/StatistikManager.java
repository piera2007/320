package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Sammelt Rennen, zeigt Siege pro Fahrer (Name) und
 * pro Motorradtyp, optional pro Land.
 *
 * @author
 * @version 1.0
 */
public class StatistikManager {
    private List<Rennen> rennenListe;

    /**
     * Konstruktor
     */
    public StatistikManager() {
        this.rennenListe = new ArrayList<>();
    }

    /**
     * Fügt ein beendetes Rennen hinzu.
     *
     * @param rennen Rennen-Objekt
     */
    public void addRennen(Rennen rennen) {
        rennenListe.add(rennen);
    }

    /**
     * Zeigt Siege pro Fahrer-Name, Motorradtyp
     * und optional Land.
     */
    public void zeigeStatistik() {
        if (rennenListe.isEmpty()) {
            System.out.println("Noch keine Rennen abgeschlossen -> Keine Statistik.");
            return;
        }
        HashMap<String, Integer> siegeProFahrer = new HashMap<>();
        HashMap<String, Integer> siegeProMoto = new HashMap<>();
        HashMap<String, Integer> siegeProLand = new HashMap<>();

        for (Rennen r : rennenListe) {
            Fahrer sieger = r.getSieger();
            if (sieger == null) continue; // kein Sieger?

            String fahrerName = sieger.getFahrerName();
            siegeProFahrer.put(fahrerName, siegeProFahrer.getOrDefault(fahrerName, 0) + 1);

            Motorrad moto = r.getMotorrad(sieger);
            if (moto != null) {
                String motoName = moto.getClass().getSimpleName();
                siegeProMoto.put(motoName, siegeProMoto.getOrDefault(motoName, 0) + 1);
            }

            String land = sieger.getLand();
            siegeProLand.put(land, siegeProLand.getOrDefault(land, 0) + 1);
        }

        System.out.println("\n=== Statistik ===");
        System.out.println("Siege pro Fahrer:");
        siegeProFahrer.forEach((name, count) ->
                System.out.println("  " + name + ": " + count)
        );

        System.out.println("\nSiege pro Motorradtyp:");
        siegeProMoto.forEach((m, count) ->
                System.out.println("  " + m + ": " + count)
        );

        System.out.println("\nSiege pro Land:");
        siegeProLand.forEach((l, count) ->
                System.out.println("  " + l + ": " + count)
        );
    }
}
