package com.example.java2denginejavafx;

import javafx.scene.input.MouseEvent;

public class ToolManager {
    private Tool currentTool;

    public ToolManager() {
        // Domyślnie ustaw narzędzie na brak narzędzia (null)
        this.currentTool = null;
    }

    public void setCurrentTool(Tool tool) {
        this.currentTool = tool;
    }

    public void handleMouseClick(MouseEvent event, Point point) {

    }
}
