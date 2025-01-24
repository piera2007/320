package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Ein Rennen mit mind. 2 Fahrern.
 * Bei Zeitgleichheit → Unentschieden (Sieger = null).
 * <p>
 *  * @author Piera Blum
 *  * @version 24.01.2025
 */
public class Rennen {
    private Rennstrecke strecke;
    private boolean started;
    private List<Fahrer> fahrerListe;
    private HashMap<Fahrer, Motorrad> fahrerZuMoto;
    private HashMap<Fahrer, Double> ergebnisse;

    /**
     * Konstruktor
     *
     * @param strecke Die Rennstrecke
     */
    public Rennen(Rennstrecke strecke) {
        this.strecke = strecke;
        this.started = false;
        this.fahrerListe = new ArrayList<>();
        this.fahrerZuMoto = new HashMap<>();
        this.ergebnisse = new HashMap<>();
    }

    /**
     * Fügt einen Teilnehmer (Fahrer + Motorrad) hinzu.
     */
    public void addTeilnehmer(Fahrer fahrer, Motorrad moto) {
        if (started) {
            throw new IllegalStateException("Rennen ist schon gestartet!");
        }
        fahrerListe.add(fahrer);
        fahrerZuMoto.put(fahrer, moto);
    }

    /**
     * Startet das Rennen.
     *
     * @param runden Anzahl Runden (mind 1)
     */
    public void starteRennen(int runden) {
        if (fahrerListe.size() < 2) {
            throw new IllegalArgumentException("Mindestens 2 Teilnehmer notwendig!");
        }
        if (started) {
            throw new IllegalStateException("Rennen bereits gestartet!");
        }
        started = true;

        for (Fahrer f : fahrerListe) {
            Motorrad m = fahrerZuMoto.get(f);
            double timeOne = berechneRundenzeit(f, m);
            ergebnisse.put(f, timeOne * runden);
        }
    }

    /**
     * Formel: effectiveSpeed = bikeSpeed + (erfahrung * 3)
     * Zeit = (streckenlänge / effectiveSpeed)
     *       * (1 + schwgrad * 0.05)
     *       * 3600
     */
    private double berechneRundenzeit(Fahrer f, Motorrad m) {
        double effSpeed = m.getGeschwindigkeit() + (f.getErfahrung() * 3);
        double basis = strecke.getLaenge() / effSpeed;
        double schwFactor = 1 + (strecke.getSchwierigkeitsgrad() * 0.05);
        return basis * schwFactor * 3600;
    }

    /**
     * Eindeutiger Sieger oder null bei Unentschieden
     */
    public Fahrer getSieger() {
        if (ergebnisse.isEmpty()) {
            return null;
        }
        double bestTime = Double.MAX_VALUE;
        for (Fahrer f : ergebnisse.keySet()) {
            double t = ergebnisse.get(f);
            if (t < bestTime) {
                bestTime = t;
            }
        }
        List<Fahrer> winners = new ArrayList<>();
        for (Fahrer f : ergebnisse.keySet()) {
            if (Math.abs(ergebnisse.get(f) - bestTime) < 0.000001) {
                winners.add(f);
            }
        }
        if (winners.size() == 1) return winners.get(0);
        return null;
    }

    public boolean isStarted() {
        return started;
    }

    public List<Fahrer> getFahrerListe() {
        return fahrerListe;
    }

    public Motorrad getMotorrad(Fahrer f) {
        return fahrerZuMoto.get(f);
    }

    /**
     * @return ergebnisse HashMap<Fahrer, Double> mit finalen Zeiten
     */
    public HashMap<Fahrer, Double> getErgebnisse() {
        return ergebnisse;
    }

    /**
     * @return Die verwendete Rennstrecke
     */
    public Rennstrecke getStrecke() {
        return strecke;
    }
}
