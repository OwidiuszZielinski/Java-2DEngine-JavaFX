package com.example.java2denginejavafx;

/**
 * Klasa Tool reprezentuje narzędzie w aplikacji.
 */
public class Tool {
    private String name;

    /**
     * Konstruktor klasy Tool.
     *
     * @param name nazwa narzędzia
     */
    public Tool(String name) {
        this.name = name;
    }

    /**
     * Metoda getName zwraca nazwę narzędzia.
     *
     * @return nazwa narzędzia
     */
    public String getName() {
        return name;
    }
}
