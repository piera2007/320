package org.example;

/**
 * Abstrakte Klasse für Motorradtypen.
 * Implementiert {@link IFahrzeug}.
 *
 * Subklassen: SportMotorrad, CrossMotorrad, TouringMotorrad
 * Demonstriert Vererbung + Interface.
 */
public abstract class Motorrad implements IFahrzeug {
    private String marke;
    private String modell;
    private int geschwindigkeit;
    private int beschleunigung;

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
