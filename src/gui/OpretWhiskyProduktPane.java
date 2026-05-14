package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Fad;

public class OpretWhiskyProduktPane extends GridPane {

    private Controller controller;
    private ListView<Fad> lsvTilgængeligeFade;

    public OpretWhiskyProduktPane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        whiskySammensætning();
    }

    public void whiskySammensætning() {

        Label lblTilgængeligeFade =
                new Label("Tilgængelige fade klar til whisky produkt");

        this.add(lblTilgængeligeFade, 0, 0);

        // Tilgængelige fade der har lagret i 3 år
        lsvTilgængeligeFade = new ListView<>();

        lsvTilgængeligeFade.setItems(
                FXCollections.observableArrayList(
                        controller.getFadeKlarTilAftapning()
                )
        );

        this.add(lsvTilgængeligeFade, 0, 1);
    }

    public void updateControls() {

        lsvTilgængeligeFade.setItems(
                FXCollections.observableArrayList(
                        controller.getFadeKlarTilAftapning()
                )
        );
    }
}