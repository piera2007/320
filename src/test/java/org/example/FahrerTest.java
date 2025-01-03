import org.example.Fahrer;
import org.example.Motorrad;
import org.example.Rennstrecke;
import org.example.SportMotorrad;
import org.example.Wetter;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FahrerTest {

    @Test
    void berechneRundenzeit_shouldReturnCorrectTime() {
        Motorrad sportMotorrad = new SportMotorrad("Yamaha", "R1", 300, 10);
        Fahrer fahrer = new Fahrer("Max", 5, 8, sportMotorrad);
        Rennstrecke strecke = new Rennstrecke("Interlagos", 5.0, 7);
        Wetter wetter = new Wetter(20, 10, 5);

        double rundenzeit = fahrer.berechneRundenzeit(strecke, wetter);
        assertTrue(rundenzeit > 0, "Rundenzeit sollte positiv sein.");
    }
}
