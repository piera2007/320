package org.example;

/**
 * Liste vorgegebener Länder.
 * Der Benutzer kann daraus auswählen.
 *
 * Du kannst hier beliebig Länder mit verschiedenem Skill eintragen.
 *
 * @author
 * @version 1.0
 */
public class LandListe {
    public static final Land CH = new Land("Schweiz", 9);
    public static final Land IT = new Land("Italien", 7);
    public static final Land DE = new Land("Deutschland", 8);
    public static final Land FR = new Land("Frankreich", 6);

    public static Land[] getAlleLaender() {
        return new Land[]{CH, IT, DE, FR};
    }
}
