package com.example.java2denginejavafx;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Engine extends Application {
    private static final int FPS = 60;
    private BorderPane root;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean running = true;
    private double circleX;
    private double circleY;
    ErrorLogger logger = new ErrorLogger();

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
        root = new BorderPane();
        Button chooseBackgroundButton = new Button("Wybierz tło");
        chooseBackgroundButton.setOnAction(event -> chooseBackground());
        chooseBackgroundButton.setFocusTraversable(false);
        root.setTop(chooseBackgroundButton);
        canvas = new Canvas(1440, 900);
        gc = canvas.getGraphicsContext2D();
        root.setCenter(canvas);
        Color randomBackgroundColor = generateRandomColor();

        // Ustawienie losowego koloru tła dla sceny
        root.setStyle("-fx-background-color: " + toHexString(randomBackgroundColor) + ";");

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

    private void initGameLoop() {
        long startTime = System.currentTimeMillis();
        new Thread(() -> {
            long lastTimeLogged = startTime;
            long frames = 0;
            while (running) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                update();
                render();
                long sleepTime = 1000 / FPS - (currentTime - startTime) % (1000 / FPS);
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        logger.logError(e);
                    }
                }

                // Logowanie czasu co sekundę lub razem z klatkami
                frames++;
                if (currentTime - lastTimeLogged >= 1000) {
                    lastTimeLogged = currentTime;
                    long seconds = elapsedTime / 1000;
                    System.out.println("Czas: " + seconds + " sek., Klatki: " + frames);
                    frames = 0;
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
            logger.closeErrorLogger();
            System.exit(0);
        }
    }

    private Color generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256); // Losowa wartość od 0 do 255 dla kanału czerwonego
        int green = random.nextInt(256); // Losowa wartość od 0 do 255 dla kanału zielonego
        int blue = random.nextInt(256); // Losowa wartość od 0 do 255 dla kanału niebieskiego
        return Color.rgb(red, green, blue);
    }

    // Metoda konwertująca kolor do postaci heksadecymalnej
    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private void chooseBackground() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz tło");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image backgroundImage = new Image(selectedFile.toURI().toString());
            root.setStyle("-fx-background-image: url('" + selectedFile.toURI().toString() + "'); " +
                    "-fx-background-size: cover;");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
