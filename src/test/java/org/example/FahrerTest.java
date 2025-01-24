package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FahrerTest {

    @Test
    void testConstructorAndGetters() {
        Fahrer fahrer = new Fahrer("Max", "Schweiz", 5);

        assertEquals("Max", fahrer.getFahrerName());
        assertEquals("Schweiz", fahrer.getLand());
        assertEquals(5, fahrer.getErfahrung());
    }
}
