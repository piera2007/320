package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TouringMotorradTest {

    @Test
    void testConstructorAndGetters() {
        TouringMotorrad moto = new TouringMotorrad("Honda", "GoldWing", 200, 6);

        assertEquals("Honda", moto.getMarke());
        assertEquals("GoldWing", moto.getModell());
        assertEquals(200, moto.getGeschwindigkeit());
        assertEquals(6, moto.getBeschleunigung());
    }
}
