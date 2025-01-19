package org.example;

/**
 * Repräsentiert einen Fahrer, der an einem Rennen teilnimmt.
 * <p>
 * Enthält:
 * - Name des Fahrers
 * - Land (String) oder Länderkürzel
 * - Erfahrung in Jahren
 *
 * So kann man mehrere Fahrer aus demselben Land haben,
 * aber unterschiedliche Namen und Erfahrung.
 *
 * @author Piera Blum
 * @version 1.0
 */
public class Fahrer {
    private String fahrerName;
    private String land;      // z. B. "Schweiz"
    private int erfahrung;    // Jahre Erfahrung

    /**
     * Konstruktor
     *
     * @param fahrerName Name des Fahrers
     * @param land Das Land, z. B. "Schweiz"
     * @param erfahrung Jahre Erfahrung als Biker
     */
    public Fahrer(String fahrerName, String land, int erfahrung) {
        this.fahrerName = fahrerName;
        this.land = land;
        this.erfahrung = erfahrung;
    }

    /**
     * @return fahrerName
     */
    public String getFahrerName() {
        return fahrerName;
    }

    /**
     * @return land
     */
    public String getLand() {
        return land;
    }

    /**
     * @return erfahrung
     */
    public int getErfahrung() {
        return erfahrung;
    }
}
