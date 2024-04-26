package com.example.java2denginejavafx;

import javafx.scene.Node;
import javafx.scene.shape.Polygon;

/**
 * Klasa Hexagon reprezentuje sześciokąt foremny dziedziczący po klasie Polygon.
 * Sześciokąt ten może być używany do rysowania w aplikacji.
 */
public class Hexagon extends Polygon {

    /**
     * Konstruktor domyślny klasy Hexagon.
     */
    public Hexagon() {
    }

    /**
     * Konstruktor klasy Hexagon przyjmujący współrzędne wierzchołków sześciokąta.
     *
     * @param points współrzędne wierzchołków sześciokąta
     */
    public Hexagon(double... points) {
        super(points);
    }

    /**
     * Metoda zwraca węzeł, który jest podstawą dla stylizacji węzła typu Hexagon.
     *
     * @return węzeł podstawowy dla stylizacji
     */
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}