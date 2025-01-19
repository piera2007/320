package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Ein Rennen mit mind. 2 Fahrern.
 * Bei Zeitgleichheit => Unentschieden (Sieger = null).
 */
public class Rennen {
    private Rennstrecke strecke;
    private boolean started;

    private List<Fahrer> fahrerListe;
    private HashMap<Fahrer, Motorrad> fahrerZuMoto;
    private HashMap<Fahrer, Double> ergebnisse;

    public Rennen(Rennstrecke strecke) {
        this.strecke = strecke;
        this.started = false;
        this.fahrerListe = new ArrayList<>();
        this.fahrerZuMoto = new HashMap<>();
        this.ergebnisse = new HashMap<>();
    }

    public void addTeilnehmer(Fahrer fahrer, Motorrad moto) {
        if (started) {
            throw new IllegalStateException("Rennen ist schon gestartet!");
        }
        fahrerListe.add(fahrer);
        fahrerZuMoto.put(fahrer, moto);
    }

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
            double tOne = berechneRundenzeit(f, m);
            ergebnisse.put(f, tOne * runden);
        }
    }

    /**
     * Beispiel-Formel:
     * effectiveSpeed = m.getGeschwindigkeit() + (fahrer.erfahrung * 3)
     * zeit = (streckeLaenge / effectiveSpeed) * (1 + (streckeSchw*0.05)) * 3600
     */
    private double berechneRundenzeit(Fahrer f, Motorrad m) {
        double effSpeed = m.getGeschwindigkeit() + (f.getErfahrung() * 3);
        double basis = strecke.getLaenge() / effSpeed;
        double schwFactor = 1 + (strecke.getSchwierigkeitsgrad() * 0.05);
        return basis * schwFactor * 3600;
    }

    /**
     * Liefert den eindeutigen Sieger oder null bei Unentschieden.
     */
    public Fahrer getSieger() {
        if (ergebnisse.isEmpty()) {
            return null;
        }
        // Beste Zeit herausfinden
        double bestTime = Double.MAX_VALUE;
        for (Fahrer f : ergebnisse.keySet()) {
            double t = ergebnisse.get(f);
            if (t < bestTime) {
                bestTime = t;
            }
        }
        // Nun schauen, wie viele Fahrer diese bestTime haben
        List<Fahrer> winners = new ArrayList<>();
        for (Fahrer f : ergebnisse.keySet()) {
            if (Math.abs(ergebnisse.get(f) - bestTime) < 0.000001) {
                // Annahme: "exakt" gleich => floating-Epsilon
                winners.add(f);
            }
        }
        if (winners.size() == 1) {
            return winners.get(0); // eindeutiger Sieger
        }
        return null; // Unentschieden
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
}
