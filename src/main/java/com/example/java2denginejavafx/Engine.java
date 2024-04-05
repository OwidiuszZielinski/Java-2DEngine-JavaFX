package com.example.java2denginejavafx;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Engine extends Application {
    private static final int FPS = 60;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean running = true;
    private double circleX;
    private double circleY;

    @Override
    public void start(Stage primaryStage) {
        try {
            initGraphics(primaryStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        initGameLoop();
        initInputHandlers();
    }

    private void initGraphics(Stage primaryStage) {
        BorderPane root = new BorderPane();
        canvas = new Canvas(1440,900);
        gc = canvas.getGraphicsContext2D();
        root.setCenter(canvas);
        drawBorder(gc);
        primaryStage.setScene(new javafx.scene.Scene(root, 1440, 900)); // Domyślny rozmiar sceny
        primaryStage.setTitle("2D Game Engine");
        primaryStage.show();

        // Nasłuchiwacz na zmianę rozmiaru sceny
        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            canvas.setWidth(newWidth.doubleValue());

        });

        primaryStage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            canvas.setHeight(newHeight.doubleValue());
        });
    }

    private void drawBorder(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void initGameLoop() {
        new Thread(() -> {
            while (running) {
                long startTime = System.currentTimeMillis();
                update();
                render();
                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = 1000 / FPS - elapsedTime;
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initInputHandlers() {
        canvas.setOnMouseClicked(this::handleMouseClick);
        canvas.setOnKeyPressed(this::handleKeyPress);
        canvas.setFocusTraversable(true);
    }

    private void update() {
    }

    private void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLUE);
        gc.fillOval(circleX, circleY, 30, 30);
    }

    private void handleMouseClick(MouseEvent event) {
        circleX = event.getX();
        circleY = event.getY();
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            circleY -= 10;
        } else if (event.getCode() == KeyCode.DOWN) {
            circleY += 10;
        } else if (event.getCode() == KeyCode.LEFT) {
            circleX -= 10;
        } else if (event.getCode() == KeyCode.RIGHT) {
            circleX += 10;
        } else if (event.getCode() == KeyCode.ESCAPE) {
            running = false;
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
