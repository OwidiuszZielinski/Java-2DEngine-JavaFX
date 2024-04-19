package com.example.java2denginejavafx;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BitmapService {

    private EngineCanvas workPlace;
    private Point point;
    private final FileChooser fileChooser = new FileChooser();

    public GraphicsContext getBackground() {
        return background;
    }

    public void setBackground(GraphicsContext background) {
        this.background = background;
    }

    private GraphicsContext background;

    public BitmapService(EngineCanvas workPlace, Point point, GraphicsContext background) {
        this.workPlace = workPlace;
        this.point = point;
        this.background = background;

    }


    private boolean render;

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public BitmapService(EngineCanvas workPlace, Point point) {
        this.workPlace = workPlace;
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
            GraphicsContext gc = workPlace.getCanvas().getGraphicsContext2D();
            // Wyczyść obszar Canvas
            gc.clearRect(0, 0, workPlace.getCanvas().getWidth(), workPlace.getCanvas().getHeight());
            // Narysuj obraz jako tło
            gc.drawImage(backgroundImage, 0, 0, workPlace.getCanvas().getWidth(), workPlace.getCanvas().getHeight());
            workPlace.setBackgroundSelected(true);
            workPlace.setBackgroundImage(backgroundImage);
        }
    }
    public void chooseBackgroundFromFile() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/canvas.png"));

            // Konwersja BufferedImage na WritableImage
            WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);

            // Pobranie obiektu Canvas z workPlace (zakładam, że workPlace jest instancją klasy zawierającej Canvas)
            Canvas canvas = workPlace.getCanvas();

            // Ustawienie zawartości Canvas na wczytany obraz
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(writableImage, 0, 0, canvas.getWidth(), canvas.getHeight());

            System.out.println("Obraz został wczytany i ustawiony jako zawartość Canvas.");
        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania obrazu: " + e.getMessage());
        }
    }

    public void choosePlayerBitmap() {
        fileChooser.setTitle("Wybierz bitmape gracza");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println(selectedFile.getName());
            point.setShape(null);
            point.setTool(null);
            point.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    public void clear(){
       GraphicsContext gc =  workPlace.getCanvas().getGraphicsContext2D();


    }

    public void save() {
        // Utworzenie obiektu WritableImage i zapisanie na nim zawartości GraphicsContext
        WritableImage writableImage = new WritableImage((int) workPlace.getCanvas().getWidth(), (int) workPlace.getCanvas().getHeight());
        workPlace.getCanvas().snapshot(null, writableImage);

// Konwersja WritableImage na BufferedImage
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);

// Zapisanie BufferedImage do pliku (np. PNG)
        File outputFile = new File("src/main/resources/canvas.png");
        try {
            ImageIO.write(bufferedImage, "png", outputFile);
            System.out.println("Obraz został zapisany do pliku.");
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu obrazu: " + e.getMessage());
        }

    }
    public void load(){
        chooseBackgroundFromFile();
    }
}
