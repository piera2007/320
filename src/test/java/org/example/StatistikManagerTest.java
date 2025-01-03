package org.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

class StatistikManagerTest {

    @Test
    void addErgebnis_shouldStoreRaceResults() {
        StatistikManager statistikManager = new StatistikManager();
        Rennen rennen = new Rennen(
                new Rennstrecke("Hockenheim", 4.5, 6),
                new Wetter(18, 5, 7)
        );

        Motorrad motorrad = new SportMotorrad("Suzuki", "GSX-R", 290, 9);
        Fahrer fahrer = new Fahrer("Tom", 10, 10, motorrad);
        rennen.addTeilnehmer(fahrer);
        rennen.starteRennen(3);

        statistikManager.addErgebnis(rennen);

        assertDoesNotThrow(() -> statistikManager.erzeugeStatistik(), "Statistik sollte ohne Fehler erzeugt werden.");
    }
}

