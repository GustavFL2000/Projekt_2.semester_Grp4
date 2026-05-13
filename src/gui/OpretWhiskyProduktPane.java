package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class OpretWhiskyProduktPane extends GridPane {

    public OpretWhiskyProduktPane() {

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Label lbl = new Label("Opret Whisky Produkt");
        this.add(lbl, 0, 0);

    }
}