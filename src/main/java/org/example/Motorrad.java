package org.example;

/**
 * Abstrakte Oberklasse für verschiedene Motorradtypen.
 * Implementiert das IFahrzeug-Interface.
 * Demonstriert Vererbung + Interface.
 *
 * Subklassen: SportMotorrad, CrossMotorrad, TouringMotorrad
 *
 * @author
 * @version 1.0
 */
public abstract class Motorrad implements IFahrzeug {

    private String marke;
    private String modell;
    private int geschwindigkeit;  // km/h
    private int beschleunigung;   // m/s²

    /**
     * Konstruktor
     *
     * @param marke Markenname
     * @param modell Modellname
     * @param geschwindigkeit km/h
     * @param beschleunigung m/s²
     */
    public Motorrad(String marke, String modell, int geschwindigkeit, int beschleunigung) {
        this.marke = marke;
        this.modell = modell;
        this.geschwindigkeit = geschwindigkeit;
        this.beschleunigung = beschleunigung;
    }

    @Override
    public String getMarke() {
        return marke;
    }

    @Override
    public String getModell() {
        return modell;
    }

    @Override
    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    @Override
    public int getBeschleunigung() {
        return beschleunigung;
    }
}
