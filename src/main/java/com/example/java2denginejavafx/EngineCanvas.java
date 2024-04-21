package com.example.java2denginejavafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class EngineCanvas {

    private  Canvas canvas;
    private boolean backgroundSelected;

    private Image backgroundImage;


    private Color color;

    public EngineCanvas(Canvas canvas, boolean backgroundSelected) {
        this.canvas = canvas;
        this.backgroundSelected = backgroundSelected;
    }

    public EngineCanvas(double width, double height) {
        canvas = new Canvas(width,height);
        backgroundSelected = false;
    }

    private GraphicsContext graphicsContext;

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isBackgroundSelected() {
        return backgroundSelected;
    }

    public void setBackgroundSelected(boolean backgroundSelected) {
        this.backgroundSelected = backgroundSelected;
    }


}
