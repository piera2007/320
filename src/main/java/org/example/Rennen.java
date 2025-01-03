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
            double gesamtzeit = 0;
            for (int runde = 1; runde <= runden; runde++) {
                gesamtzeit += fahrer.berechneRundenzeit(strecke, wetter);
            }
            ergebnisse.put(fahrer, gesamtzeit);
        }
    }

    public Fahrer getGewinner() {
        Fahrer besterFahrer = null;
        double besteZeit = Double.MAX_VALUE;

        for (Fahrer fahrer : ergebnisse.keySet()) {
            double zeit = ergebnisse.get(fahrer);
            if (zeit < besteZeit) {
                besteZeit = zeit;
                besterFahrer = fahrer;
            }
        }

        return besterFahrer;
    }

    public HashMap<Fahrer, Double> getErgebnisse() {
        return ergebnisse;
    }
}


