package org.example;

/**
 * Konkrete Klasse SportMotorrad.
 *
 * @author Piera Blum
 * @version 24.01.2025
 */
public class SportMotorrad extends Motorrad {

    /**
     * Erzeugt ein SportMotorrad-Objekt
     *
     * @param marke Markenname
     * @param modell Modellname
     * @param geschwindigkeit km/h
     * @param beschleunigung m/s²
     */
    public SportMotorrad(String marke, String modell, int geschwindigkeit, int beschleunigung) {
        super(marke, modell, geschwindigkeit, beschleunigung);
    }
}
