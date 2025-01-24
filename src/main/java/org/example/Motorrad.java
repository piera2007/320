package org.example;

/**
 * Abstrakte Basisklasse für Motorradtypen, die das
 * {@link IFahrzeug}-Interface implementieren.
 * <p>
 * Subklassen sind z. B. {@link SportMotorrad},
 * {@link CrossMotorrad} oder {@link TouringMotorrad}.
 * <p>
 * Felder: Marke, Modell, Geschwindigkeit, Beschleunigung.
 * Diese bestimmen die Grundeigenschaften des Motorrads.
 *
 * @author
 * @version 23.01.2025
 */
public abstract class Motorrad implements IFahrzeug {

    private String marke;
    private String modell;
    private int geschwindigkeit;
    private int beschleunigung;

    /**
     * Erzeugt ein {@code Motorrad}.
     *
     * @param marke           z. B. "Yamaha"
     * @param modell          z. B. "R1"
     * @param geschwindigkeit km/h
     * @param beschleunigung  m/s²
     */
    public Motorrad(String marke, String modell, int geschwindigkeit, int beschleunigung) {
        this.marke = marke;
        this.modell = modell;
        this.geschwindigkeit = geschwindigkeit;
        this.beschleunigung = beschleunigung;
    }

    /**
     * @return Markenname des Motorrads
     */
    @Override
    public String getMarke() {
        return marke;
    }

    /**
     * @return Modellname des Motorrads
     */
    @Override
    public String getModell() {
        return modell;
    }

    /**
     * @return maximale Geschwindigkeit in km/h
     */
    @Override
    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    /**
     * @return Beschleunigung in m/s²
     */
    @Override
    public int getBeschleunigung() {
        return beschleunigung;
    }
}
