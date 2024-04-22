package com.example.java2denginejavafx;

import com.example.java2denginejavafx.game.Game;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

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
            // Sprawdź, czy plik istnieje
            File file = new File("src/main/resources/canvas.png");
            if (!file.exists()) {
                System.out.println("Plik " + file.getPath() + " nie istnieje.");
                return;
            }

            // Wczytaj obraz z pliku
            BufferedImage bufferedImage = ImageIO.read(file);

            // Sprawdź, czy udało się wczytać obraz
            if (bufferedImage == null) {
                System.out.println("Nie udało się wczytać obrazu z pliku.");
                return;
            }

            // Konwertuj BufferedImage do javafx.scene.image.Image
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            // Pobierz kanwę i kontekst graficzny
            Canvas canvas = workPlace.getCanvas();
            GraphicsContext gc = canvas.getGraphicsContext2D();

            // Wyczyść kanwę
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // Narysuj obraz na kanwie
            gc.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());

            // Zwolnij zasoby
            bufferedImage = null;
            image = null;
        } catch (IOException e) {
            // Obsłuż wyjątek IO
            System.out.println("Błąd podczas wczytywania obrazu: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Obsłuż inne wyjątki
            System.out.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
            e.printStackTrace();
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

    public void clear() {
        workPlace.getCanvas().getGraphicsContext2D().clearRect(0, 0, workPlace.getCanvas().getWidth(), workPlace.getCanvas().getHeight());
        save();
        workPlace.setBackgroundSelected(false);
        load();

    }


    public void save() {
        WritableImage writableImage = new WritableImage((int) workPlace.getCanvas().getWidth(), (int) workPlace.getCanvas().getHeight());
        workPlace.getCanvas().snapshot(null, writableImage);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
        File outputFile = new File("src/main/resources/canvas.png");
        try {
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace(); // Wyświetlanie błędu w przypadku problemów z zapisem
        }
    }

    public void saveFill() {
        WritableImage writableImage = new WritableImage((int) workPlace.getCanvas().getWidth(), (int) workPlace.getCanvas().getHeight());
        workPlace.getCanvas().snapshot(null, writableImage);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
        File outputFile = new File("src/main/resources/canvasFill.png");
        try {
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace(); // Wyświetlanie błędu w przypadku problemów z zapisem
        }
    }


    public void load() {
        chooseBackgroundFromFile();
    }


    public void run() {
        Game game = new Game();
        game.start(new Stage());
    }
}
