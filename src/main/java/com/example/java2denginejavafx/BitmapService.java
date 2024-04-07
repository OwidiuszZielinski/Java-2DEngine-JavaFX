package com.example.java2denginejavafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Random;

public class BitmapService {

    private BorderPane root;
    private Point point;
    private final FileChooser fileChooser = new FileChooser();

    public BitmapService(BorderPane root) {
        this.root = root;
    }

    public BitmapService(BorderPane root, Point point) {
        this.root = root;
        this.point = point;
    }

    public BitmapService() {
    }

    public Color generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256); // Losowa wartość od 0 do 255 dla kanału czerwonego
        int green = random.nextInt(256); // Losowa wartość od 0 do 255 dla kanału zielonego
        int blue = random.nextInt(256); // Losowa wartość od 0 do 255 dla kanału niebieskiego
        return Color.rgb(red, green, blue);
    }

    // Metoda konwertująca kolor do postaci heksadecymalnej
    public String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public void chooseBackground() {
        fileChooser.setTitle("Wybierz tło");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image backgroundImage = new Image(selectedFile.toURI().toString());
            root.setStyle("-fx-background-image: url('" + selectedFile.toURI().toString() + "'); " +
                    "-fx-background-size: cover;");
        }
    }
    public void choosePlayerBitmap() {
        fileChooser.setTitle("Wybierz bitmape gracza");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println(selectedFile.getName());
            point.setShape(null);
            point.setImage(new Image(selectedFile.toURI().toString()));
        }
    }
}
