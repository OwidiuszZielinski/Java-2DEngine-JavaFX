package com.example.java2denginejavafx;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Point {
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;
    private Shape shape;
    private Image image;

    public Point() {
    }

    // Konstruktor dla kształtu
    public Point(double x, double y, double width, double height, Color color, Shape shape) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.shape = shape;
    }

    // Konstruktor dla obrazu (bitmapy)
    public Point(double x, double y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    // Gettery i settery

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}