package com.example.java2denginejavafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class EngineCanvas {

    private  Canvas canvas;
    private boolean backgroundSelected;

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    private Image backgroundImage;

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public boolean isBackgroundSelected() {
        return backgroundSelected;
    }

    public void setBackgroundSelected(boolean backgroundSelected) {
        this.backgroundSelected = backgroundSelected;
    }

    public EngineCanvas(Canvas canvas, boolean backgroundSelected) {
        this.canvas = canvas;
        this.backgroundSelected = backgroundSelected;
    }

    public EngineCanvas(double width, double height) {
        canvas = new Canvas(width,height);
        backgroundSelected = false;
    }
}
