package org.example;

/**
 * Repräsentiert eine Wette auf einen Fahrer.
 * <p>
 * Enthält den Fahrernamen und den Einsatzbetrag.
 * Wird in {@link BetManager} verwaltet.
 *
 * @author Piera Blum
 * @version 24.01.2025
 */
public class Bet {

    private String fahrerName;
    private double einsatz;

    /**
     * Erzeugt ein {@code Bet}-Objekt.
     *
     * @param fahrerName Name des Fahrers, auf den gewettet wird.
     * @param einsatz    Geldbetrag (z. B. 10.0).
     */
    public Bet(String fahrerName, double einsatz) {
        this.fahrerName = fahrerName;
        this.einsatz = einsatz;
    }

    /**
     * @return Name des Fahrers
     */
    public String getFahrerName() {
        return fahrerName;
    }

    /**
     * @return Einsatz in Geldeinheiten
     */
    public double getEinsatz() {
        return einsatz;
    }
}
