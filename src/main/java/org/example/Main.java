package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Hauptklasse der Motorrad-Rennen-Simulation.
 * <p>
 * Steuert ein Menü, in dem der Benutzer:
 * <ol>
 *   <li>Neues Rennen anlegen</li>
 *   <li>Wette platzieren</li>
 *   <li>Rennen starten</li>
 *   <li>Statistik anzeigen</li>
 *   <li>Kontostand anzeigen</li>
 *   <li>Beenden</li>
 * </ol>
 *
 * Bei falscher Eingabe (z. B. Buchstaben statt Zahl) wird eine
 * Fehlermeldung ausgegeben und erneut gefragt.
 * <p>
 * Programm endet erst bei Auswahl von Punkt 6.
 *
 * @author
 * @version 23.01.2025
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static StatistikManager statistikManager = new StatistikManager();
    private static BetManager betManager = new BetManager(100.0);
    private static Rennen aktuellesRennen = null;

    /**
     * Startet das Hauptmenü in einer Endlosschleife.
     *
     * @param args nicht genutzt
     */
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== HAUPTMENÜ === (Guthaben: "
                    + betManager.getUserBalance() + ")");
            System.out.println("1) Neues Rennen anlegen");
            System.out.println("2) Wette platzieren");
            System.out.println("3) Rennen starten");
            System.out.println("4) Statistik");
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
                    kontostandAnzeigen();
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
     * Legt ein neues Rennen an, falls kein ungestartetes existiert.
     */
    private static void rennenAnlegen() {
        if (aktuellesRennen != null && !aktuellesRennen.isStarted()) {
            System.out.println("Es gibt bereits ein ungestartetes Rennen!");
            return;
        }
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
            Motorrad[] moegliche = MotorradListe.getVerfuegbareFuerStrecke(strecke.getSchwierigkeitsgrad());
            Motorrad m = waehleMotorrad(moegliche);
            rennen.addTeilnehmer(f, m);
        }
        aktuellesRennen = rennen;
        System.out.println("Rennen mit " + anzahl + " Fahrern angelegt.");
    }

    /**
     * Platziert eine Wette auf einen Fahrer des laufenden Rennens.
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
     * Startet das angelegte Rennen, falls vorhanden.
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
            System.out.println("UNENTSCHIEDEN! Alle haben dieselbe Zeit!");
            betManager.checkBets("");
        } else {
            System.out.println("\nSieger: " + sieger.getFahrerName() + " (" + sieger.getLand() + ")");
            betManager.checkBets(sieger.getFahrerName());
        }

        statistikManager.addRennen(aktuellesRennen);
        aktuellesRennen = null;
    }

    /**
     * Fragt Name, Land und Erfahrung ab, um einen Fahrer zu erzeugen.
     *
     * @return neuer {@link Fahrer}
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
     * Lässt den Benutzer aus einem {@code Motorrad}-Array wählen.
     *
     * @param arr zulässige Motorräder
     * @return das gewählte Motorrad
     */
    private static Motorrad waehleMotorrad(Motorrad[] arr) {
        System.out.println("Verfügbare Motorräder (nach Schwierigkeit):");
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
     * Lässt den Benutzer eine {@link Rennstrecke} aus
     * {@link StreckenListe} auswählen.
     *
     * @return gewählte Rennstrecke
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

    /**
     * Liest eine Ganzzahl. Gibt bei fehlerhafter Eingabe
     * "Ungültige Eingabe. Ganzzahl:" aus und fragt erneut.
     *
     * @return int-Wert
     */
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

    /**
     * Liest einen double-Wert (Kommazahl). Bei Fehler:
     * "Ungültige Kommazahl:" und erneut abfragen.
     *
     * @return double-Wert
     */
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
        double bal = betManager.getUserBalance();
        System.out.println("\nIhr aktuelles Guthaben: " + bal);
    }
}
