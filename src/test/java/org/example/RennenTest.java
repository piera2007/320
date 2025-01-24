package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RennenTest {

    @Test
    void testAddTeilnehmerAndStartRennen() {
        Rennen rennen = new Rennen(new Rennstrecke("Test", 2.0, 2));
        Fahrer f1 = new Fahrer("Max", "CH", 5);
        Fahrer f2 = new Fahrer("Lisa", "CH", 3);

        assertDoesNotThrow(() -> rennen.addTeilnehmer(f1, new SportMotorrad("Yamaha", "R1", 260, 12)));
        assertDoesNotThrow(() -> rennen.addTeilnehmer(f2, new SportMotorrad("Yamaha", "R1", 260, 12)));

        assertFalse(rennen.isStarted(), "Rennen sollte noch nicht gestartet sein");

        assertDoesNotThrow(() -> rennen.starteRennen(1));
        assertTrue(rennen.isStarted());

        assertNotNull(rennen.getSieger());
    }

    @Test
    void testStartRennenWithTooFewFahrer() {
        Rennen rennen = new Rennen(new Rennstrecke("Test", 2.0, 2));
        Fahrer f1 = new Fahrer("Max", "CH", 5);
        rennen.addTeilnehmer(f1, new SportMotorrad("Yamaha", "R1", 260, 12));

        assertThrows(IllegalArgumentException.class, () -> rennen.starteRennen(1));
    }

    @Test
    void testStartRennenTwice() {
        Rennen rennen = new Rennen(new Rennstrecke("Test", 2.0, 2));
        Fahrer f1 = new Fahrer("Max", "CH", 5);
        Fahrer f2 = new Fahrer("Lisa", "CH", 3);
        rennen.addTeilnehmer(f1, new SportMotorrad("Yamaha", "R1", 260, 12));
        rennen.addTeilnehmer(f2, new SportMotorrad("Yamaha", "R1", 260, 12));
        rennen.starteRennen(1);

        assertThrows(IllegalStateException.class, () -> rennen.starteRennen(1));
    }
}
