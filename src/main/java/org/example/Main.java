package org.example;

public class Main {
    public static void main(String[] args) {
        // Initialisierung des Statistikmanagers
        StatistikManager statistikManager = new StatistikManager();

        // Hinzufügen von Rennsimulationen
        for (int i = 1; i <= 5; i++) {
            Rennen rennen = TestdatenGenerator.erzeugeZufallsRennen();
            rennen.starteRennen(3);
            statistikManager.addErgebnis(rennen);
        }

        // Ausgabe der Statistik
        statistikManager.erzeugeStatistik();
    }
}

