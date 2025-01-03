package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class StatistikManager {
    private ArrayList<Rennen> rennenListe;

    public StatistikManager() {
        rennenListe = new ArrayList<>();
    }

    public void addErgebnis(Rennen rennen) {
        rennenListe.add(rennen);
    }

    public void erzeugeStatistik() {
        System.out.println("===== Wettbewerbsstatistik =====");

        HashMap<String, Integer> siegProMotorradTyp = new HashMap<>();
        HashMap<String, Integer> siegProFahrer = new HashMap<>();

        for (Rennen rennen : rennenListe) {
            Fahrer gewinner = rennen.getGewinner();
            String motorradTyp = gewinner.getMotorrad().getClass().getSimpleName();
            String fahrerName = gewinner.getName();

            // Statistik zu Motorradtypen
            siegProMotorradTyp.put(motorradTyp, siegProMotorradTyp.getOrDefault(motorradTyp, 0) + 1);

            // Statistik zu Fahrern
            siegProFahrer.put(fahrerName, siegProFahrer.getOrDefault(fahrerName, 0) + 1);
        }

        System.out.println("Siege pro Motorradtyp:");
        siegProMotorradTyp.forEach((typ, siege) -> System.out.println(typ + ": " + siege + " Siege"));

        System.out.println("\nSiege pro Fahrer:");
        siegProFahrer.forEach((name, siege) -> System.out.println(name + ": " + siege + " Siege"));
    }
}

