package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Hauptklasse:
 * - Enthält ein Benutzer-Guthaben
 * - Erlaubt Wetten (werden abgezogen vom Guthaben),
 *   bei Sieg gewinnt man doppelt,
 *   bei Unentschieden verliert man.
 * - Sorgt dafür, dass bei exakter Zeitgleichheit
 *   (Unentschieden) kein Sieger bestimmt wird.
 *
 * @author
 * @version 1.0
 */
public class Main {
    private static Scanner sc = new Scanner(System.in);

    private static StatistikManager statistikManager = new StatistikManager();
    // Starte mit 100.0 Guthaben
    private static BetManager betManager = new BetManager(100.0);

    private static Rennen aktuellesRennen = null;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== HAUPTMENÜ === (Guthaben: " + betManager.getUserBalance() + ")");
            System.out.println("1) Neues Rennen anlegen");
            System.out.println("2) Wette platzieren");
            System.out.println("3) Rennen starten");
            System.out.println("4) Statistik anzeigen");
            System.out.println("5) Kontostand anzeigen");
            System.out.println("6) Beenden");
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
                    System.out.println("\n=== Aktuelle Konto- und Wettübersicht ===");
                    System.out.println("Kontostand: " + betManager.getUserBalance());
                    betManager.showAllBets();
                    break;
                case 6:
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
     * Legt ein neues Rennen an.
     */
    private static void rennenAnlegen() {
        if (aktuellesRennen != null && !aktuellesRennen.isStarted()) {
            System.out.println("Es gibt bereits ein ungestartetes Rennen!");
            return;
        }
        // Neue Wetten -> BetManager leeren oder beibehalten?
        // Hier: leeren wir nicht automatisch,
        // da der Benutzer sein Guthaben behalten soll.
        // Falls du möchtest, kannst du bets.clear() hier aufrufen.

        System.out.println("\n--- Neues Rennen anlegen ---");
        Rennstrecke strecke = waehleStrecke();

        Rennen rennen = new Rennen(strecke);

        int anzahl;
        do {
            System.out.print("Wie viele Fahrer? (mind 2): ");
            anzahl = leseInt();
            if (anzahl < 2) {
                System.out.println("Mindestens 2 Fahrer nötig!");
            }
        } while (anzahl < 2);

        for (int i = 1; i <= anzahl; i++) {
            System.out.println("\nFahrer " + i + ":");
            Fahrer f = erstelleFahrer();
            // je nach Strecke nur bestimmte Motos
            Motorrad[] moegliche = MotorradListe.getVerfuegbareFuerStrecke(strecke.getSchwierigkeitsgrad());
            Motorrad m = waehleMotorrad(moegliche);
            rennen.addTeilnehmer(f, m);
        }
        aktuellesRennen = rennen;
        System.out.println("Rennen mit " + anzahl + " Fahrern angelegt.");
    }

    /**
     * Wette platzieren, nur auf Fahrer des ungestarteten Rennens.
     */
    private static void wettenPlatzieren() {
        if (aktuellesRennen == null || aktuellesRennen.isStarted()) {
            System.out.println("Kein ungestartetes Rennen vorhanden!");
            return;
        }
        List<Fahrer> flist = aktuellesRennen.getFahrerListe();
        if (flist.size() < 2) {
            System.out.println("Zu wenige Fahrer!");
            return;
        }

        System.out.println("\n--- Wette platzieren --- (Guthaben: " + betManager.getUserBalance() + ")");
        System.out.println("Verfügbare Fahrer:");
        for (int i = 0; i < flist.size(); i++) {
            Fahrer f = flist.get(i);
            System.out.println((i + 1) + ") " + f.getFahrerName()
                    + " (" + f.getLand() + ", Erf=" + f.getErfahrung() + ")");
        }

        System.out.print("Wähle Fahrer (Index): ");
        int idx = leseInt() - 1;
        if (idx < 0 || idx >= flist.size()) {
            System.out.println("Ungültige Wahl!");
            return;
        }
        Fahrer gewaehlter = flist.get(idx);

        System.out.print("Einsatz: ");
        double einsatz = leseDouble();

        Bet b = new Bet(gewaehlter.getFahrerName(), einsatz);
        boolean ok = betManager.addBet(b);
        if (!ok) {
            System.out.println("Nicht genug Guthaben!");
            return;
        }
        System.out.println("Wette platziert auf " + gewaehlter.getFahrerName() + " (Einsatz=" + einsatz + ")");
        betManager.showAllBets();
    }

