package com.example.java2denginejavafx;

import javafx.geometry.Point2D;

public class LineSegment {
    private Point2D startPoint;
    private Point2D endPoint;

    public LineSegment(Point2D startPoint, Point2D endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    // Metoda do odczytania współrzędnych poszczególnych końców odcinka
    public Point2D getStartPoint() {
        return startPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    // Metoda do zmodyfikowania współrzędnych poszczególnych końców odcinka
    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point2D endPoint) {
        this.endPoint = endPoint;
    }

    // Metoda do narysowania odcinka

}
