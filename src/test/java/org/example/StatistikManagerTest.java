package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatistikManagerTest {

    private StatistikManager manager;

    @BeforeEach
    void setUp() {
        manager = new StatistikManager();
    }

    @Test
    void testAddRennenNotStarted() {
        Rennen rennen = new Rennen(new Rennstrecke("Test", 2.0, 2));
        manager.addRennen(rennen);
        manager.zeigeStatistik();
    }

    @Test
    void testAddRennenStarted() {
        Rennen rennen = new Rennen(new Rennstrecke("Test", 2.0, 2));
        Fahrer f1 = new Fahrer("Max", "CH", 5);
        Fahrer f2 = new Fahrer("Lisa", "CH", 3);
        rennen.addTeilnehmer(f1, new SportMotorrad("Yamaha", "R1", 260, 12));
        rennen.addTeilnehmer(f2, new SportMotorrad("Yamaha", "R1", 260, 12));
        rennen.starteRennen(1);

        manager.addRennen(rennen);
        manager.zeigeStatistik();
    }
}
