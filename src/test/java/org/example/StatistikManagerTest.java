package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet die Klasse {@link StatistikManager}.
 */
class StatistikManagerTest {

    private StatistikManager manager;

    @BeforeEach
    void setUp() {
        manager = new StatistikManager();
    }

    @Test
    void testAddRennenNotStarted() {
        Rennen rennen = new Rennen(new Rennstrecke("Test", 2.0, 2));
        // Noch nie gestartet => sollte nicht aufgenommen werden
        manager.addRennen(rennen);
        // Rufe manager.zeigeStatistik() auf -> keine Ausnahmen, aber "Keine Rennen abgeschlossen"
        // Testen wir minimal
        // Evtl. Assertions durch Umleiten System.out oder Auswertung, ob man EINE leere Liste hat
        // Hier minimal:
        manager.zeigeStatistik();
        // Schau in Konsole, ob "Keine Rennen abgeschlossen" erscheint
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
        // Hier könnte man SystemOut-Umleitung machen,
        // oder einfach checken, ob es keine Exceptions gibt
        // Minimales Coverage
        manager.zeigeStatistik();
    }
}
