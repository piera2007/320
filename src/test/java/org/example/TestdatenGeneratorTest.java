package org.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TestdatenGeneratorTest {

    @Test
    void erzeugeZufallsRennen_shouldCreateValidRace() {
        Rennen rennen = TestdatenGenerator.erzeugeZufallsRennen();
        assertNotNull(rennen, "Das generierte Rennen sollte nicht null sein.");
        assertTrue(rennen.getErgebnisse().isEmpty(), "Das Rennen sollte Teilnehmer haben.");
    }
}