    /**
     * Rennen starten:
     * - mind. 2 Fahrer
     * - Runden
     * - Sieger oder unentschieden
     * - Wetten auswerten
     * - Statistik updaten
     */
    private static void rennenStarten() {
        if (aktuellesRennen == null) {
            System.out.println("Kein angelegtes Rennen!");
            return;
        }
        if (aktuellesRennen.isStarted()) {
            System.out.println("Rennen schon gestartet!");
            return;
        }
        System.out.print("Wie viele Runden? ");
        int r = leseInt();
        if (r < 1) r = 1;

        try {
            aktuellesRennen.starteRennen(r);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
            return;
        }

        Fahrer sieger = aktuellesRennen.getSieger();
        if (sieger == null) {
            // unentschieden
            System.out.println("UNENTSCHIEDEN! Alle haben dieselbe Zeit!");
            betManager.checkBets(""); // wir geben leeren String => unentschieden
        } else {
            System.out.println("\nSieger: " + sieger.getFahrerName() + " (" + sieger.getLand() + ")");
            betManager.checkBets(sieger.getFahrerName());
        }

        statistikManager.addRennen(aktuellesRennen);
        aktuellesRennen = null;
    }

    /**
     * Erstellt einen Fahrer anhand von Benutzereingaben
     */
    private static Fahrer erstelleFahrer() {
        System.out.print("Fahrer-Name: ");
        String name = sc.nextLine();

        System.out.print("Land (z. B. Schweiz): ");
        String land = sc.nextLine();

        int erf;
        while(true) {
            System.out.print("Jahre Erfahrung (0..50): ");
            erf = leseInt();
            if (erf < 0) {
                System.out.println("Nicht negativ!");
            } else if (erf > 50) {
                System.out.println("Max 50. Nehme 50.");
                erf = 50;
                break;
            } else {
                break;
            }
        }
        return new Fahrer(name, land, erf);
    }

    /**
     * Lässt den User ein Motorrad aus der übergebenen Auswahl wählen.
     */
    private static Motorrad waehleMotorrad(Motorrad[] arr) {
        System.out.println("Verfügbare Motorräder (basierend auf Strecken-Schwierigkeit):");
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
     * Wählt eine Rennstrecke aus StreckenListe
     */
    private static Rennstrecke waehleStrecke() {
        Rennstrecke[] arr = StreckenListe.getAlleStrecken();
        System.out.println("Verfügbare Strecken:");
        for (int i = 0; i < arr.length; i++) {
            Rennstrecke rs = arr[i];
            System.out.println((i+1) + ") " + rs.getName()
                    + " (Länge=" + rs.getLaenge()
                    + ", Schwierigkeit=" + rs.getSchwierigkeitsgrad() + ")");
        }
        System.out.print("Ihre Wahl: ");
        int ind = leseInt() - 1;
        if (ind < 0 || ind >= arr.length) {
            ind = 0;
            System.out.println("Ungültig, nehme " + arr[0].getName());
        }
        return arr[ind];
    }

    private static int leseInt() {
        while(true) {
            String inp = sc.nextLine();
            try {
                return Integer.parseInt(inp);
            } catch(NumberFormatException e) {
                System.out.print("Ungültige Eingabe. Ganzzahl: ");
            }
        }
    }

    private static double leseDouble() {
        while(true) {
            String inp = sc.nextLine();
            try {
                return Double.parseDouble(inp);
            } catch(NumberFormatException e) {
                System.out.print("Ungültige Kommazahl: ");
            }
        }
    }
    /**
     * Zeigt den aktuellen Kontostand an.
     */
    private static void kontostandAnzeigen() {
        double balance = betManager.getUserBalance();
        System.out.println("\nIhr aktuelles Guthaben beträgt: " + balance + " Geldeinheiten.");    }
}
