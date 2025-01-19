package org.example;

/**
 * Stellt eine Liste vordefinierter Motorrad-Objekte bereit.
 * Je nach Streckentyp kann eine Auswahl eingeschränkt werden.
 *
 * @author
 * @version 1.0
 */
public class MotorradListe {
    // Beispielhafte Instanzen
    public static final Motorrad SPORT = new SportMotorrad("Yamaha", "R1", 260, 12);
    public static final Motorrad CROSS = new CrossMotorrad("KTM", "Enduro", 180, 8);
    public static final Motorrad TOURING = new TouringMotorrad("Honda", "GoldWing", 200, 6);

    /**
     * Gibt alle verfügbaren Motorräder zurück (allgemein).
     *
     * @return Array von Motorrad
     */
    public static Motorrad[] getAlle() {
        return new Motorrad[]{SPORT, CROSS, TOURING};
    }

    /**
     * Gibt je nach Strecken-Schwierigkeitsgrad
     * eine eingeschränkte Auswahl zurück:
     * - Easy: Sport, Touring
     * - Medium: Alle
     * - Hard: Cross, Touring
     *
     * @param schwierigkeitsgrad 1-10
     * @return passendes Array
     */
    public static Motorrad[] getVerfuegbareFuerStrecke(int schwierigkeitsgrad) {
        if (schwierigkeitsgrad <= 3) {
            // Easy
            return new Motorrad[]{SPORT, TOURING};
        } else if (schwierigkeitsgrad <= 6) {
            // Medium
            return new Motorrad[]{SPORT, CROSS, TOURING};
        } else {
            // Hard
            return new Motorrad[]{CROSS, TOURING};
        }
    }
}
