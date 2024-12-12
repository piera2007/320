package org.example;

public class Wetter {
    private int temperatur; // In °C
    private int niederschlag; // In mm
    private int windgeschwindigkeit; // In km/h

    public Wetter(int temperatur, int niederschlag, int windgeschwindigkeit) {
        this.temperatur = temperatur;
        this.niederschlag = niederschlag;
        this.windgeschwindigkeit = windgeschwindigkeit;
    }

    public double getWetterEinfluss() {
        return (niederschlag * 0.01) + (windgeschwindigkeit * 0.005);
    }
}

