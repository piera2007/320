package org.example;

/**
 * Interface für ein Fahrzeug.
 *
 * @author
 * @version 1.0
 */
public interface IFahrzeug {
    /**
     * @return Markenname des Fahrzeugs
     */
    String getMarke();

    /**
     * @return Modellname des Fahrzeugs
     */
    String getModell();

    /**
     * @return Geschwindigkeit in km/h
     */
    int getGeschwindigkeit();

    /**
     * @return Beschleunigung in m/s²
     */
    int getBeschleunigung();
}
