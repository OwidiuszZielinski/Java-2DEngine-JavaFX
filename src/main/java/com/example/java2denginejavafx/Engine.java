package com.example.java2denginejavafx;

import com.example.java2denginejavafx.gui.AppButton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Engine extends Application {
    private static final int FPS = 60;
    private final EngineCanvas engineCanvas = new EngineCanvas(1440, 900);
    private GraphicsContext gc;
    private boolean running = true;
    private final Point point = new Point(0, 0, 30, 30, Color.BLACK, new Rectangle(20, 20));
    private final Logger logger = new Logger();
    private final BorderPane root = new BorderPane();
    private final ButtonBar buttonBar = new ButtonBar();
    private final BitmapService bitmapService = new BitmapService(engineCanvas, point);
    private final AppButton appButton = new AppButton(buttonBar, bitmapService);

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
        appButton.addSetPlayerBitmapButton();
        appButton.drawCircleButton();
        appButton.createTriangleButton();
        appButton.createSquareButton();
        appButton.createRectangleButton();
        appButton.createPolygonButton();
        root.setTop(buttonBar);
        gc = engineCanvas.getCanvas().getGraphicsContext2D();
        root.setCenter(engineCanvas.getCanvas());
        Color randomBackgroundColor = bitmapService.generateRandomColor();
        root.setStyle("-fx-background-color: " + bitmapService.toHexString(randomBackgroundColor) + ";");

        //ustawiam scene
        primaryStage.setScene(new Scene(root, 1440, 900));

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
                render(point);

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
        engineCanvas.getCanvas().setOnMouseClicked(mouseEvent -> handleMouseClick(mouseEvent, point));
        engineCanvas.getCanvas().setOnKeyPressed(keyEvent -> handleKeyPress(keyEvent, point));
        engineCanvas.getCanvas().setFocusTraversable(true);
    }

    private void update() {
        try {
            //sprawdzenie dzialania loggera
            String test = null;
            test.length();
        } catch (NullPointerException e) {
            logger.logError(e);
        }
    }

    private void render(Point point) {
        gc.clearRect(0, 0, engineCanvas.getCanvas().getWidth(), engineCanvas.getCanvas().getHeight());

        if (engineCanvas.isBackgroundSelected()) {
            // Rysowanie tła
            gc.drawImage(engineCanvas.getBackgroundImage(), 0, 0, engineCanvas.getCanvas().getWidth(), engineCanvas.getCanvas().getHeight());
        }

        if (point != null) {
            // Rysowanie punktu
            if (point.getShape() != null) {
                // Rysowanie kształtu
                gc.setFill(point.getColor());
                Shape shape = point.getShape();
                if (shape instanceof Rectangle rectangle) {
                    gc.fillRect(point.getX(), point.getY(), rectangle.getWidth(), rectangle.getHeight());
                } else if (shape instanceof Circle circle) {
                    gc.fillOval(point.getX(), point.getY(), circle.getRadius(), circle.getRadius());
                }
            } else if (point.getImage() != null) {
                // Rysowanie bitmapy
                Image image = point.getImage();
                double width = Math.min(image.getWidth(), point.getWidth());
                double height = Math.min(image.getHeight(), point.getHeight());
                gc.drawImage(image, point.getX(), point.getY(), width, height);
            }
        }
    }


    private void handleMouseClick(MouseEvent event, Point point) {
        point.setX(event.getX());
        point.setY(event.getY());
        render(point);
    }

    private void handleKeyPress(KeyEvent event, Point point) {
        double moveAmount = 10;
        //System.out.println("X: " + point.getX() + " " + "Y: " + point.getY());
        if (event.getCode() == KeyCode.UP) {
            if (point.getY() - moveAmount >= 0) {
                point.setY(point.getY() - moveAmount);
                render(point);
            }
        } else if (event.getCode() == KeyCode.DOWN) {
            if (point.getY() + moveAmount <= engineCanvas.getCanvas().getHeight() - point.getHeight()) {
                point.setY(point.getY() + moveAmount);
                render(point);
            }
        } else if (event.getCode() == KeyCode.LEFT) {
            if (point.getX() - moveAmount >= 0) {
                point.setX(point.getX() - moveAmount);
                render(point);
            }
        } else if (event.getCode() == KeyCode.RIGHT) {
            if (point.getX() + moveAmount <= engineCanvas.getCanvas().getWidth() - point.getWidth()) {
                point.setX(point.getX() + moveAmount);
                render(point);
            }
        } else if (event.getCode() == KeyCode.ESCAPE) {
            running = false;
            logger.closeErrorLogger();
            System.exit(0);
        }
    }


    private void sceneSizeListener(Stage primaryStage) {
        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            engineCanvas.getCanvas().setWidth(newWidth.doubleValue());
        });

        primaryStage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            engineCanvas.getCanvas().setHeight(newHeight.doubleValue());
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
