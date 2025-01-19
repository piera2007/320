package org.example;

/**
 * Repräsentiert eine Wette auf einen Fahrer.
 *
 * Speichert den Namen des Fahrers und den Einsatz.
 *
 * @author
 * @version 1.0
 */
public class Bet {
    private String fahrerName;
    private double einsatz;

    /**
     * Konstruktor
     *
     * @param fahrerName Fahrername, auf den gewettet wird
     * @param einsatz Geldbetrag (z. B. 10.0)
     */
    public Bet(String fahrerName, double einsatz) {
        this.fahrerName = fahrerName;
        this.einsatz = einsatz;
    }

    /**
     * @return Fahrername
     */
    public String getFahrerName() {
        return fahrerName;
    }

    /**
     * @return Einsatz
     */
    public double getEinsatz() {
        return einsatz;
    }
}
