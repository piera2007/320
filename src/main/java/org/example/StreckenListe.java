package org.example;

public class StreckenListe {
    public static final Rennstrecke EASY = new Rennstrecke("Easy Track", 2.0, 2);
    public static final Rennstrecke MEDIUM = new Rennstrecke("Medium Track", 5.0, 5);
    public static final Rennstrecke HARD = new Rennstrecke("Hard Track", 9.0, 8);

    public static Rennstrecke[] getAlleStrecken() {
        return new Rennstrecke[]{EASY, MEDIUM, HARD};
    }
}
