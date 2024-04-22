package com.example.java2denginejavafx;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.util.LinkedList;
import java.util.Queue;

public class PrimitiveRenderer {

    private final Point point;
    private final EngineCanvas canvas;



    private final Point[] lastTwoClicks;

    public PrimitiveRenderer(Point point, EngineCanvas canvas, Point[] lastTwoClicks) {
        this.point = point;
        this.canvas = canvas;
        this.lastTwoClicks = lastTwoClicks;

    }


    public void chooseDrawCircle() {
        point.setTool(null);
        point.setShape(new Circle(point.getHeight()));


    }

    public void chooseDrawSquare() {
        point.setTool(null);
        point.setShape(new Rectangle(point.getHeight(), point.getHeight()));
    }

    public void chooseDrawPolygon() {
        point.setTool(null);
        point.setShape(new Polygon());
    }

    public void chooseDrawRectangle() {
        point.setTool(null);
        point.setShape(new Rectangle(point.getHeight() * 2, point.getHeight()));
    }

    public void chooseDrawHexagon() {
        point.setTool(null);
        point.setShape(new Hexagon());
    }
    public void chooseDrawLine(){
        point.setShape(null);
        point.setTool(new Tool("Line"));

    }






    public void drawEquilateralTriangle(GraphicsContext gc, double sideLength) {
        double height = sideLength * Math.sqrt(3) / 2; // Wysokość trójkąta równobocznego
        // Współrzędne punktów trójkąta
        double x1 = point.getX() - sideLength / 2;
        double y1 = point.getY() + height / 2;
        double x2 = x1 + sideLength;
        double y2 = y1;
        double x3 = point.getX();
        double y3 = point.getY() - height / 2;
        // Rysowanie trójkąta
        gc.setFill(point.getTargetColor());
        gc.fillPolygon(new double[]{ x1, x2, x3 }, new double[]{ y1, y2, y3 }, 3);
    }

    public void drawRegularHexagon(GraphicsContext gc, double sideLength) {
        double[] xPoints = new double[6];
        double[] yPoints = new double[6];
        double rotate = 0; // Domyślnie brak obrotu
        if (point.getShape() instanceof Hexagon h) {
            rotate = h.getRotate();
        }
        for (int i = 0; i < 6; i++) {
            double angleRad = Math.toRadians(60 * i + rotate);
            xPoints[i] = point.getX() + sideLength * Math.cos(angleRad);
            yPoints[i] = point.getY() + sideLength * Math.sin(angleRad);
        }

        gc.setFill(point.getTargetColor());
        gc.fillPolygon(xPoints, yPoints, 6);
    }
    public void drawSquare(GraphicsContext gc){
        double x = point.getX() - point.getWidth() / 2;
        double y = point.getY() - point.getHeight() / 2;
        if(point.getShape() instanceof Rectangle rectangle) {
            if (point.getShape().getRotate() != 0) {
                gc.save();
                gc.translate(x + rectangle.getWidth() / 2, y + rectangle.getHeight() / 2);
                gc.rotate(rectangle.getRotate());
                gc.fillRect(-rectangle.getWidth() / 2, -rectangle.getHeight() / 2, rectangle.getWidth(), rectangle.getHeight());
                gc.restore();
            } else {
                gc.fillRect(x, y, rectangle.getWidth(), rectangle.getHeight());
            }
        }
    }

    public void renderLine(GraphicsContext gc, Point a, Point b) {
        point.setShape(null);
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }


    public void setColor(Color selectedColor) {
        System.out.println("Ustawiono kolor : " + selectedColor.toString());
        point.setTargetColor(selectedColor);
        //point.setShape(null);
    }



    public void drawCircle(GraphicsContext gc) {
        if (point.getShape() instanceof Circle circle) {
            double x = point.getX() - point.getWidth() / 2;
            double y = point.getY() - point.getHeight() / 2;
            gc.fillOval(x,y,circle.getRadius(),circle.getRadius());
        }
    }

    public void drawImage(GraphicsContext gc) {
        double x = point.getX() - point.getWidth() / 2;
        double y = point.getY() - point.getHeight() / 2;
        Image image = point.getImage();
        double width = Math.min(image.getWidth(), point.getWidth());
        double height = Math.min(image.getHeight(), point.getHeight());
        gc.save(); // Zachowaj stan rysowania
        gc.translate(x + width / 2, y + height / 2); // Przesuń do punktu środkowego obrazka
        gc.rotate(point.getImageRotate()); // Obróć obrazek
        gc.drawImage(image, -width / 2, -height / 2, width, height); // Narysuj obrócony obrazek
        gc.restore(); // Przywróć poprzedni stan rysowania
    }
    public void rotateObject() {
        rotatePoint();

    }

    private void rotatePoint() {
        if (point.getShape() instanceof Rectangle rectangle) {
            rectangle.setRotate(rectangle.getRotate() + 5);
        }
        if(point.getShape() instanceof  Hexagon hexagon){
            hexagon.setRotate(hexagon.getRotate() + 5);
        }
        if(point.getImage() != null){
            point.setImageRotate(point.getImageRotate() + 5);
        }

    }
}


