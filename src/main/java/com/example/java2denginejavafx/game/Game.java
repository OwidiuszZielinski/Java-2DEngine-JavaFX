package com.example.java2denginejavafx.game;

import com.example.java2denginejavafx.BitmapService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

public class Game extends Application {
    private static final int SCENE_WIDTH = 1440;
    private static final int SCENE_HEIGHT = 900;
    private static final int PLAYER_SIZE = 50;
    private static final int POINT_SIZE = 25;
    private static int PLAYER_SPEED = 1;

    private ImageView playerImageView;
    private Rectangle pointRectangle;
    private int score = 0;
    private final Label scoreLabel = new Label("Score: 0");
    private final Label timeLabel = new Label("Time: 0");
    private final long startTime = System.nanoTime();
    private Direction currentDirection = Direction.RIGHT;
    private final ButtonBar buttonBar = new ButtonBar();

    private final BitmapService bitmapService;

    public Game(BitmapService bitmapService) {
        this.bitmapService = bitmapService;
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        Font.loadFont(getClass().getResourceAsStream("/PressStart2P-Regular.ttf"), 24);
        scoreLabel.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 24;");
        timeLabel.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 24;");
        scoreLabel.setPrefWidth(300);
        buttonBar.getButtons().addAll(scoreLabel, timeLabel);
        root.setTop(buttonBar);

        String imagePath = "src/main/resources/canvas.png";
        Image backgroundImage = new Image(new File(imagePath).toURI().toString());
        BackgroundImage b = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(b);
        root.setBackground(background);
        Image player = bitmapService.copyOfPoint().getImage();
        if (player == null) {
            playerImageView = new ImageView(new Image(
                    new File("src/main/resources/toppng.com-mario-clipart-super-mario-bros-clip-art-images-super-mario-64-ds-artwork-420x790.png").toURI()
                            .toString()));
            set();
            root.getChildren().add(playerImageView);
        } else {
            playerImageView = new ImageView(player);
            set();
            root.getChildren().add(playerImageView);
        }
        pointRectangle = new Rectangle(POINT_SIZE, POINT_SIZE, Color.RED);
        generatePoint();
        root.getChildren().add(pointRectangle);

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

        AnimationTimer timer = new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    moveSnake();
                    lastUpdate = now;
                }

                if (playerImageView.getX() < 0 || playerImageView.getX() >= SCENE_WIDTH - PLAYER_SIZE) {
                    currentDirection = (currentDirection == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT;
                    moveSnake();
                }

                if (playerImageView.getY() < 0 || playerImageView.getY() >= SCENE_HEIGHT - PLAYER_SIZE) {
                    currentDirection = (currentDirection == Direction.UP) ? Direction.DOWN : Direction.UP;
                    moveSnake();
                }
                long elapsedTime = (System.nanoTime() - startTime) / 1_000_000_000;
                timeLabel.setText("Time: " + elapsedTime);
            }
        };
        timer.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void moveSnake() {
        switch (currentDirection) {
            case UP:
                playerImageView.setY(playerImageView.getY() - PLAYER_SPEED);
                break;
            case DOWN:
                playerImageView.setY(playerImageView.getY() + PLAYER_SPEED);
                break;
            case LEFT:
                playerImageView.setX(playerImageView.getX() - PLAYER_SPEED);
                break;
            case RIGHT:
                playerImageView.setX(playerImageView.getX() + PLAYER_SPEED);
                break;
        }

        if (playerImageView.getBoundsInParent().intersects(pointRectangle.getBoundsInParent())) {
            score++;
            PLAYER_SPEED++;
            generatePoint();
            scoreLabel.setText("Score: " + score);
            System.out.println(score);
        }
    }

    private void generatePoint() {
        Random random = new Random();
        double newX = random.nextInt((int) (SCENE_WIDTH - POINT_SIZE));
        double newY = random.nextInt((int) (SCENE_HEIGHT - POINT_SIZE));
        pointRectangle.setX(newX);
        pointRectangle.setY(newY);
    }

    private void set() {
        playerImageView.setFitWidth(PLAYER_SIZE);
        playerImageView.setFitHeight(PLAYER_SIZE);
        playerImageView.setX((double) (SCENE_WIDTH - PLAYER_SIZE) / 2);
        playerImageView.setY((double) (SCENE_HEIGHT - PLAYER_SIZE) / 2);
    }

    public static void main(String[] args) {
        launch(args);
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}