package com.example.java2denginejavafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class EngineCanvas {

    private final Canvas canvas;
    private boolean backgroundSelected;

    private Image backgroundImage;

    public EngineCanvas(double width, double height) {
        canvas = new Canvas(width, height);
        backgroundSelected = false;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public boolean isBackgroundSelected() {
        return backgroundSelected;
    }

    public void setBackgroundSelected(boolean backgroundSelected) {
        this.backgroundSelected = backgroundSelected;
    }


}
