package com.example.java2denginejavafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Klasa EngineCanvas reprezentuje płótno (Canvas) używane do rysowania w aplikacji.
 * Zapewnia także obsługę tła i jego wyświetlanie.
 */
public class EngineCanvas {

    private final Canvas canvas;
    private boolean backgroundSelected;
    private Image backgroundImage;

    /**
     * Konstruktor klasy EngineCanvas.
     *
     * @param width  szerokość płótna
     * @param height wysokość płótna
     */
    public EngineCanvas(double width, double height) {
        canvas = new Canvas(width, height);
        backgroundSelected = false;
    }

    /**
     * Metoda zwraca obraz tła.
     *
     * @return obraz tła
     */
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Metoda ustawia obraz tła.
     *
     * @param backgroundImage obraz tła do ustawienia
     */
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Metoda zwraca płótno (Canvas).
     *
     * @return płótno
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Metoda sprawdza, czy tło zostało wybrane.
     *
     * @return true jeśli tło zostało wybrane, w przeciwnym razie false
     */
    public boolean isBackgroundSelected() {
        return backgroundSelected;
    }

    /**
     * Metoda ustawia stan wybrania tła.
     *
     * @param backgroundSelected true jeśli tło zostało wybrane, w przeciwnym razie false
     */
    public void setBackgroundSelected(boolean backgroundSelected) {
        this.backgroundSelected = backgroundSelected;
    }
}