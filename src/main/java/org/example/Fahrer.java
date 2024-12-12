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

    public double fahreRunde(Rennstrecke strecke, Wetter wetter) {
        double basisZeit = motorrad.fahreRunde(strecke.getLaenge(), wetter);
        double erfahrungsBonus = 1 - (erfahrung * 0.05);
        double konditionsMalus = 1 + (10 - kondition) * 0.02;
        return basisZeit * erfahrungsBonus * konditionsMalus;
    }

    public String getName() {
        return name;
    }

    public String getMotorradBeschreibung() {
        return motorrad.getBeschreibung();
    }
}

