package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class RenndatenTest {

    @Test
    void testConstructorAndGetters() {
        Rennen rennen = new Rennen(new Rennstrecke("TestStrecke", 2.0, 2));
        Fahrer f1 = new Fahrer("Max", "CH", 5);
        Fahrer f2 = new Fahrer("Lisa", "CH", 5);
        rennen.addTeilnehmer(f1, new SportMotorrad("Yamaha","R1",260,12));
        rennen.addTeilnehmer(f2, new SportMotorrad("Yamaha","R1",260,12));
        rennen.starteRennen(1);

        Renndaten rd = new Renndaten(rennen);

        assertNotNull(rd.getZeiten());
        assertEquals(rennen, rd.getRennen());
    }
}
