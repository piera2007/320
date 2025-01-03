package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MotorradTest {

    @Test
    void sportMotorrad_shouldHaveCorrectAttributes() {
        Motorrad motorrad = new SportMotorrad("Honda", "CBR", 290, 9);

        assertEquals("Honda", motorrad.getMarke());
        assertEquals("CBR", motorrad.getModell());
        assertEquals(290, motorrad.getGeschwindigkeit());
        assertEquals(9, motorrad.getBeschleunigung());
    }

    @Test
    void touringMotorrad_shouldHaveCorrectAttributes() {
        Motorrad motorrad = new TouringMotorrad("BMW", "R1250", 200, 5);

        assertEquals("BMW", motorrad.getMarke());
        assertEquals("R1250", motorrad.getModell());
        assertEquals(200, motorrad.getGeschwindigkeit());
        assertEquals(5, motorrad.getBeschleunigung());
    }
}
