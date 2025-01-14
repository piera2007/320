package org.example;

/**
 * Repräsentiert ein Land mit Name und Skill (Erfahrung/Kompetenz).
 * <p>
 * Anstelle eines Fahrers -> das Land ist der Teilnehmer.
 *
 * @author
 * @version 1.0
 */
public class Land {
    private String name;
    private int skill; // 1-10, je höher, desto besser/faster

    /**
     * Konstruktor
     * @param name  Name des Landes
     * @param skill Skill (1-10)
     */
    public Land(String name, int skill) {
        this.name = name;
        this.skill = skill;
    }

    /**
     * @return Name des Landes
     */
    public String getName() {
        return name;
    }

    /**
     * @return Skill (1-10)
     */
    public int getSkill() {
        return skill;
    }
}
