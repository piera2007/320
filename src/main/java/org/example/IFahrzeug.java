package org.example;

/**
 * Interface für Fahrzeug - hier Motorrad.
 */
public interface IFahrzeug {
    String getMarke();
    String getModell();
    int getGeschwindigkeit(); // km/h
    int getBeschleunigung();  // m/s²
}
