package org.example;

/**
 * Einfaches Land-Objekt mit Name.
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
     */
    public Land(String name) {
        this.name = name;
    }

    /**
     * @return Name des Landes
     */
    public String getName() {
        return name;
    }
}
