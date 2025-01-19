package org.example;

/**
 * Repräsentiert ein Land mit Name und Skill (Erfahrung/Kompetenz).
 *
 * @author Piera Blum
 * @version 1.0
 */
public class Land {
    private String name;

    /**
     * Konstruktor
     * @param name  Name des Landes
     * @param skill Skill (1-10)
     */
    public Land(String name, int skill) {
        this.name = name;
    }

    /**
     * @return Name des Landes
     */
    public String getName() {
        return name;
    }

}
