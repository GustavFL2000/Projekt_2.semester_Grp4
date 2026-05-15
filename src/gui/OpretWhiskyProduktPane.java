package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Fad;
import model.KvalitetsStempel;

public class OpretWhiskyProduktPane extends GridPane {

    private Controller controller;
    private ListView<Fad> lsvTilgængeligeFade;

    public OpretWhiskyProduktPane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        opretProdukt();
        whiskySammensætning();
    }

    public void whiskySammensætning() {

        Label lblTilgængeligeFade =
                new Label("Tilgængelige fade klar til whisky produkt");

        this.add(lblTilgængeligeFade, 3, 0);

        // Tilgængelige fade der har lagret i 3 år
        lsvTilgængeligeFade = new ListView<>();

        lsvTilgængeligeFade.setItems(
                FXCollections.observableArrayList(
                        controller.getFadeKlarTilAftapning()
                )
        );

        this.add(lsvTilgængeligeFade, 3, 1, 1, 15);
    }


    public void opretProdukt() {
        Label lbl = new Label("Opret whiskyprodukt");
        this.add(lbl, 0, 0);

        Label lblVandMængde = new Label("Indtast vandmængde");
        this.add(lblVandMængde, 0, 1);
        TextField txtVandMængde = new TextField();
        this.add(txtVandMængde, 0, 2);

        Label lblVandOprindelse = new Label("Indtast vandoprindelse");
        this.add(lblVandOprindelse, 0, 3);
        TextField txtVandOprindelse = new TextField();
        this.add(txtVandOprindelse, 0, 4);

        Label lblAlkoholProcent = new Label("Indtast alkoholprocent");
        this.add(lblAlkoholProcent, 0, 5);
        TextField txtAlkoholProcent = new TextField();
        this.add(txtAlkoholProcent, 0, 6);

        Label lblBeskrivelse = new Label("Indtast beskrivelse");
        this.add(lblBeskrivelse, 0, 7);
        TextField txtBeskrivelse = new TextField();
        this.add(txtBeskrivelse, 0, 8);

        Label lblKvalitetsStempel = new Label("Vælg kvalitetsstempel");
        this.add(lblKvalitetsStempel, 0, 9);
        ComboBox<KvalitetsStempel> cobKvalitetsStempel = new ComboBox<>();
        cobKvalitetsStempel.setItems(
                FXCollections.observableArrayList(KvalitetsStempel.values())
        );
        this.add(cobKvalitetsStempel, 0, 10);

        Button btnOpretProdukt = new Button("Opret produkt");
        this.add(btnOpretProdukt, 0, 11);

        btnOpretProdukt.setOnAction(event -> {
            try {
                double vandMængde = Double.parseDouble(txtVandMængde.getText());
                String vandOprindelse = txtVandOprindelse.getText();
                double alkoholProcent = Double.parseDouble(txtAlkoholProcent.getText());
                String beskrivelse = txtBeskrivelse.getText();
                KvalitetsStempel kvalitetsStempel =
                        cobKvalitetsStempel.getSelectionModel().getSelectedItem();

                controller.createProdukt(
                        vandMængde,
                        vandOprindelse,
                        alkoholProcent,
                        beskrivelse,
                        kvalitetsStempel
                );

                txtVandMængde.clear();
                txtVandOprindelse.clear();
                txtAlkoholProcent.clear();
                txtBeskrivelse.clear();
                cobKvalitetsStempel.getSelectionModel().clearSelection();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Produkt oprettet");
                alert.setContentText("Whiskyproduktet blev oprettet korrekt.");
                alert.showAndWait();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldigt tal");
                alert.setContentText("Vandmængde og alkoholprocent skal være tal.");
                alert.showAndWait();

            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldige data");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
    }

    public void updateControls() {

        lsvTilgængeligeFade.setItems(
                FXCollections.observableArrayList(
                        controller.getFadeKlarTilAftapning()
                )
        );
    }
}