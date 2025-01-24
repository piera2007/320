package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetTest {

    @Test
    void testConstructorAndGetters() {
        Bet bet = new Bet("Max", 10.0);

        assertEquals("Max", bet.getFahrerName(), "Fahrername sollte 'Max' sein");
        assertEquals(10.0, bet.getEinsatz(), 0.0001, "Einsatz sollte 10.0 sein");
    }
}
