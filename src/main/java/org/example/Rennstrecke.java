package org.example;

public class Rennstrecke {
    private String name;
    private double laenge; // km
    private int schwierigkeitsgrad; // 1-10

    public Rennstrecke(String name, double laenge, int schwierigkeitsgrad) {
        this.name = name;
        this.laenge = laenge;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public String getName() {
        return name;
    }

    public double getLaenge() {
        return laenge;
    }

    public int getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }
}
