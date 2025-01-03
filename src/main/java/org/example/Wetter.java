package org.example;

public class Wetter {
    private int temperatur; // In Grad Celsius
    private int niederschlag; // In mm
    private int windgeschwindigkeit; // In km/h

    public Wetter(int temperatur, int niederschlag, int windgeschwindigkeit) {
        this.temperatur = temperatur;
        this.niederschlag = niederschlag;
        this.windgeschwindigkeit = windgeschwindigkeit;
    }

    public double berechneEinfluss() {
        double regenEffekt = 1 + (niederschlag * 0.01);
        double windEffekt = 1 + (windgeschwindigkeit * 0.005);
        return regenEffekt * windEffekt;
    }
}


