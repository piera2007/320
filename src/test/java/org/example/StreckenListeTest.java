package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StreckenListeTest {

    @Test
    void testGetAlleStrecken() {
        Rennstrecke[] arr = StreckenListe.getAlleStrecken();
        assertEquals(3, arr.length, "Es sollten 3 vordefinierte Strecken geben");
    }
}
