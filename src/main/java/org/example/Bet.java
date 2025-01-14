package org.example;

/**
 * Repräsentiert eine Wette auf ein bestimmtes Land
 * in EINEM bestimmten Rennen.
 * Wir haben z. B. Einsatz = Geld, hier optional.
 */
public class Bet {
    private String landName;
    private double einsatz; // optional

    public Bet(String landName, double einsatz) {
        this.landName = landName;
        this.einsatz = einsatz;
    }

    public String getLandName() {
        return landName;
    }

    public double getEinsatz() {
        return einsatz;
    }
}
