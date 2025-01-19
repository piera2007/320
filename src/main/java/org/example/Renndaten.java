package org.example;

import java.util.HashMap;

/**
 * Hilfsklasse, die die finalen Resultate eines Rennens speichert.
 * So kann der StatistikManager später Details ausgeben.
 */
public class Renndaten {

    private Rennen rennen;
    // Original-Objekt, oder wir kopieren nur die Werte
    // Hier zeige ich, wie wir es direkt speichern können

    private HashMap<Fahrer, Double> zeiten;
    private Fahrer sieger;

    /**
     * Konstruktor
     *
     * @param rennen Referenz auf das beendete Rennen
     */
    public Renndaten(Rennen rennen) {
        this.rennen = rennen;
        // Kopie der Zeiten
        this.zeiten = new HashMap<>(rennen.getErgebnisse());
        this.sieger = rennen.getSieger();
    }

    /**
     * @return Die HashMap (Fahrer -> Zeit)
     */
    public HashMap<Fahrer, Double> getZeiten() {
        return zeiten;
    }

    /**
     * @return Der Sieger (null bei Unentschieden)
     */
    public Fahrer getSieger() {
        return sieger;
    }

    /**
     * @return Referenz auf das original Rennen
     */
    public Rennen getRennen() {
        return rennen;
    }
}
