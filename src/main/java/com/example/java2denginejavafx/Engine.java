package com.example.java2denginejavafx;

import com.example.java2denginejavafx.gui.AppButton;
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

    private final double circleA = 50;
    private final double circleB = 50;
    private static final int FPS = 60;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean running = true;
    private double circleX;
    private double circleY;
    private final Logger logger = new Logger();
    private final BorderPane root = new BorderPane();
    private final BackgroundService backgroundService = new BackgroundService(root);
    private final AppButton appButton = new AppButton(root, backgroundService);

    @Override
    public void start(Stage primaryStage) {
        try {
            initGraphics(primaryStage);
            initGameLoop();
            initInputHandlers();
        } catch (Exception e) {
            logger.logError(e);
            throw new RuntimeException(e);
        }
    }

    private void initGraphics(Stage primaryStage) {
        //init buttons
        appButton.addBackgroundButton();

        canvas = new Canvas(1440, 900);
        gc = canvas.getGraphicsContext2D();
        root.setCenter(canvas);
        Color randomBackgroundColor = backgroundService.generateRandomColor();
        root.setStyle("-fx-background-color: " + backgroundService.toHexString(randomBackgroundColor) + ";");

        //ustawiam scene
        primaryStage.setScene(new javafx.scene.Scene(root, 1440, 900));
        primaryStage.setTitle("2D Game Engine");
        primaryStage.show();


        sceneSizeListener(primaryStage);
    }



    private void initGameLoop() {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            long lastTimeLogged = startTime;
            long frames = 0;

            while (running) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;

                // Update game logic
                update();

                // Render frame
                render();

                frames++;

                if (currentTime - lastTimeLogged >= 1000) {
                    logger.logFrameRate(currentTime, startTime, frames);
                    lastTimeLogged = currentTime;
                    frames = 0; // Zresetuj liczbę klatek po zalogowaniu
                }

                // Sleep to meet target FPS
                long sleepTime = Math.max(0, (long) (1000 / FPS - (System.currentTimeMillis() - currentTime)));
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
        // przykladowy error ktory logujemy
        try {
            String test = null;
            test.length();
        } catch (NullPointerException e) {
            logger.logError(e);
        }
    }

    private void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLUE);
        gc.fillOval(circleX, circleY, circleA, circleB);
    }

    private void handleMouseClick(MouseEvent event) {
        circleX = event.getX();
        circleY = event.getY();
    }

    private void handleKeyPress(KeyEvent event) {
        double moveAmount = 10; // Przesunięcie o 10 pikseli
        System.out.println("X: " + circleX + " " + "Y: " + circleY);
        if (event.getCode() == KeyCode.UP) {
            if (circleY - moveAmount >= 0) {
                circleY -= moveAmount;
            }
        } else if (event.getCode() == KeyCode.DOWN) {
            if (circleY + moveAmount <= canvas.getHeight()-(circleA+appButton.getPrefHeight())) {
                circleY += moveAmount;
            }
        } else if (event.getCode() == KeyCode.LEFT) {
            if (circleX - moveAmount >= 0) {
                circleX -= moveAmount;
            }
        } else if (event.getCode() == KeyCode.RIGHT) {
            if (circleX + moveAmount <= canvas.getWidth() - (circleB)) {
                circleX += moveAmount;
            }
        } else if (event.getCode() == KeyCode.ESCAPE) {
            running = false;
            logger.closeErrorLogger();
            System.exit(0);
        }
    }



    private void sceneSizeListener(Stage primaryStage) {
        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            canvas.setWidth(newWidth.doubleValue());
        });

        primaryStage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            canvas.setHeight(newHeight.doubleValue());
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
