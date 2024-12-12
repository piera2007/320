package org.example;

public class Rennstrecke {
    private String name;
    private double laenge; // In Kilometern
    private int schwierigkeitsgrad; // Skala 1-10

    public Rennstrecke(String name, double laenge, int schwierigkeitsgrad) {
        this.name = name;
        this.laenge = laenge;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public double getLaenge() {
        return laenge;
    }
}

