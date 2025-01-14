package org.example;

/**
 * Liste vordefinierter Motorräder (Sport, Cross, Touring).
 */
public class MotorradListe {
    public static final Motorrad SPORT = new SportMotorrad("Yamaha", "R1", 260, 12);
    public static final Motorrad CROSS = new CrossMotorrad("KTM", "Enduro", 180, 8);
    public static final Motorrad TOURING = new TouringMotorrad("Honda", "GoldWing", 200, 6);

    public static Motorrad[] getAlleMotorradTypen() {
        return new Motorrad[]{SPORT, CROSS, TOURING};
    }
}
