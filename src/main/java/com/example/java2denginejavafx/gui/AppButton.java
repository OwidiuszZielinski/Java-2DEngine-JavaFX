package com.example.java2denginejavafx.gui;

import com.example.java2denginejavafx.BitmapService;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class AppButton {
    private final ButtonBar buttonBar;
    private final double prefWidth = 120;
    private final double prefHeight = 30;
    private final BitmapService bitmapService;

    public AppButton(ButtonBar buttonBar, BitmapService bitmapService) {
        this.buttonBar = buttonBar;
        this.bitmapService = bitmapService;
    }

    public double getPrefWidth() {
        return prefWidth;
    }

    public double getPrefHeight() {
        return prefHeight;
    }

    public void addBackgroundButton() {
        Button chooseBackgroundButton = new Button("Wybierz tło");
        chooseBackgroundButton.setOnAction(event -> bitmapService.chooseBackground());
        chooseBackgroundButton.setFocusTraversable(false);
        chooseBackgroundButton.setPrefSize(prefWidth, prefHeight);
        ButtonBar.setButtonData(chooseBackgroundButton, ButtonBar.ButtonData.LEFT);
        buttonBar.getButtons().add(chooseBackgroundButton);
    }

    public void addSetPlayerBitmapButton() {
        Button chooseBackgroundButton = new Button("Bitmapa gracza");
        chooseBackgroundButton.setOnAction(event -> bitmapService.choosePlayerBitmap());
        chooseBackgroundButton.setFocusTraversable(false);
        chooseBackgroundButton.setPrefSize(prefWidth, prefHeight);
        ButtonBar.setButtonData(chooseBackgroundButton, ButtonBar.ButtonData.LEFT);
        buttonBar.getButtons().add(chooseBackgroundButton);

    }

    public void drawCircleButton() {
        Circle circle = new Circle(10); // Promień 10
        circle.setFill(null);
        circle.setStroke(Color.BLACK);
        Button chooseBackgroundButton = new Button();
        chooseBackgroundButton.setGraphic(circle);
        chooseBackgroundButton.setOnAction(event -> bitmapService.chooseBackground());
        chooseBackgroundButton.setFocusTraversable(false);
        chooseBackgroundButton.setMinWidth(30);
        chooseBackgroundButton.setMaxWidth(30);
        chooseBackgroundButton.setMaxHeight(30);
        buttonBar.getButtons().add(chooseBackgroundButton);

    }

    public void createTriangleButton() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                0.0, 15.0,                // Lewy dolny róg
                (double) (10 / 2), 0.0,        // Wierzchołek trójkąta
                15.0, 15.0          // Prawy dolny róg
        );
        triangle.setFill(null);
        triangle.setStroke(Color.BLACK);
        Button chooseTriangleButton = new Button();
        chooseTriangleButton.setGraphic(triangle);
        //chooseTriangleButton.setOnAction(event -> bitmapService.chooseTriangle());
        chooseTriangleButton.setFocusTraversable(false);
        chooseTriangleButton.setMinHeight(30);
        chooseTriangleButton.setMaxWidth(50);
        buttonBar.getButtons().add(chooseTriangleButton);
    }

    // Metoda tworząca przycisk z kwadratem
    public void createSquareButton() {
        Rectangle square = new Rectangle(15, 15);
        square.setFill(null);
        square.setStroke(Color.BLACK);

        Button chooseSquareButton = new Button();
        chooseSquareButton.setGraphic(square);
        //chooseSquareButton.setOnAction(event -> bitmapService.chooseSquare());
        chooseSquareButton.setFocusTraversable(false);
        chooseSquareButton.setMinHeight(30);
        chooseSquareButton.setMaxWidth(50);
        buttonBar.getButtons().add(chooseSquareButton);
    }

    // Metoda tworząca przycisk z prostokątem
    public void createRectangleButton() {
        Rectangle rectangle = new Rectangle(35, 15);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);

        Button chooseRectangleButton = new Button();
        chooseRectangleButton.setGraphic(rectangle);
        //chooseRectangleButton.setOnAction(event -> bitmapService.chooseRectangle());
        chooseRectangleButton.setFocusTraversable(false);
        chooseRectangleButton.setMinHeight(30);
        chooseRectangleButton.setMaxWidth(50);
        buttonBar.getButtons().add(chooseRectangleButton);
    }

    // Metoda tworząca przycisk z wielokątem (np. sześciokątem)
    public void createPolygonButton() {
        Polygon polygon = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI * i / 6;
            double x = 10 * Math.cos(angle);
            double y = 10 * Math.sin(angle);
            polygon.getPoints().addAll(x, y);
        }
        polygon.setFill(null);
        polygon.setStroke(Color.BLACK);

        Button choosePolygonButton = new Button();
        choosePolygonButton.setGraphic(polygon);
        //choosePolygonButton.setOnAction(event -> bitmapService.choosePolygon());
        choosePolygonButton.setFocusTraversable(false);
        choosePolygonButton.setMinHeight(30);
        choosePolygonButton.setMaxWidth(50);
        buttonBar.getButtons().add(choosePolygonButton);
    }


}
