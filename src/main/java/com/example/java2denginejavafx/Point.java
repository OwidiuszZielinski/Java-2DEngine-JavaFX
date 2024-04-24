package com.example.java2denginejavafx;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

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

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public Point(double x, double y, double width, double height, Color targetColor, Shape shape) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetColor = targetColor;
        this.shape = shape;
    }
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
    public double getImageRotate() {
        return imageRotate;
    }

    public void setImageRotate(double imageRotate) {
        this.imageRotate = imageRotate;
    }

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

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
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

    public Color getTargetColor() {
        return targetColor;
    }

    public void setTargetColor(Color targetColor) {
        this.targetColor = targetColor;
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