package com.example.java2denginejavafx;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klasa Logger reprezentuje prosty mechanizm logowania błędów oraz informacji o częstotliwości klatek.
 * Jest wykorzystywana do zapisywania informacji o błędach oraz o częstotliwości renderowania klatek.
 */
public class Logger {

    private final PrintWriter writer;

    /**
     * Konstruktor klasy Logger, inicjalizuje obiekt PrintWriter do zapisu logów do pliku "error_log.txt".
     * W przypadku błędu podczas tworzenia obiektu PrintWriter, rzucany jest RuntimeException.
     */
    {
        try {
            writer = new PrintWriter(new FileWriter("error_log.txt", true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Konstruktor domyślny klasy Logger.
     */
    public Logger() {
    }

    /**
     * Metoda logError służy do zapisywania informacji o błędzie oraz stack trace do pliku.
     *
     * @param e obiekt Exception zawierający informacje o błędzie
     */
    public void logError(Exception e) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        writer.println("Error at: " + formattedTime);
        e.printStackTrace(writer);
        writer.println();
    }

    /**
     * Metoda logFrameRate służy do wyświetlania informacji o częstotliwości renderowania klatek w konsoli.
     *
     * @param currentTime aktualny czas w milisekundach
     * @param startTime   czas rozpoczęcia działania programu w milisekundach
     * @param frames      liczba klatek
     */
    public void logFrameRate(long currentTime, long startTime, long frames) {
        long elapsedTime = currentTime - startTime;
        long seconds = elapsedTime / 1000;
        System.out.println("Czas: " + seconds + " sek., Klatki: " + frames);
    }

    /**
     * Metoda closeErrorLogger zamyka strumień zapisu PrintWriter.
     */
    public void closeErrorLogger() {
        writer.close();
    }
}