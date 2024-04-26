package com.example.java2denginejavafx;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Klasa Point reprezentuje punkt na płaszczyźnie, który może być wykorzystywany w różnych kontekstach.
 * Może przechowywać informacje o pozycji (x, y), rozmiarze, kolorze, kształcie oraz obrazie.
 */
public class Point {
    private double x;
    private double y;
    private double width;
    private double height;
    private Color targetColor;
    private Shape shape;
    private Image image;
    private double imageRotate;
    private Tool tool;

    /**
     * Konstruktor domyślny klasy Point.
     */
    public Point() {
    }

    /**
     * Konstruktor klasy Point inicjalizujący punkt z podanymi współrzędnymi (x, y).
     *
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Konstruktor klasy Point inicjalizujący punkt z podanymi parametrami.
     *
     * @param x           współrzędna x
     * @param y           współrzędna y
     * @param width       szerokość punktu
     * @param height      wysokość punktu
     * @param targetColor docelowy kolor punktu
     * @param shape       kształt punktu
     */
    public Point(double x, double y, double width, double height, Color targetColor, Shape shape) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetColor = targetColor;
        this.shape = shape;
    }

    /**
     * Konstruktor kopiujący klasy Point.
     *
     * @param point punkt do skopiowania
     */
    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
        this.width = point.width;
        this.height = point.height;
        this.targetColor = point.targetColor;
        this.shape = point.shape;
        this.image = point.image;
        this.imageRotate = point.imageRotate;
        this.tool = point.tool;
    }

    /**
     * Metoda getImageRotate zwraca kąt obrotu obrazu.
     *
     * @return kąt obrotu obrazu
     */
    public double getImageRotate() {
        return imageRotate;
    }

    /**
     * Metoda setImageRotate ustawia kąt obrotu obrazu.
     *
     * @param imageRotate kąt obrotu obrazu
     */
    public void setImageRotate(double imageRotate) {
        this.imageRotate = imageRotate;
    }

    /**
     * Metoda getX zwraca współrzędną x punktu.
     *
     * @return współrzędna x
     */
    public double getX() {
        return x;
    }

    /**
     * Metoda setX ustawia współrzędną x punktu.
     *
     * @param x współrzędna x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Metoda getY zwraca współrzędną y punktu.
     *
     * @return współrzędna y
     */
    public double getY() {
        return y;
    }

    /**
     * Metoda setY ustawia współrzędną y punktu.
     *
     * @param y współrzędna y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Metoda getWidth zwraca szerokość punktu.
     *
     * @return szerokość punktu
     */
    public double getWidth() {
        return width;
    }

    /**
     * Metoda getTool zwraca narzędzie powiązane z punktem.
     *
     * @return narzędzie powiązane z punktem
     */
    public Tool getTool() {
        return tool;
    }

    /**
     * Metoda setTool ustawia narzędzie powiązane z punktem.
     *
     * @param tool narzędzie powiązane z punktem
     */
    public void setTool(Tool tool) {
        this.tool = tool;
    }

    /**
     * Metoda setWidth ustawia szerokość punktu.
     *
     * @param width szerokość punktu
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Metoda getHeight zwraca wysokość punktu.
     *
     * @return wysokość punktu
     */
    public double getHeight() {
        return height;
    }

    /**
     * Metoda setHeight ustawia wysokość punktu.
     *
     * @param height wysokość punktu
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Metoda getTargetColor zwraca docelowy kolor punktu.
     *
     * @return docelowy kolor punktu
     */
    public Color getTargetColor() {
        return targetColor;
    }

    /**
     * Metoda setTargetColor ustawia docelowy kolor punktu.
     *
     * @param targetColor docelowy kolor punktu
     */
    public void setTargetColor(Color targetColor) {
        this.targetColor = targetColor;
    }

    /**
     * Metoda getShape zwraca kształt punktu.
     *
     * @return kształt punktu
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Metoda setShape ustawia kształt punktu.
     *
     * @param shape kształt punktu
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Metoda getImage zwraca obraz powiązany z punktem.
     *
     * @return obraz powiązany z punktem
     */
    public Image getImage() {
        return image;
    }

    /**
     * Metoda setImage ustawia obraz powiązany z punktem.
     *
     * @param image obraz powiązany z punktem
     */
    public void setImage(Image image) {
        this.image = image;
    }
}