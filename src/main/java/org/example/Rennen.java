package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Rennen {
    private Rennstrecke strecke;
    private Wetter wetter;
    private ArrayList<Fahrer> teilnehmer;
    private HashMap<Fahrer, Double> ergebnisse;

    public Rennen(Rennstrecke strecke, Wetter wetter) {
        this.strecke = strecke;
        this.wetter = wetter;
        this.teilnehmer = new ArrayList<>();
        this.ergebnisse = new HashMap<>();
    }

    public void addTeilnehmer(Fahrer fahrer) {
        teilnehmer.add(fahrer);
    }

    public void starteRennen(int runden) {
        for (Fahrer fahrer : teilnehmer) {
            double gesamtZeit = 0;
            for (int runde = 1; runde <= runden; runde++) {
                gesamtZeit += fahrer.fahreRunde(strecke, wetter);
            }
            ergebnisse.put(fahrer, gesamtZeit);
        }
    }

    public void zeigeStatistik() {
        System.out.println("Rennergebnisse:");
        ergebnisse.forEach((fahrer, zeit) -> {
            System.out.println(fahrer.getName() + " auf " + fahrer.getMotorradBeschreibung() + " - Zeit: " + zeit + " Sekunden");
        });
    }
}

