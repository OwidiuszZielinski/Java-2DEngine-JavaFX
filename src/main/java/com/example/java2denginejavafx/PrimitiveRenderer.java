package com.example.java2denginejavafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PrimitiveRenderer {

    private Point point;
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
        drawShape();

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



    public void drawShape() {
        System.out.println("Wybrano rysowanie : ");
        if (point.getShape() != null) {
            // Rysowanie kształtu
            GraphicsContext gc = canvas.getCanvas().getGraphicsContext2D();
            gc.setFill(point.getColor());
            Shape shape = point.getShape();
            if (shape instanceof Rectangle rectangle) {
                System.out.println("Kwadrat");
                gc.fillRect(point.getX(), point.getY(), point.getWidth(), point.getHeight());
            }
            if (shape instanceof Polygon polygon) {
                System.out.println("Trójkąt");
                drawEquilateralTriangle(gc, point.getHeight());

            }
            if (shape instanceof Hexagon hexagon) {
                System.out.println("Hexagon");
                drawRegularHexagon(gc, point.getHeight());
            } else if (shape instanceof Circle circle) {
                System.out.println("Okrąg");
                gc.fillOval(point.getX(), point.getY(), point.getHeight(), point.getHeight());
            }
        }
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
        gc.setFill(Color.BLACK);
        gc.fillPolygon(new double[]{ x1, x2, x3 }, new double[]{ y1, y2, y3 }, 3);
    }

    public void drawRegularHexagon(GraphicsContext gc, double sideLength) {
        double[] xPoints = new double[6];
        double[] yPoints = new double[6];

        for (int i = 0; i < 6; i++) {
            double angleRad = Math.toRadians(60 * i);
            xPoints[i] = point.getX() + sideLength * Math.cos(angleRad);
            yPoints[i] = point.getY() + sideLength * Math.sin(angleRad);
        }

        gc.setFill(Color.BLACK);
        gc.fillPolygon(xPoints, yPoints, 6);
    }

    public void renderLine(GraphicsContext gc, Point a, Point b) {
        point.setShape(null);
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }

    public void drawLine() {
        renderLine(canvas.getCanvas().getGraphicsContext2D(),lastTwoClicks[0],lastTwoClicks[1]);
    }
}
