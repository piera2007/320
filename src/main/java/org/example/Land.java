package org.example;

/**
 * Einfaches Land-Objekt mit Name.
 * (Skill wird aktuell nicht weiterverwendet.)
 *
 * @author Piera Blum
 * @version 24.01.2025
 */
public class Land {
    private String name;

    /**
     * Erzeugt ein {@code Land}-Objekt.
     *
     * @param name  Name des Landes
     * @param skill Skill-Faktor (1-10, aktuell ungenutzt)
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
