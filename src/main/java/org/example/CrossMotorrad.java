package org.example;

/**
 * Cross-Motorrad für schwierige Geländestrecken.
 *
 * Erbt von {@link Motorrad} und setzt dessen Konstruktor um.
 *
 * @author Piera Blum
 * @version 24.01.2025
 */
public class CrossMotorrad extends Motorrad {

    /**
     * Erzeugt ein {@code CrossMotorrad}.
     *
     * @param marke           Motorrad-Marke
     * @param modell          Modellbezeichnung
     * @param geschwindigkeit in km/h
     * @param beschleunigung  in m/s²
     */
    public CrossMotorrad(String marke, String modell, int geschwindigkeit, int beschleunigung) {
        super(marke, modell, geschwindigkeit, beschleunigung);
    }
}
