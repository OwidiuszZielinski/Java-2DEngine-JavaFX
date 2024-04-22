package com.example.java2denginejavafx;

import com.example.java2denginejavafx.game.Game;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class BitmapService {

    private EngineCanvas workPlace;
    private Point point;
    private final FileChooser fileChooser = new FileChooser();

    private GraphicsContext background;
    private String direction;
    private boolean render;
    private Image image;

    private boolean playerAnimation;

    private Image up1;
    private Image up2;
    private Image down1;
    private Image down2;
    private Image left1;
    private Image left2;
    private Image right1;
    private Image right2;
    public GraphicsContext getBackground() {
        return background;
    }

    public void setBackground(GraphicsContext background) {
        this.background = background;
    }
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }



    public BitmapService(EngineCanvas workPlace, Point point, GraphicsContext background) {
        this.workPlace = workPlace;
        this.point = point;
        this.background = background;

    }
    public BitmapService(EngineCanvas workPlace, Point point) {
        this.workPlace = workPlace;
        this.point = point;
    }

    public BitmapService() {
    }





    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean isPlayerAnimation() {
        return playerAnimation;
    }

    public void setPlayerAnimation(boolean playerAnimation) {
        this.playerAnimation = playerAnimation;
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
        setPlayerAnimation(false);
        fileChooser.setTitle("Wybierz bitmape gracza");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println(selectedFile.getName());
            point.setShape(null);
            point.setTool(null);
            Image image = new Image(selectedFile.toURI().toString());
            point.setImage(image);
            System.out.println(image);
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



    public void load() {
        chooseBackgroundFromFile();
    }


    public void run() {
        Game game = new Game();
        game.start(new Stage());
    }


    public void getPlayerImages(){
             up1 = new Image("file:src/main/resources/boy_down_1.png");
             up2 = new Image("file:src/main/resources/boy_down_2.png");
             down1 = new Image("file:src/main/resources/boy_up_1.png");
             down2 = new Image("file:src/main/resources/boy_up_2.png");
             left1 = new Image("file:src/main/resources/boy_left_1.png");
             left2 = new Image("file:src/main/resources/boy_left_2.png");
             right1 = new Image("file:src/main/resources/boy_right_1.png");
             right2 = new Image("file:src/main/resources/boy_right_2.png");
    }
    public void draw(GraphicsContext gc) {
        double x = point.getX() - point.getWidth() / 2;
        double y = point.getY() - point.getHeight() / 2;
        double width = Math.min(point.getWidth(), point.getWidth());
        double height = Math.min(point.getHeight(), point.getHeight());
        Image image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };

            gc.save(); // Zachowaj stan rysowania
            gc.translate(x + width / 2, y + height / 2); // Przesuń do punktu środkowego obrazka
            gc.rotate(point.getImageRotate()); // Obróć obrazek
            gc.drawImage(image, -width / 2, -height / 2, width, height); // Narysuj obrócony obrazek
            gc.restore(); // Przywróć poprzedni stan rysowania
        }

    public void drawBitmap(GraphicsContext gc) {
        double x = point.getX() - point.getWidth() / 2;
        double y = point.getY() - point.getHeight() / 2;
        double width = Math.min(point.getWidth(), point.getWidth());
        double height = Math.min(point.getHeight(), point.getHeight());
        gc.save(); // Zachowaj stan rysowania
        gc.translate(x + width / 2, y + height / 2); // Przesuń do punktu środkowego obrazka
        gc.rotate(point.getImageRotate()); // Obróć obrazek
        gc.drawImage(point.getImage(), -width / 2, -height / 2, width, height); // Narysuj obrócony obrazek
        gc.restore(); // Przywróć poprzedni stan rysowania
    }

    public void switchToAnimation() {
        getPlayerImages();
        setPlayerAnimation(true);
        point.setImage(up1);
    }
}

