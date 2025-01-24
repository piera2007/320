package org.example;

/**
 * Repräsentiert eine Rennstrecke mit Name, Länge (km)
 * und Schwierigkeitsgrad (1-10).
 *
 * @author Piera Blum
 * @version 24.01.2025
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
     * @return name der Rennstrecke
     */
    public String getName() {
        return name;
    }

    /**
     * @return laenge Länge in km
     */
    public double getLaenge() {
        return laenge;
    }

    /**
     * @return schwierigkeitsgrad (1-10)
     */
    public int getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }
}
