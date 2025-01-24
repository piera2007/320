package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class LandTest {

    @Test
    void testConstructorAndGetName() {
        Land land = new Land("Schweiz");

        assertEquals("Schweiz", land.getName());
    }
}
