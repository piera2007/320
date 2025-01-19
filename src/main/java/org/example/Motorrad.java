package org.example;

/**
 * Abstrakte Oberklasse für verschiedene Motorradtypen, die das
 * {@link IFahrzeug}-Interface implementieren.
 * <p>
 * Diese Klasse dient als Basis für konkrete Motorradklassen
 * wie {@link SportMotorrad}, {@link CrossMotorrad} und
 * {@link TouringMotorrad}. Sie demonstriert Vererbung (durch
 * ihre Subklassen) und die Implementierung eines Interfaces.
 * <p>
 * Vererbte Felder:
 * <ul>
 *   <li>{@code marke} (z.B. "Yamaha")</li>
 *   <li>{@code modell} (z.B. "R1")</li>
 *   <li>{@code geschwindigkeit} (km/h)</li>
 *   <li>{@code beschleunigung} (m/s²)</li>
 * </ul>
 * Diese Felder bestimmen die Grundeigenschaften eines
 * Motorrads, die in den Unterklassen weiter spezialisiert
 * werden können.
 * <p>
 * @author Piera Blum
 * @version 1.0
 */
public abstract class Motorrad implements IFahrzeug {

    /**
     * Die Marke des Motorrads, z.B. "Honda" oder "BMW".
     */
    private String marke;

    /**
     * Das Modell des Motorrads, z.B. "GoldWing" oder "GSX-R".
     */
    private String modell;

    /**
     * Die maximale Geschwindigkeit des Motorrads in km/h.
     */
    private int geschwindigkeit;

    /**
     * Die Beschleunigung des Motorrads in m/s².
     */
    private int beschleunigung;

    /**
     * Konstruktor, der die Grundattribute des Motorrads setzt.
     *
     * @param marke           Markenname (z.B. "Yamaha")
     * @param modell          Modellname (z.B. "R1")
     * @param geschwindigkeit Maximale Geschwindigkeit in km/h
     * @param beschleunigung  Beschleunigung in m/s²
     */
    public Motorrad(String marke, String modell, int geschwindigkeit, int beschleunigung) {
        this.marke = marke;
        this.modell = modell;
        this.geschwindigkeit = geschwindigkeit;
        this.beschleunigung = beschleunigung;
    }

    /**
     * Gibt die Marke des Motorrads zurück.
     *
     * @return der Markenname
     */
    @Override
    public String getMarke() {
        return marke;
    }

    /**
     * Gibt das Modell des Motorrads zurück.
     *
     * @return der Modellname
     */
    @Override
    public String getModell() {
        return modell;
    }

    /**
     * Gibt die maximale Geschwindigkeit des Motorrads in km/h zurück.
     *
     * @return die Geschwindigkeit in km/h
     */
    @Override
    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    /**
     * Gibt die Beschleunigung des Motorrads in m/s² zurück.
     *
     * @return der Beschleunigungswert
     */
    @Override
    public int getBeschleunigung() {
        return beschleunigung;
    }
}
