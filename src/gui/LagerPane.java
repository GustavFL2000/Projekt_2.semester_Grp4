package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LagerPane extends GridPane {
    public LagerPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Label lbl = new Label("Lager side");
        this.add(lbl, 0, 0);

    }
}
