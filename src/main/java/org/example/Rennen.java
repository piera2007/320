package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Verwaltet das Anlegen der Teilnehmer (Land + Motorrad)
 * und ermittelt nach dem Start den Sieger.
 *
 * Achtung: Wir legen das Rennen an, fügen Teilnehmer hinzu,
 * aber starten es erst später -> so kann man vorher Wetten platzieren.
 */
public class Rennen {
    private Rennstrecke strecke;
    private boolean started; // wurde das Rennen bereits gestartet?

    // Hier speichern wir Paare (Land -> Motorrad)
    private List<Land> laender;
    private HashMap<Land, Motorrad> fahrzeuge;
    private HashMap<Land, Double> ergebnisse;

    public Rennen(Rennstrecke strecke) {
        this.strecke = strecke;
        this.laender = new ArrayList<>();
        this.fahrzeuge = new HashMap<>();
        this.ergebnisse = new HashMap<>();
        this.started = false;
    }

    /**
     * Fügt einen Teilnehmer (Land + Motorrad) hinzu,
     * solange das Rennen noch nicht gestartet ist.
     */
    public void addTeilnehmer(Land land, Motorrad m) {
        if (started) {
            throw new IllegalStateException("Rennen wurde bereits gestartet, kein Hinzufügen mehr möglich!");
        }
        laender.add(land);
        fahrzeuge.put(land, m);
    }

    /**
     * Startet das Rennen mit (runden) und berechnet
     * die Zeiten.
     *
     * Nach dem Start kann man keine neuen Teilnehmer hinzufügen.
     */
    public void starteRennen(int runden) {
        if (laender.size() < 2) {
            throw new IllegalArgumentException("Mindestens 2 Teilnehmer erforderlich!");
        }
        if (started) {
            throw new IllegalStateException("Rennen ist bereits gestartet!");
        }
        this.started = true;

        for (Land land : laender) {
            Motorrad m = fahrzeuge.get(land);
            double singleLapTime = berechneRundenzeit(land, m);
            double totalTime = singleLapTime * runden;
            ergebnisse.put(land, totalTime);
        }
    }

    /**
     * Formel:
     *  effectiveSpeed = (Motorrad.getGeschwindigkeit() + (Skill * 5))
     *  zeit pro runde = (strecke.getLaenge() / effectiveSpeed)
     *                  * (1 + (strecke.getSchwierigkeitsgrad() * 0.05))
     *                  * 3600
     */
    private double berechneRundenzeit(Land land, Motorrad moto) {
        double effectiveSpeed = moto.getGeschwindigkeit() + (land.getSkill() * 5);
        double basis = strecke.getLaenge() / effectiveSpeed;
        double schwFactor = 1 + (strecke.getSchwierigkeitsgrad() * 0.05);
        return basis * schwFactor * 3600;
    }

    /**
     * Gibt den Sieger (Land) zurück (kleinste Zeit).
     * Null, wenn keine Teilnehmer.
     */
    public Land getSieger() {
        Land bester = null;
        double besteZeit = Double.MAX_VALUE;
        for (Land land : ergebnisse.keySet()) {
            double zeit = ergebnisse.get(land);
            if (zeit < besteZeit) {
                besteZeit = zeit;
                bester = land;
            }
        }
        return bester;
    }

    /**
     * Liefert das verwendete Motorrad für ein Land.
     */
    public Motorrad getMotorrad(Land land) {
        return fahrzeuge.get(land);
    }

    /**
     * Gibt alle Teilnehmer (Land-Objekte) zurück.
     * Kann z. B. für das Wetten verwendet werden.
     */
    public List<Land> getTeilnehmer() {
        return laender;
    }

    /**
     * Prüft, ob das Rennen bereits gestartet wurde.
     */
    public boolean isStarted() {
        return started;
    }
}
