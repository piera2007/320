package org.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class RennenTest {

    @Test
    void starteRennen_shouldCalculateResults() {
        Rennstrecke strecke = new Rennstrecke("Monza", 3.0, 5);
        Wetter wetter = new Wetter(25, 0, 3);
        Rennen rennen = new Rennen(strecke, wetter);

        Motorrad sportMotorrad = new SportMotorrad("Ducati", "Panigale", 320, 10);
        Fahrer fahrer = new Fahrer("Alice", 7, 9, sportMotorrad);

        rennen.addTeilnehmer(fahrer);
        rennen.starteRennen(5);

        HashMap<Fahrer, Double> ergebnisse = rennen.getErgebnisse();
        assertNotNull(ergebnisse.get(fahrer), "Die Rundenzeit des Fahrers sollte berechnet werden.");
    }

    @Test
    void getGewinner_shouldReturnBestDriver() {
        Rennstrecke strecke = new Rennstrecke("Silverstone", 5.9, 8);
        Wetter wetter = new Wetter(15, 5, 10);
        Rennen rennen = new Rennen(strecke, wetter);

        Motorrad sportMotorrad = new SportMotorrad("Yamaha", "R6", 280, 8);
        Motorrad touringMotorrad = new TouringMotorrad("Kawasaki", "Versys", 200, 6);

        Fahrer fahrer1 = new Fahrer("Fahrer1", 5, 9, sportMotorrad);
        Fahrer fahrer2 = new Fahrer("Fahrer2", 7, 8, touringMotorrad);

        rennen.addTeilnehmer(fahrer1);
        rennen.addTeilnehmer(fahrer2);
        rennen.starteRennen(3);

        Fahrer gewinner = rennen.getGewinner();
        assertNotNull(gewinner, "Es sollte einen Gewinner geben.");
        assertTrue(rennen.getErgebnisse().containsKey(gewinner), "Der Gewinner sollte in den Ergebnissen sein.");
    }
}

