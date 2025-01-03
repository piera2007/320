package org.example;

public class Rennstrecke {
    private String name;
    private double laenge; // In km
    private int schwierigkeit; // Skala 1-10

    public Rennstrecke(String name, double laenge, int schwierigkeit) {
        this.name = name;
        this.laenge = laenge;
        this.schwierigkeit = schwierigkeit;
    }

    public double getLaenge() {
        return laenge;
    }

    public int getSchwierigkeit() {
        return schwierigkeit;
    }

    public String getName() {
        return name;
    }
}


