package org.example;

public class Fahrer {
    private String name;
    private int erfahrung; // In Jahren
    private int kondition; // Skala 1-10
    private Motorrad motorrad;

    public Fahrer(String name, int erfahrung, int kondition, Motorrad motorrad) {
        this.name = name;
        this.erfahrung = erfahrung;
        this.kondition = kondition;
        this.motorrad = motorrad;
    }

    public String getName() {
        return name;
    }

    public Motorrad getMotorrad() {
        return motorrad;
    }

    public double berechneRundenzeit(Rennstrecke strecke, Wetter wetter) {
        double basisZeit = strecke.getLaenge() / motorrad.getGeschwindigkeit();
        double schwierigkeit = 1 + strecke.getSchwierigkeit() * 0.1;
        double wetterEffekt = wetter.berechneEinfluss();
        double erfahrungsBonus = 1 - (erfahrung * 0.02);
        double konditionPenalty = 1 + (10 - kondition) * 0.05;

        return basisZeit * schwierigkeit * wetterEffekt * erfahrungsBonus * konditionPenalty;
    }
}


