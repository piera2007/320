package org.example;

public class Main {
    public static void main(String[] args) {
        // Erstellen von Motorrädern
        Motorrad sportBike = new SportMotorrad("Yamaha", "R1", 320, 10);
        Motorrad touringBike = new TouringMotorrad("Honda", "Goldwing", 180, 5);

        // Erstellen von Fahrern
        Fahrer fahrer1 = new Fahrer("Max", 5, 8, sportBike);
        Fahrer fahrer2 = new Fahrer("Lisa", 3, 7, touringBike);

        // Rennstrecke und Wetter
        Rennstrecke strecke = new Rennstrecke("Monaco", 3.2, 8);
        Wetter wetter = new Wetter(22, 0, 5);

        // Rennen
        Rennen rennen = new Rennen(strecke, wetter);
        rennen.addTeilnehmer(fahrer1);
        rennen.addTeilnehmer(fahrer2);

        // Rennen starten
        rennen.starteRennen(3);

        // Statistik anzeigen
        rennen.zeigeStatistik();
    }
}
