package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RegistrerDestilleringPane extends GridPane {

    public RegistrerDestilleringPane() {

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Label lbl = new Label("Registrer Destillering");
        this.add(lbl, 0, 0);

    }
}