package org.example;

public class TouringMotorrad extends Motorrad {
    public TouringMotorrad(String marke, String modell, int maxGeschwindigkeit, int beschleunigung) {
        super(marke, modell, maxGeschwindigkeit, beschleunigung);
    }

    @Override
    public double fahreRunde(double streckenLaenge, Wetter wetter) {
        double zeit = streckenLaenge / (maxGeschwindigkeit * 0.6);
        return zeit * (1 + wetter.getWetterEinfluss());
    }
}
