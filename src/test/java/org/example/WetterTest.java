package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
class WetterTest {

    @Test
    void berechneEinfluss_shouldReturnCorrectMultiplier() {
        Wetter wetter = new Wetter(20, 10, 5);

        double einfluss = wetter.berechneEinfluss();
        assertTrue(einfluss > 1, "Der Wettereinfluss sollte größer als 1 sein.");
    }
}

