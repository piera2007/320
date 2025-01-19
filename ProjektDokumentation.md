# Motorrad-Rennen-Simulation

Dieses Projekt ist eine **Konsolenanwendung** in Java, die ein einfaches **Motorrad-Rennen** simuliert.
Jeder Teilnehmer ist ein *Fahrer* mit Namen, Land und Erfahrung, sowie einem *Motorrad* mit unterschiedlichen Geschwindigkeiten.  
Mehrere Fahrer können an einem Rennen teilnehmen, eine **Strecke** wird gewählt, und am Ende wird ein **Sieger** bestimmt.

Ausserdem kann man **Wetten** auf den Ausgang eines Rennens platzieren – mit Guthaben-Verwaltung.

## Features

1. **Fahrer und Motorradwahl**
    - Fahrer haben Namen, Land und Erfahrung.
    - Motorräder (Sport, Cross, Touring) können je nach **Streckenschwierigkeit** gewählt werden.

2. **Rennen**
    - Mindestens 2 Fahrer (Teilnehmer).
    - Simuliert eine bestimmte Anzahl an Runden.
    - Eindeutiger Sieger oder **Unentschieden**, falls mehrere exakt dieselbe Zeit haben.

3. **Wetten**
    - Jeder Benutzer startet mit einem **Guthaben** (z. B. 100.0).
    - Eine Wette auf einen Fahrer zieht Einsatz vom Guthaben ab.
    - Bei korrektem Tipp erhält man den **doppelten Einsatz** zurück. Bei Unentschieden oder falschem Tipp ist der Einsatz verloren.

4. **Statistik**
    - Es werden **Siege pro Fahrer**, **Siege pro Motorrad** und **Siege pro Land** aufgezeichnet.
    - Detaillierte **Endzeiten** aller Fahrer pro Rennen werden gespeichert und mit einer **Zeitdifferenz** zum Sieger angezeigt.

## Ablauf

1. Beim Start zeigt das Programm ein **Hauptmenü**:
    - 1) **Neues Rennen anlegen**
        - Strecke wählen, Anzahl Fahrer, Fahrer-Daten eingeben, Motorrad wählen.
    - 2) **Wette platzieren**
        - Du wählst aus den **Teilnehmern** des noch nicht gestarteten Rennens.
        - Einsatz wird vom Guthaben abgezogen.
    - 3) **Rennen starten**
        - Du bestimmst die Rundenanzahl.
        - Das Rennen wird simuliert.
        - Bei Unentschieden gibt es keinen Gewinner, alle Wetten verlieren.
        - Sonst gewinnt einer, und die richtigen Wetten werden ausbezahlt.
    - 4) **Statistik & Ergebnisse**
        - Zeigt sowohl die **Standard-Statistik** (Siege pro Fahrer/Motorrad/Land) als auch die **detaillierten Rennergebnisse** aller bisher abgeschlossenen Rennen.
    - 5) **Kontostand anzeigen**
        - Zeigt dein aktuelles Guthaben.
    - 6) **Beenden**

2. **Erweiterungen**
    - Du kannst beliebig viele Rennen hintereinander anlegen.
    - Mehrere Fahrer aus demselben Land sind möglich, wenn sie verschiedene Fahrernamen haben.
    - Rundenanzahl kann variieren.

## Installation und Ausführung

1. **Java-Version**: Achte darauf, dass du Java 8 oder höher installiert hast.
2. **Kompilieren**: `javac -d bin src/org/example/*.java` (oder in einer IDE wie IntelliJ/Maven/Gradle-Projekt).
3. **Starten**: `java org.example.Main` (bei entsprechender Package-Struktur).

## Anmerkungen

- Dieses Projekt dient als **Demonstration** verschiedener OOP-Konzepte:
    - Vererbung (`Motorrad` + Subklassen)
    - Abstrakte Klasse (`Motorrad`)
    - Interface (`IFahrzeug`)
    - Komposition (`Fahrer` hat Daten, `Rennen` verwaltet mehrere Fahrer)
    - Polymorphismus (verschiedene Motorradtypen)
    - Exception-Handling (falsche Eingaben)
    - Aggregation/Assoziation (StatistikManager sammelt mehrere Rennen).
- **Wertungen** und **Formeln** sind nur beispielhaft. Man kann das Spiel beliebig erweitern (z. B. Wetter, Rundenzeiten, Quoten …).

Viel Spass!

