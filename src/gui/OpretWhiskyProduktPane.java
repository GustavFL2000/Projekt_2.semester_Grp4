package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Fad;

public class OpretWhiskyProduktPane extends GridPane {

    private Controller controller;

    public OpretWhiskyProduktPane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Label lbl = new Label("Opret Whisky Produkt");
        this.add(lbl, 0, 0);

        ListView<Fad> lsvFad = new ListView<>();
        lsvFad.setItems(FXCollections.observableArrayList(controller.getFade()));
        this.add(lsvFad, 0, 1);
    }
}