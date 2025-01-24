package org.example;

import java.util.HashMap;

/**
 * Hilfsklasse, die die finalen Resultate eines Rennens speichert.
 * So kann der StatistikManager später Details ausgeben.
 * <p>
 *  * @author Piera Blum
 *  * @version 24.01.2025
 */
public class Renndaten {

    private Rennen rennen;
    private HashMap<Fahrer, Double> zeiten;
    private Fahrer sieger;

    /**
     * Konstruktor
     *
     * @param rennen Referenz auf das beendete Rennen
     */
    public Renndaten(Rennen rennen) {
        this.rennen = rennen;
        this.zeiten = new HashMap<>(rennen.getErgebnisse());
        this.sieger = rennen.getSieger();
    }

    /**
     * @return zeiten die HashMap (Fahrer -> Zeit)
     */
    public HashMap<Fahrer, Double> getZeiten() {
        return zeiten;
    }

    /**
     * @return sieger der Sieger (null bei Unentschieden)
     */
    public Fahrer getSieger() {
        return sieger;
    }

    /**
     * @return rennen Referenz auf das original Rennen
     */
    public Rennen getRennen() {
        return rennen;
    }
}
