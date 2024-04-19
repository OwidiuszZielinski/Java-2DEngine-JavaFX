package com.example.java2denginejavafx.gui;

import com.example.java2denginejavafx.BitmapService;
import com.example.java2denginejavafx.PrimitiveRenderer;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class AppButton {
    private final ButtonBar buttonBar;
    private final double prefWidth = 120;
    private final double prefHeight = 30;
    private final BitmapService bitmapService;

    private final PrimitiveRenderer primitiveRenderer;

    public AppButton(ButtonBar buttonBar, BitmapService bitmapService, PrimitiveRenderer primitiveRenderer) {
        this.buttonBar = buttonBar;
        this.bitmapService = bitmapService;
        this.primitiveRenderer = primitiveRenderer;
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
        chooseBackgroundButton.setOnAction(event -> primitiveRenderer.chooseDrawCircle());
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
        chooseTriangleButton.setOnAction(event -> primitiveRenderer.chooseDrawPolygon());
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

        Button button = new Button();
        button.setGraphic(square);
        button.setOnAction(event -> primitiveRenderer.chooseDrawSquare());
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(50);
        buttonBar.getButtons().add(button);
    }

    // Metoda tworząca przycisk z prostokątem
    public void createRectangleButton() {
        Rectangle rectangle = new Rectangle(35, 15);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);

        Button button = new Button();
        button.setGraphic(rectangle);
        button.setOnAction(event -> primitiveRenderer.chooseDrawRectangle());
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(50);
        buttonBar.getButtons().add(button);
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

        Button button = new Button();
        button.setGraphic(polygon);
        button.setOnAction(event -> primitiveRenderer.chooseDrawHexagon());
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(50);
        buttonBar.getButtons().add(button);
    }
    public void createLineButton() {
        Button button = new Button();
        button.setGraphic(new Rectangle(35.0,1.0));
        button.setOnAction(event -> primitiveRenderer.chooseDrawLine());
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(50);
        buttonBar.getButtons().add(button);
    }

    public void createMoveOrRenderButton(){
        Button button = new Button("Render");
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(50);
        buttonBar.getButtons().add(button);

        button.setOnAction(event -> {
            // Zmiana tekstu na przycisku
            if (button.getText().equals("Render")) {
                button.setText("Move");
                bitmapService.setRender(true);
            } else {

                button.setText("Render");
                bitmapService.setRender(false);
                //Napisz metode ktora zapisze aktualne tlo do tla i wczyta na tlo
                bitmapService.save();

            }
            // Zablokowanie obsługi zdarzenia po pierwszym kliknięciu
        });



    }


}
