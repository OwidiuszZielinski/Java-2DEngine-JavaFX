package com.example.java2denginejavafx.gui;

import com.example.java2denginejavafx.BackgroundService;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class AppButton {
    private final BorderPane root;
    private final double prefWidth = 80;
    private final double prefHeight = 30;
    private final BackgroundService backgroundService;

    public AppButton(BorderPane root, BackgroundService backgroundService) {
        this.root = root;
        this.backgroundService = backgroundService;
    }

    public double getPrefWidth() {
        return prefWidth;
    }

    public double getPrefHeight() {
        return prefHeight;
    }

    public void addBackgroundButton(){
        Button chooseBackgroundButton = new Button("Wybierz tło");
        chooseBackgroundButton.setOnAction(event -> backgroundService.chooseBackground());
        chooseBackgroundButton.setFocusTraversable(false);
        chooseBackgroundButton.setPrefSize(prefWidth,prefHeight);
        root.setTop(chooseBackgroundButton);
    }


}
