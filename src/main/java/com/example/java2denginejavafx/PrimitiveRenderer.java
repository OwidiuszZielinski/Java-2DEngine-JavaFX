package com.example.java2denginejavafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * Klasa PrimitiveRenderer zawiera metody do renderowania podstawowych kształtów oraz operacje na punktach.
 */
public class PrimitiveRenderer {

    private final Point point;

    /**
     * Konstruktor klasy PrimitiveRenderer.
     *
     * @param point punkt do renderowania
     */
    public PrimitiveRenderer(Point point) {
        this.point = point;
    }

    /**
     * Metoda chooseDrawCircle ustawia punkt na rysowanie okręgu.
     */
    public void chooseDrawCircle() {
        point.setTool(null);
        point.setShape(new Circle(point.getHeight()));
    }

    /**
     * Metoda chooseDrawSquare ustawia punkt na rysowanie kwadratu.
     */
    public void chooseDrawSquare() {
        point.setTool(null);
        point.setShape(new Rectangle(point.getHeight(), point.getHeight()));
    }

    /**
     * Metoda chooseDrawPolygon ustawia punkt na rysowanie wielokąta.
     */
    public void chooseDrawPolygon() {
        point.setTool(null);
        point.setShape(new Polygon());
    }

    /**
     * Metoda chooseDrawRectangle ustawia punkt na rysowanie prostokąta.
     */
    public void chooseDrawRectangle() {
        point.setTool(null);
        point.setShape(new Rectangle(point.getHeight() * 2, point.getHeight()));
    }

    /**
     * Metoda chooseDrawHexagon ustawia punkt na rysowanie sześciokąta.
     */
    public void chooseDrawHexagon() {
        point.setTool(null);
        point.setShape(new Hexagon());
    }

    /**
     * Metoda chooseDrawLine ustawia narzędzie na rysowanie linii.
     */
    public void chooseDrawLine() {
        point.setShape(null);
        point.setTool(new Tool("Line"));
    }

    /**
     * Metoda drawEquilateralTriangle rysuje trójkąt równoboczny.
     *
     * @param gc          kontekst graficzny
     * @param sideLength  długość boku trójkąta
     */
    public void drawEquilateralTriangle(GraphicsContext gc, double sideLength) {
        double height = sideLength * Math.sqrt(3) / 2;
        double x1 = point.getX() - sideLength / 2;
        double y1 = point.getY() + height / 2;
        double x2 = x1 + sideLength;
        double y2 = y1;
        double x3 = point.getX();
        double y3 = point.getY() - height / 2;
        gc.setFill(point.getTargetColor());
        gc.fillPolygon(new double[]{x1, x2, x3}, new double[]{y1, y2, y3}, 3);
    }

    /**
     * Metoda drawRegularHexagon rysuje sześciokąt foremny.
     *
     * @param gc         kontekst graficzny
     * @param sideLength długość boku sześciokąta
     */
    public void drawRegularHexagon(GraphicsContext gc, double sideLength) {
        double[] xPoints = new double[6];
        double[] yPoints = new double[6];
        double rotate = 0;
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

    /**
     * Metoda drawSquare rysuje kwadrat.
     *
     * @param gc kontekst graficzny
     */
    public void drawSquare(GraphicsContext gc) {
        double x = point.getX() - point.getWidth() / 2;
        double y = point.getY() - point.getHeight() / 2;
        if (point.getShape() instanceof Rectangle rectangle) {
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

    /**
     * Metoda renderLine rysuje linię między dwoma punktami.
     *
     * @param gc kontekst graficzny
     * @param a  pierwszy punkt
     * @param b  drugi punkt
     */
    public void renderLine(GraphicsContext gc, Point a, Point b) {
        point.setShape(null);
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }

    /**
     * Metoda setColor ustawia kolor rysowania.
     *
     * @param selectedColor wybrany kolor
     */
    public void setColor(Color selectedColor) {
        point.setTargetColor(selectedColor);
    }

    /**
     * Metoda drawCircle rysuje okrąg.
     *
     * @param gc kontekst graficzny
     */
    public void drawCircle(GraphicsContext gc) {
        if (point.getShape() instanceof Circle circle) {
            double x = point.getX() - point.getWidth() / 2;
            double y = point.getY() - point.getHeight() / 2;
            gc.fillOval(x, y, circle.getRadius(), circle.getRadius());
        }
    }

    /**
     * Metoda rotateObject obraca obiekt o 5 stopni.
     */
    public void rotateObject() {
        rotatePoint();
    }

    /**
     * Metoda rotatePoint obraca punkt o 5 stopni.
     */
    private void rotatePoint() {
        if (point.getShape() instanceof Rectangle rectangle) {
            rectangle.setRotate(rectangle.getRotate() + 5);
        }
        if (point.getShape() instanceof Hexagon hexagon) {
            hexagon.setRotate(hexagon.getRotate() + 5);
        }
        if (point.getImage() != null) {
            point.setImageRotate(point.getImageRotate() + 5);
        }
    }
}


