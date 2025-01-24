package org.example;

/**
 * Interface für ein Fahrzeug mit Basis-Methoden.
 *
 * Wird von {@link Motorrad} implementiert und
 * in Subklassen (z. B. {@link SportMotorrad})
 * konkretisiert.
 *
 * @author Piera Blum
 * @version 24.01.2025
 */
public interface IFahrzeug {
    String getMarke();
    String getModell();
    int getGeschwindigkeit();
    int getBeschleunigung();
}
