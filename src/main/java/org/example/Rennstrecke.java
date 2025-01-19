package org.example;

/**
 * Repräsentiert eine Rennstrecke mit Name, Länge (km)
 * und Schwierigkeitsgrad (1-10).
 *
 * @author
 * @version 1.0
 */
public class Rennstrecke {
    private String name;
    private double laenge;
    private int schwierigkeitsgrad;

    /**
     * Konstruktor
     *
     * @param name Name der Strecke
     * @param laenge Länge in km
     * @param schwierigkeitsgrad 1-10
     */
    public Rennstrecke(String name, double laenge, int schwierigkeitsgrad) {
        this.name = name;
        this.laenge = laenge;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    /**
     * @return Name der Rennstrecke
     */
    public String getName() {
        return name;
    }

    /**
     * @return Länge in km
     */
    public double getLaenge() {
        return laenge;
    }

    /**
     * @return Schwierigkeitsgrad (1-10)
     */
    public int getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }
}
