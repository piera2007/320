package org.example;

import java.util.Scanner;
import java.util.List;

/**
 * Hauptklasse mit Menü.
 * 1) Rennen anlegen (mind. 2 Teilnehmer)
 * 2) Wetten platzieren (nur auf Teilnehmer des noch ungestarteten Rennens)
 * 3) Rennen starten (Ergebnis -> Wetten auswerten -> Statistik updaten)
 * 4) Statistik anzeigen
 * 5) Beenden
 *
 * So ist die Logik stringenter:
 * - Erst anlegen, dann Wetten, dann starten,
 * - Dann kann man erst ein neues Rennen anlegen.
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static StatistikManager statistikManager = new StatistikManager();

    // Nur EIN Rennen zurzeit, das angelegt, aber noch nicht gestartet sein kann
    private static Rennen aktuellesRennen = null;
    // Wetten beziehen sich IMMER auf das "aktuellesRennen"
    private static BetManager betManager = new BetManager();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== HAUPTMENÜ ===");
            System.out.println("1) Rennen anlegen");
            System.out.println("2) Wetten platzieren");
            System.out.println("3) Rennen starten (abschließen)");
            System.out.println("4) Statistik anzeigen");
            System.out.println("5) Beenden");
            System.out.print("Ihre Auswahl: ");

            int auswahl = leseInt();
            switch (auswahl) {
                case 1:
                    rennenAnlegen();
                    break;
                case 2:
                    wettenPlatzieren();
                    break;
                case 3:
                    rennenStarten();
                    break;
                case 4:
                    statistikManager.zeigeStatistik();
                    break;
                case 5:
                    running = false;
                    System.out.println("Programm beendet.");
                    break;
                default:
                    System.out.println("Ungültige Auswahl!");
            }
        }
        sc.close();
    }

    /**
     * Legt ein neues Rennen an, fragt:
     * - Welche Strecke?
     * - Wie viele Teilnehmer (mind. 2)
     * - Für jeden Teilnehmer: Land auswählen + Motorrad auswählen
     * => Ergebnis: Ein ungestartetes Rennen in aktuellesRennen
     */
    private static void rennenAnlegen() {
        if (aktuellesRennen != null && !aktuellesRennen.isStarted()) {
            System.out.println("Es existiert bereits ein ungestartetes Rennen. Bitte dieses erst starten oder abbrechen.");
            return;
        }
        // Wetten leeren
        betManager = new BetManager();

        System.out.println("\n--- Neues Rennen anlegen ---");
        Rennstrecke strecke = waehleStrecke();

        Rennen rennen = new Rennen(strecke);

        System.out.print("Anzahl Teilnehmer (mind. 2, max. 4): ");
        int anzahl = leseInt();
        if (anzahl < 2) {
            System.out.println("Wenigstens 2 Teilnehmer nötig. Setze anzahl=2.");
            anzahl = 2;
        }
        if (anzahl > 4) {
            anzahl = 4;
        }

        for (int i = 1; i <= anzahl; i++) {
            System.out.println("\nTeilnehmer " + i + ":");
            Land land = waehleLand();
            Motorrad moto = waehleMotorrad();
            try {
                rennen.addTeilnehmer(land, moto);
            } catch (IllegalStateException e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }

        aktuellesRennen = rennen;
        System.out.println("Rennen wurde angelegt. Teilnehmer: " + anzahl);
    }

    /**
     * Lässt den Benutzer Wetten auf das ungestartete Rennen platzieren.
     * - Nur möglich, wenn aktuellesRennen != null und !started
     * - Man wählt aus den vorhandenen Teilnehmern
     */
    private static void wettenPlatzieren() {
        if (aktuellesRennen == null) {
            System.out.println("Kein ungestartetes Rennen vorhanden!");
            return;
        }
        if (aktuellesRennen.isStarted()) {
            System.out.println("Rennen bereits gestartet, keine Wetten mehr möglich!");
            return;
        }

        System.out.println("\n--- Wetten platzieren ---");
        // Teilnehmer-Liste abrufen (anstelle von var)
        List<Land> teilnehmer = aktuellesRennen.getTeilnehmer();
        if (teilnehmer.isEmpty()) {
            System.out.println("Keine Teilnehmer!?");
            return;
        }

        System.out.println("Verfügbare Teilnehmer:");
        for (int i = 0; i < teilnehmer.size(); i++) {
            System.out.println((i + 1) + ") " + teilnehmer.get(i).getName());
        }

        System.out.print("Wähle Teilnehmer (Index): ");
        int index = leseInt() - 1;
        if (index < 0 || index >= teilnehmer.size()) {
            System.out.println("Ungültige Wahl!");
            return;
        }
        Land gewaehltesLand = teilnehmer.get(index);

        System.out.print("Wetteinsatz (z.B. 10.0): ");
        double einsatz = leseDouble();

        betManager.addBet(new Bet(gewaehltesLand.getName(), einsatz));
        System.out.println("Wette auf " + gewaehltesLand.getName() + " (Einsatz: " + einsatz + ") platziert.");

        // Alle Wetten anzeigen
        betManager.showAllBets();
    }

    /**
     * Startet das vorhandene Rennen (falls existiert und noch nicht gestartet).
     * - Fragt nach Runden
     * - wertet Wetten aus
     * - packt das Rennen in die Statistik
     * - setzt aktuellesRennen = null
     */
    private static void rennenStarten() {
        if (aktuellesRennen == null) {
            System.out.println("Kein angelegtes Rennen vorhanden.");
            return;
        }
        if (aktuellesRennen.isStarted()) {
            System.out.println("Rennen wurde schon gestartet/abgeschlossen.");
            return;
        }
        List<Land> teilnehmer = aktuellesRennen.getTeilnehmer();
        if (teilnehmer.size() < 2) {
            System.out.println("Mind. 2 Teilnehmer nötig! Rennen unvollständig!");
            return;
        }

        System.out.print("Wie viele Runden? ");
        int runden = leseInt();
        if (runden < 1) {
            runden = 1;
        }

        try {
            aktuellesRennen.starteRennen(runden);
        } catch (Exception e) {
            System.out.println("Fehler beim Start: " + e.getMessage());
            return;
        }

        // Sieger
        Land sieger = aktuellesRennen.getSieger();
        if (sieger == null) {
            System.out.println("Kein Sieger!? (Sollte nicht passieren)");
        } else {
            System.out.println("\nSieger: " + sieger.getName()
                    + " (Skill=" + sieger.getSkill() + ")");
            betManager.checkBets(sieger.getName());
        }
        // Rennen in Statistik
        statistikManager.addRennen(aktuellesRennen);
        // Rennen auf null setzen
        aktuellesRennen = null;
    }

    /**
     * Lies einen Integerwert vom Scanner (mit Fehlerbehandlung).
     */
    private static int leseInt() {
        while(true) {
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input);
            } catch(NumberFormatException e) {
                System.out.print("Ungültige Zahl. Bitte erneut: ");
            }
        }
    }

    /**
     * Lies einen Doublewert vom Scanner (mit Fehlerbehandlung).
     */
    private static double leseDouble() {
        while(true) {
            String input = sc.nextLine();
            try {
                return Double.parseDouble(input);
            } catch(NumberFormatException e) {
                System.out.print("Ungültige Kommazahl. Bitte erneut: ");
            }
        }
    }

    /**
     * Lässt den User ein Land aus LandListe wählen.
     */
    private static Land waehleLand() {
        Land[] alle = LandListe.getAlleLaender();
        System.out.println("Verfügbare Länder:");
        for (int i = 0; i < alle.length; i++) {
            System.out.println((i+1) + ") " + alle[i].getName()
                    + " (Skill=" + alle[i].getSkill() + ")");
        }
        System.out.print("Ihre Wahl: ");
        int w = leseInt() - 1;
        if (w < 0 || w >= alle.length) {
            w = 0;
            System.out.println("Ungültig, nehme " + alle[0].getName());
        }
        return alle[w];
    }

    /**
     * Lässt den User ein Motorrad aus MotorradListe wählen.
     */
    private static Motorrad waehleMotorrad() {
        Motorrad[] arr = MotorradListe.getAlleMotorradTypen();
        System.out.println("Verfügbare Motorradtypen:");
        for (int i = 0; i < arr.length; i++) {
            Motorrad m = arr[i];
            System.out.println((i+1) + ") " + m.getClass().getSimpleName()
                    + " [" + m.getMarke() + " " + m.getModell()
                    + ", Speed=" + m.getGeschwindigkeit()
                    + ", Acc=" + m.getBeschleunigung() + "]");
        }
        System.out.print("Ihre Wahl: ");
        int w = leseInt() - 1;
        if (w < 0 || w >= arr.length) {
            w = 0;
            System.out.println("Ungültig, nehme " + arr[0].getClass().getSimpleName());
        }
        return arr[w];
    }

    /**
     * Lässt den User eine Rennstrecke aus StreckenListe wählen.
     */
    private static Rennstrecke waehleStrecke() {
        Rennstrecke[] arr = StreckenListe.getAlleStrecken();
        System.out.println("Verfügbare Strecken:");
        for (int i = 0; i < arr.length; i++) {
            System.out.println((i+1) + ") " + arr[i].getName()
                    + " (Länge=" + arr[i].getLaenge()
                    + ", Schwierigkeit=" + arr[i].getSchwierigkeitsgrad() + ")");
        }
        System.out.print("Ihre Wahl: ");
        int w = leseInt() - 1;
        if (w < 0 || w >= arr.length) {
            w = 0;
            System.out.println("Ungültige Wahl, nehme " + arr[0].getName());
        }
        return arr[w];
    }
}
