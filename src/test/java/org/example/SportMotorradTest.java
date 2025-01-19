package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet die Klasse {@link SportMotorrad}.
 */
class SportMotorradTest {

    @Test
    void testConstructorAndGetters() {
        SportMotorrad moto = new SportMotorrad("Yamaha", "R1", 260, 12);

        assertEquals("Yamaha", moto.getMarke());
        assertEquals("R1", moto.getModell());
        assertEquals(260, moto.getGeschwindigkeit());
        assertEquals(12, moto.getBeschleunigung());
    }
}
