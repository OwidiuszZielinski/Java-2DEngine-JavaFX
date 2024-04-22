package com.example.java2denginejavafx.game;

import com.example.java2denginejavafx.BitmapService;
import com.example.java2denginejavafx.Point;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends Application {
    private static final int SCENE_WIDTH = 1440;
    private static final int SCENE_HEIGHT = 900;
    private static final int PLAYER_SIZE = 20;
    private static final int POINT_SIZE = 10;
    private static final int PLAYER_SPEED = 1;

    private Rectangle player = new Rectangle(10,10);
    private Rectangle point;
    private int score = 0;
    private List<Rectangle> snakeParts = new ArrayList<>();
    private Direction currentDirection = Direction.RIGHT;

    public Game() {



    }

    @Override
    public void start(Stage primaryStage) {
        // Tworzenie płótna do rysowania
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        // Ścieżka do pliku obrazka tła
        String imagePath = "src/main/resources/canvas.png";
        Image backgroundImage = new Image(new File(imagePath).toURI().toString());

        BackgroundImage b= new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Background background = new Background(b);

        root.setBackground(background);

        player.setX((SCENE_WIDTH - PLAYER_SIZE) / 2);
        player.setY((SCENE_HEIGHT - PLAYER_SIZE) / 2);
        snakeParts.add(player);

        // Tworzenie punktu
        point = new Rectangle(POINT_SIZE, POINT_SIZE, Color.RED);
        generatePoint();

        // Dodanie gracza i punktu do płótna
        root.getChildren().addAll(player, point);

        // Obsługa ruchu gracza
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (currentDirection != Direction.DOWN)
                        currentDirection = Direction.UP;
                    break;
                case DOWN:
                    if (currentDirection != Direction.UP)
                        currentDirection = Direction.DOWN;
                    break;
                case LEFT:
                    if (currentDirection != Direction.RIGHT)
                        currentDirection = Direction.LEFT;
                    break;
                case RIGHT:
                    if (currentDirection != Direction.LEFT)
                        currentDirection = Direction.RIGHT;
                    break;
            }
        });

        // Animacja gry - ruch węża
        AnimationTimer timer = new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    moveSnake();
                    lastUpdate = now;
                }
                // Sprawdzenie, czy gracz wychodzi poza granice sceny
                if (player.getX() < 0 || player.getX() >= SCENE_WIDTH - PLAYER_SIZE) {
                    currentDirection = (currentDirection == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT; // Obróć kierunek poziomy
                    moveSnake(); // Aktualizuj pozycję gracza po zmianie kierunku ruchu
                }

                if (player.getY() < 0 || player.getY() >= SCENE_HEIGHT - PLAYER_SIZE) {
                    currentDirection = (currentDirection == Direction.UP) ? Direction.DOWN : Direction.UP; // Obróć kierunek pionowy
                    moveSnake(); // Aktualizuj pozycję gracza po zmianie kierunku ruchu
                }
            }
        };
        timer.start();

        // Wyświetlenie sceny
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Metoda do przesuwania węża
    private void moveSnake() {
        switch (currentDirection) {
            case UP:
                player.setY(player.getY() - PLAYER_SPEED);
                break;
            case DOWN:
                player.setY(player.getY() + PLAYER_SPEED);
                break;
            case LEFT:
                player.setX(player.getX() - PLAYER_SPEED);
                break;
            case RIGHT:
                player.setX(player.getX() + PLAYER_SPEED);
                break;
        }

        // Sprawdzenie kolizji z punktem
        if (player.getBoundsInParent().intersects(point.getBoundsInParent())) {
            score++;
            generatePoint();
            //addSnakePart();
            System.out.println(score);
        }
    }

    // Metoda do generowania punktu w losowej lokalizacji na planszy
    private void generatePoint() {
        Random random = new Random();
        double newX = random.nextInt((int) (SCENE_WIDTH - POINT_SIZE));
        double newY = random.nextInt((int) (SCENE_HEIGHT - POINT_SIZE));
        point.setX(newX);
        point.setY(newY);
    }

    // Metoda do dodawania części węża
    private void addSnakePart() {
        Rectangle newPart = new Rectangle(PLAYER_SIZE, PLAYER_SIZE, Color.BLUE);
        newPart.setX(player.getX());
        newPart.setY(player.getY());
       // snakeParts.add(newPart);
    }

    public static void main(String[] args) {
        launch(args);
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
