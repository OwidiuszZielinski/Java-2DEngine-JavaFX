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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Engine extends Application {

    private static final int FPS = 60;
    private GraphicsContext gc;
    private final EngineCanvas engineCanvas = new EngineCanvas(1440, 900);
    private final Point[] lastTwoClicks = new Point[2];
    private int clickCounter = 0;
    private boolean running = true;
    private final Point point = new Point(50, 50, 40, 50, Color.BLACK, new Rectangle(50, 50));
    private final Logger logger = new Logger();
    private final BorderPane root = new BorderPane();
    private final ButtonBar buttonBar = new ButtonBar();
    private final BitmapService bitmapService = new BitmapService(engineCanvas, point, gc);
    private final PrimitiveRenderer primitiveRenderer = new PrimitiveRenderer(point, engineCanvas, lastTwoClicks);
    private final AppButton appButton = new AppButton(buttonBar, bitmapService, primitiveRenderer);


    @Override
    public void start(Stage primaryStage) {
        try {
            initGraphics(primaryStage);
            initGameLoop();
            initInputHandlers();
        } catch (Exception e) {
            //logger.logError(e);
            throw new RuntimeException(e);
        }
    }

    private void initGraphics(Stage primaryStage) {
        initButtons();
        gc = engineCanvas.getCanvas().getGraphicsContext2D();
        root.setBottom(engineCanvas.getCanvas());
        primaryStage.setScene(new Scene(root, 1440, 900));
        primaryStage.setTitle("2D Game Engine");
        primaryStage.show();
        sceneSizeListener(primaryStage);
    }

    private void initButtons() {
        appButton.addBackgroundButton();
        appButton.addSetPlayerBitmapButton();
        appButton.drawCircleButton();
        appButton.createTriangleButton();
        appButton.createSquareButton();
        appButton.createRectangleButton();
        appButton.createPolygonButton();
        appButton.createLineButton();
        appButton.createMoveOrRenderButton();
        appButton.createColorPickerButton();
        appButton.createClearCanvasButton();
        appButton.createFillButton();
        appButton.createRotateButton();
        appButton.createRunButton();
        root.setTop(buttonBar);
    }


    private void initGameLoop() {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            long lastTimeLogged = startTime;
            long frames = 0;
            while (running) {
                long currentTime = System.currentTimeMillis();
                // Update game logic
                // update();
                // Render frame
                render(point);
                frames++;
                if (currentTime - lastTimeLogged >= 1000) {
                    logger.logFrameRate(currentTime, startTime, frames);
                    lastTimeLogged = currentTime;
                    frames = 0;
                }
                long sleepTime = Math.max(0, (long) (2000 / FPS - (System.currentTimeMillis() - currentTime)));
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
        engineCanvas.getCanvas().setOnMouseClicked(event -> {
            point.setX(event.getX());
            point.setY(event.getY());
            Image image = engineCanvas.getCanvas().snapshot(null, null);
            Color color = image.getPixelReader().getColor((int) point.getX(), (int) point.getY());
            System.out.println("Kolor po kliknieciu to : " + color.toString());
            handleMouseClick(event, point);
            double mouseX = event.getX();
            double mouseY = event.getY();
            System.out.println("Mouse position : X " + mouseX + " Y : " + mouseY);
            if (point.getTool() != null) {
                if (point.getTool().getName().equals("Line")) {
                    lastTwoClicks[clickCounter % 2] = new Point(mouseX, mouseY);
                    clickCounter++;
                }
            }
        });
        engineCanvas.getCanvas().setOnKeyPressed(keyEvent -> handleKeyPress(keyEvent, point));
        engineCanvas.getCanvas().setFocusTraversable(true);
    }

    private void update() {
        try {
            String test = null;
            test.length();
        } catch (NullPointerException e) {
            logger.logError(e);
        }
    }

    private void render(Point point) {
        if (!bitmapService.isRender()) {
            bitmapService.load();
        }
        if (engineCanvas.isBackgroundSelected()) {
            gc.drawImage(engineCanvas.getBackgroundImage(), 0, 0, engineCanvas.getCanvas().getWidth(), engineCanvas.getCanvas().getHeight());
        }
        if (point != null) {
            if (point.getTool() != null && point.getTool().getName().equals("Line")) {
                if (lastTwoClicks[1] != null) {
                    primitiveRenderer.renderLine(gc, lastTwoClicks[0], lastTwoClicks[1]);
                }
            }

            if (point.getShape() != null) {
                gc.setFill(point.getTargetColor());
                Shape shape = point.getShape();
                if (shape instanceof Rectangle) {
                    primitiveRenderer.drawSquare(gc);
                }if (shape instanceof Polygon) {
                    primitiveRenderer.drawEquilateralTriangle(gc, point.getHeight());
                }if (shape instanceof Hexagon) {
                    primitiveRenderer.drawRegularHexagon(gc, point.getHeight());
                } else if (shape instanceof Circle ) {
                    primitiveRenderer.drawCircle(gc);
                }
            } else if (point.getImage() != null && point.getTool() == null) {
                primitiveRenderer.drawImage(gc);

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
        } else if (event.getCode() == KeyCode.MINUS) {
            point.setHeight(point.getHeight() - 5);
            point.setWidth(point.getWidth() - 5);
            System.out.println("Zmniejszono rozmiar : " + point.getHeight());

        } else if (event.getCode() == KeyCode.EQUALS) {
            point.setHeight(point.getHeight() + 5);
            point.setWidth(point.getWidth() + 5);
            System.out.println("Zwiekszono rozmiar : " + point.getHeight());

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