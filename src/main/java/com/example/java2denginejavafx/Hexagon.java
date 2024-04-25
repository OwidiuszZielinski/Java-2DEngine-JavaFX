package com.example.java2denginejavafx;

import javafx.scene.Node;
import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {
    public Hexagon() {
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    public Hexagon(double... points) {
        super(points);
    }
}
