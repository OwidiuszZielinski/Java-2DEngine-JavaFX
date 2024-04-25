package com.example.java2denginejavafx.gui;

import com.example.java2denginejavafx.BitmapService;
import com.example.java2denginejavafx.PrimitiveRenderer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        Circle circle = new Circle(10);
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
                0.0, 15.0,
                (double) (10 / 2), 0.0,
                15.0, 15.0
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
        button.setGraphic(new Rectangle(35.0, 1.0));
        button.setOnAction(event -> primitiveRenderer.chooseDrawLine());
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(50);
        buttonBar.getButtons().add(button);
    }

    public void createMoveOrRenderButton() {
        Button button = new Button("Render");
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(50);
        buttonBar.getButtons().add(button);
        button.setOnAction(event -> {
            if (button.getText().equals("Render")) {
                button.setText("Move");
                bitmapService.setRender(true);
            } else {
                button.setText("Render");
                bitmapService.setRender(false);
                bitmapService.save();
            }
        });
    }
    public void createColorPickerButton() {
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.CORAL);
        colorPicker.setMaxHeight(30);
        colorPicker.setFocusTraversable(false);
        final Text text = new Text("Try the color picker!");
        text.setFont(Font.font("Verdana", 20));
        text.setFill(colorPicker.getValue());
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color selectedColor = colorPicker.getValue();
                primitiveRenderer.setColor(selectedColor);
                text.setFill(colorPicker.getValue());
            }
        });
        buttonBar.getButtons().add(colorPicker);
    }

    public void createClearCanvasButton() {
        Button button = new Button("Clear");
        button.setOnAction(event -> clearCanvas());
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(50);
        buttonBar.getButtons().add(button);

    }

    public void createRunButton() {
        Button button = new Button();
        Image image = loadImage("src/main/resources/run.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20); // Ustawienie szerokości obrazu na 30
        imageView.setFitHeight(20); // Ustawienie wysokości obrazu na 30
        imageView.setPreserveRatio(true); // Zachowanie proporcji obrazu
        button.setGraphic(imageView);
        button.setOnAction(event -> {
            bitmapService.run();
        });
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(30);
        buttonBar.getButtons().add(button);
    }

    public void createBitmapPlayerButton() {
        Button button = new Button();
        Image image = loadImage("src/main/resources/boy_up_1.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20); // Ustawienie szerokości obrazu na 30
        imageView.setFitHeight(20); // Ustawienie wysokości obrazu na 30
        imageView.setPreserveRatio(true); // Zachowanie proporcji obrazu
        button.setGraphic(imageView);
        button.setOnAction(event -> {
            bitmapService.switchToAnimation();
        });
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(30);
        buttonBar.getButtons().add(button);
    }

    public void createFillButton() {
        Button button = new Button();
        Image image = loadImage("src/main/resources/fill.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        button.setOnAction(event -> {
            //Dopisac wypelnianie
            //bitmapService.fill();
        });
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(30);
        buttonBar.getButtons().add(button);

    }

    public void createRotateButton() {
        Button button = new Button();
        Image image = loadImage("src/main/resources/rotate.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20); // Ustawienie szerokości obrazu na 30
        imageView.setFitHeight(20); // Ustawienie wysokości obrazu na 30
        imageView.setPreserveRatio(true); // Zachowanie proporcji obrazu
        button.setGraphic(imageView);
        button.setOnAction(event -> {
            primitiveRenderer.rotateObject();
        });
        button.setFocusTraversable(false);
        button.setMinHeight(30);
        button.setMaxWidth(30);
        buttonBar.getButtons().add(button);
    }

    private void clearCanvas() {
        bitmapService.clear();
    }

    public Image loadImage(String pathname) {
        File file = new File(pathname);
        if (!file.exists()) {
            System.out.println("Plik " + file.getPath() + " nie istnieje.");
            return null;
        }
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Nie udało się wczytać obrazu z pliku: " + e.getMessage());
            return null;
        }
        if (bufferedImage == null) {
            System.out.println("Nie udało się wczytać obrazu z pliku.");
            return null;
        }
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

}
