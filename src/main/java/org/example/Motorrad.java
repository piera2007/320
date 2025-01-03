package org.example;

public abstract class Motorrad {
    private String marke;
    private String modell;
    private int geschwindigkeit; // In km/h
    private int beschleunigung; // In m/s²

    public Motorrad(String marke, String modell, int geschwindigkeit, int beschleunigung) {
        this.marke = marke;
        this.modell = modell;
        this.geschwindigkeit = geschwindigkeit;
        this.beschleunigung = beschleunigung;
    }

    public String getMarke() {
        return marke;
    }

    public String getModell() {
        return modell;
    }

    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public int getBeschleunigung() {
        return beschleunigung;
    }
}






