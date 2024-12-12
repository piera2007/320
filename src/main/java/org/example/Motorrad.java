package org.example;

public abstract class Motorrad {
    protected String marke;
    protected String modell;
    protected int maxGeschwindigkeit;
    protected int beschleunigung;

    public Motorrad(String marke, String modell, int maxGeschwindigkeit, int beschleunigung) {
        this.marke = marke;
        this.modell = modell;
        this.maxGeschwindigkeit = maxGeschwindigkeit;
        this.beschleunigung = beschleunigung;
    }

    public abstract double fahreRunde(double streckenLaenge, Wetter wetter);

    public String getBeschreibung() {
        return marke + " " + modell;
    }
}

