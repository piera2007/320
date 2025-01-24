package org.example;

/**
 * Repräsentiert einen Fahrer mit Name, Land und Erfahrung.
 *
 * Mehrere Fahrer können dasselbe Land haben,
 * aber unterschiedliche Namen/Erfahrungen.
 *
 * @author Piera Blum
 * @version 23.01.2025
 */
public class Fahrer {
    private String fahrerName;
    private String land;
    private int erfahrung;

    /**
     * Erzeugt einen {@code Fahrer}.
     *
     * @param fahrerName Name des Fahrers
     * @param land       Land (z. B. "Schweiz")
     * @param erfahrung  Jahre Fahrerfahrung
     */
    public Fahrer(String fahrerName, String land, int erfahrung) {
        this.fahrerName = fahrerName;
        this.land = land;
        this.erfahrung = erfahrung;
    }

    /**
     * @return Name des Fahrers
     */
    public String getFahrerName() {
        return fahrerName;
    }

    /**
     * @return Land (String)
     */
    public String getLand() {
        return land;
    }

    /**
     * @return Fahrerfahrung (Jahre)
     */
    public int getErfahrung() {
        return erfahrung;
    }
}
