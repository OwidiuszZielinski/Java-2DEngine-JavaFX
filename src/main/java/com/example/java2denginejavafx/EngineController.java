package com.example.java2denginejavafx;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EngineController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button topButton;



    @FXML
    protected void onClickTopButton() {
        topButton.setText("ELO");
    }


}