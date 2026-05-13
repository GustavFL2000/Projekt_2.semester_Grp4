package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FadPane extends GridPane {

    public FadPane() {

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Label lbl = new Label("Fad side");
        this.add(lbl, 0, 0);

    }
}