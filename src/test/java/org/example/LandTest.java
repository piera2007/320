package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet die Klasse {@link Land}.
 */
class LandTest {

    @Test
    void testConstructorAndGetName() {
        Land land = new Land("Schweiz", 9); // skill ignoriert
        assertEquals("Schweiz", land.getName());
    }
}
