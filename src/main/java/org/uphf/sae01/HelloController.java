package org.uphf.sae01;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloController {

    @FXML
    private GridPane gridPane;

    private Monde monde;

    @FXML
    public void initialize() {
        this.monde = new Monde();
        afficherGrille();
    }

    private void afficherGrille() {
        gridPane.getChildren().clear();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                StackPane cell = new StackPane();
                Rectangle rect = new Rectangle(35, 35, Color.WHITE);
                rect.setStroke(Color.BLACK);



                cell.getChildren().add(rect);
                gridPane.add(cell, j, i);
            }
        }
    }
}