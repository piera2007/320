package org.example;

import java.util.Random;

public class TestdatenGenerator {
    private static final Random random = new Random();

    public static Rennen erzeugeZufallsRennen() {
        Rennstrecke strecke = new Rennstrecke("Strecke " + (random.nextInt(5) + 1), 2.5 + random.nextDouble() * 3, random.nextInt(10) + 1);
        Wetter wetter = new Wetter(random.nextInt(35), random.nextInt(20), random.nextInt(10));

        Rennen rennen = new Rennen(strecke, wetter);

        for (int i = 1; i <= 3; i++) {
            Motorrad motorrad = random.nextBoolean()
                    ? new SportMotorrad("Marke" + i, "Modell" + i, random.nextInt(300) + 100, random.nextInt(10) + 5)
                    : new TouringMotorrad("Marke" + i, "Modell" + i, random.nextInt(200) + 100, random.nextInt(5) + 2);

            Fahrer fahrer = new Fahrer("Fahrer " + i, random.nextInt(10), random.nextInt(10) + 1, motorrad);
            rennen.addTeilnehmer(fahrer);
        }

        return rennen;
    }
}

