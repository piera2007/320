package org.example;

public class SportMotorrad extends Motorrad {
    public SportMotorrad(String marke, String modell, int maxGeschwindigkeit, int beschleunigung) {
        super(marke, modell, maxGeschwindigkeit, beschleunigung);
    }

    @Override
    public double fahreRunde(double streckenLaenge, Wetter wetter) {
        double zeit = streckenLaenge / (maxGeschwindigkeit * 0.8);
        return zeit * (1 + wetter.getWetterEinfluss());
    }
}

