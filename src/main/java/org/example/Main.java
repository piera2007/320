package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Die Hauptklasse der Motorrad-Rennen-Simulation.
 * <p>
 * Dieses Programm ermöglicht es, neue Rennen anzulegen,
 * Wetten zu platzieren und ein Rennen zu starten. Am Ende
 * kann eine Statistik eingesehen und der Kontostand
 * angezeigt werden. Das Menü wird in einer Endlosschleife
 * ausgeführt, bis der Benutzer das Programm beendet.
 * <p>
 * Wichtige Aufgaben dieser Klasse:
 * <ul>
 *   <li>Steuerung des Hauptmenüs und Verarbeitung der Benutzereingaben</li>
 *   <li>Verwaltung eines {@link Rennen}-Objekts als laufendes Rennen</li>
 *   <li>Verwaltung eines {@link BetManager} mit Guthaben und Wetten</li>
 *   <li>Einbindung des {@link StatistikManager}, um Rennen und Ergebnisse
 *       zu speichern und später anzuzeigen</li>
 * </ul>
 *
 * <strong>Menüpunkte:</strong>
 * <ol>
 *   <li><b>Neues Rennen anlegen</b>:
 *       Streckenauswahl, Eingabe von Fahrern, Auswahl von Motorrädern.</li>
 *   <li><b>Wette platzieren</b>:
 *       Wette auf einen teilnehmenden Fahrer abschließen (Einsatz wird abgezogen).</li>
 *   <li><b>Rennen starten</b>:
 *       Anzahl Runden festlegen, Sieger ermitteln, Wetten auswerten.</li>
 *   <li><b>Statistik</b>:
 *       Zeigt die abgespeicherten Daten über vergangene Rennen.</li>
 *   <li><b>Kontostand anzeigen</b>:
 *       Zeigt das aktuelle Benutzer-Guthaben an.</li>
 *   <li><b>Beenden</b>:
 *       Programm läuft nicht länger weiter.</li>
 * </ol>
 * <p>
 * Bei Zeitgleichheit aller Fahrer (exakte Zeiten) wird
 * ein Unentschieden festgestellt und alle Wetten verlieren.
 *
 * @author Piera Blum
 * @version 1.0
 */
public class Main {

    /**
     * Scanner, um Benutzereingaben vom Terminal einzulesen.
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Dient zur Verwaltung der Statistik über abgeschlossene Rennen.
     */
    private static StatistikManager statistikManager = new StatistikManager();

    /**
     * Verwalter aller Wetten und des Benutzer-Guthabens.
     */
    private static BetManager betManager = new BetManager(100.0);

    /**
     * Aktuell laufendes, aber noch nicht gestartetes Rennen.
     */
    private static Rennen aktuellesRennen = null;

    /**
     * Einstiegspunkt des Programms (main-Methode).
     * <p>
     * Startet eine Endlosschleife mit einem Konsolen-Menü,
     * bis der Benutzer das Programm über eine Auswahl
     * (Menüpunkt 6) beendet.
     *
     * @param args Kommandozeilen-Argumente (nicht genutzt)
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
     * Legt ein neues Rennen an, sofern kein ungestartetes Rennen
     * existiert. Der Benutzer wählt eine Strecke aus und legt
     * eine Mindestzahl von Fahrern fest. Für jeden Fahrer werden
     * Name, Land, Erfahrung abgefragt, und ein Motorrad wird
     * ausgewählt.
     * <p>
     * Das angelegte Rennen wird in {@code aktuellesRennen} gespeichert.
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
            // Je nach Schwierigkeitsgrad können andere Motorräder verfügbar sein
            Motorrad[] moegliche = MotorradListe.getVerfuegbareFuerStrecke(strecke.getSchwierigkeitsgrad());
            Motorrad m = waehleMotorrad(moegliche);
            rennen.addTeilnehmer(f, m);
        }
        aktuellesRennen = rennen;
        System.out.println("Rennen mit " + anzahl + " Fahrern angelegt.");
    }

    /**
     * Ermöglicht das Platzieren einer Wette auf einen der Fahrer,
     * der noch nicht gestarteten Rennens.
     * <p>
     * Falls es kein Rennen oder bereits gestartetes Rennen gibt,
     * wird die Aktion abgebrochen.
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
     * Startet das aktuell angelegte Rennen, falls es existiert
     * und noch nicht gestartet wurde. Der Benutzer gibt die
     * Rundenanzahl an. Danach werden die Ergebnisse berechnet:
     * <ul>
     *   <li>Sieger ermittelt (oder Unentschieden)</li>
     *   <li>Wetten im {@link BetManager} ausgewertet</li>
     *   <li>Rennen in den {@link StatistikManager} übernommen</li>
     *   <li>{@code aktuellesRennen} auf {@code null} gesetzt</li>
     * </ul>
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
     * Erstellt einen neuen Fahrer durch Benutzereingaben
     * für Name, Land und Erfahrungsjahre.
     *
     * @return Ein {@link Fahrer}-Objekt mit den eingegebenen Daten
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
     * Lässt den Benutzer ein Motorrad aus dem übergebenen Array
     * auswählen. Falls ein ungültiger Index eingegeben wird,
     * wird das erste Motorrad gewählt.
     *
     * @param arr ein Array zulässiger Motorrad-Objekte,
     *            z. B. basierend auf der Streckenschwierigkeit
     * @return das ausgewählte {@link Motorrad}-Objekt
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
     * Lässt den Benutzer eine Rennstrecke aus {@link StreckenListe}
     * auswählen. Bei falscher Eingabe wird automatisch die erste
     * Strecke gewählt.
     *
     * @return die vom Benutzer gewählte {@link Rennstrecke}
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
     * Liest eine ganze Zahl (int) von der Konsole,
     * mit Fehlerbehandlung bei ungültigen Eingaben.
     *
     * @return der eingelesene int-Wert
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
     * Liest einen double-Wert (Kommazahl) von der Konsole,
     * mit Fehlerbehandlung bei ungültigen Eingaben.
     *
     * @return der eingelesene double-Wert
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
     * Zeigt den aktuellen Kontostand an, indem
     * {@link BetManager#getUserBalance()} aufgerufen wird.
     */
    private static void kontostandAnzeigen() {
        double bal = betManager.getUserBalance();
        System.out.println("\nIhr aktuelles Guthaben: " + bal);
    }
}
