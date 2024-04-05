package com.example.java2denginejavafx;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLogger {

    private final PrintWriter writer;

    {
        try {
            writer = new PrintWriter(new FileWriter("error_log.txt", true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ErrorLogger() {

    }

    public void logError(Exception e) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        writer.println("Error at: " + formattedTime);
        e.printStackTrace(writer);
        writer.println();
    }

    public void closeErrorLogger() {
        writer.close();
    }
}