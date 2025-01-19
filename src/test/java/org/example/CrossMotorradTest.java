package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet die Klasse {@link CrossMotorrad}.
 */
class CrossMotorradTest {

    @Test
    void testConstructorAndGetters() {
        CrossMotorrad moto = new CrossMotorrad("KTM", "Enduro", 180, 8);

        assertEquals("KTM", moto.getMarke());
        assertEquals("Enduro", moto.getModell());
        assertEquals(180, moto.getGeschwindigkeit());
        assertEquals(8, moto.getBeschleunigung());
    }
}
