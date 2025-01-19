package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet die Klasse {@link MotorradListe}.
 */
class MotorradListeTest {

    @Test
    void testGetVerfuegbareFuerStreckeEasy() {
        Motorrad[] result = MotorradListe.getVerfuegbareFuerStrecke(2); // easy
        assertEquals(2, result.length, "Bei Easy sollten 2 Motorräder zurückkommen");
        // Evtl. genauer prüfen, ob Sport und Touring
    }

    @Test
    void testGetVerfuegbareFuerStreckeMedium() {
        Motorrad[] result = MotorradListe.getVerfuegbareFuerStrecke(5); // medium
        assertEquals(3, result.length, "Bei Medium sollten 3 Motorräder zurückkommen");
    }

    @Test
    void testGetVerfuegbareFuerStreckeHard() {
        Motorrad[] result = MotorradListe.getVerfuegbareFuerStrecke(9); // hard
        assertEquals(2, result.length, "Bei Hard sollten 2 Motorräder zurückkommen");
    }
}
