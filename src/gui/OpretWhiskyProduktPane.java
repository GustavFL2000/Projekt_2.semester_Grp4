package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Destillat;
import model.KvalitetsStempel;
import model.Produkt;

public class OpretWhiskyProduktPane extends GridPane {

    private Controller controller;

    private ComboBox<Produkt> cobProduktTilSammensætning;
    private ComboBox<Produkt> cobProduktTilFlasker;
    private ComboBox<Produkt> cobProduktTilHistorik;
    private ComboBox<Destillat> cobDestillater;

    public OpretWhiskyProduktPane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(15);
        this.setVgap(10);

        opretProduktPane();
        whiskySammensætningPane();
        opretFlaskerPane();
        visFlaskeHistorik();

        updateControls();
    }

    public void opretProduktPane() {
        Label lblTitel = new Label("Opret whiskyprodukt");
        this.add(lblTitel, 0, 0);

        Label lblVandMængde = new Label("Indtast vandmængde");
        this.add(lblVandMængde, 0, 1);
        TextField txtVandMængde = new TextField();
        this.add(txtVandMængde, 0, 2);

        Label lblAlkoholProcent = new Label("Indtast alkoholprocent");
        this.add(lblAlkoholProcent, 0, 3);
        TextField txtAlkoholProcent = new TextField();
        this.add(txtAlkoholProcent, 0, 4);

        Label lblBeskrivelse = new Label("Indtast beskrivelse");
        this.add(lblBeskrivelse, 0, 5);
        TextField txtBeskrivelse = new TextField();
        this.add(txtBeskrivelse, 0, 6);

        Label lblKvalitetsStempel = new Label("Vælg kvalitetsstempel");
        this.add(lblKvalitetsStempel, 0, 7);
        ComboBox<KvalitetsStempel> cobKvalitetsStempel = new ComboBox<>();
        cobKvalitetsStempel.setItems(FXCollections.observableArrayList(KvalitetsStempel.values()));
        this.add(cobKvalitetsStempel, 0, 8);

        Button btnOpretProdukt = new Button("Opret produkt");
        this.add(btnOpretProdukt, 0, 9);

        btnOpretProdukt.setOnAction(event -> {
            try {
                double vandMængde = Double.parseDouble(txtVandMængde.getText());
                double alkoholProcent = Double.parseDouble(txtAlkoholProcent.getText());
                String beskrivelse = txtBeskrivelse.getText();
                KvalitetsStempel kvalitetsStempel =
                        cobKvalitetsStempel.getSelectionModel().getSelectedItem();

                controller.createProdukt(vandMængde, alkoholProcent, beskrivelse, kvalitetsStempel);


                txtVandMængde.clear();
                txtAlkoholProcent.clear();
                txtBeskrivelse.clear();
                cobKvalitetsStempel.getSelectionModel().clearSelection();

                updateControls();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Produkt oprettet");
                alert.setContentText("Whiskyproduktet blev oprettet.");
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

    public void whiskySammensætningPane() {
        Label lblTitel = new Label("Whisky sammensætning");
        this.add(lblTitel, 2, 0);

        Label lblProdukt = new Label("Vælg produkt");
        this.add(lblProdukt, 2, 1);
        cobProduktTilSammensætning = new ComboBox<>();
        cobProduktTilSammensætning.setMaxWidth(300);
        this.add(cobProduktTilSammensætning, 2, 2);

        Label lblDestillat = new Label("Vælg destillat");
        this.add(lblDestillat, 2, 3);
        cobDestillater = new ComboBox<>();
        cobDestillater.setMaxWidth(300);
        this.add(cobDestillater, 2, 4);

        Label lblMængde = new Label("Mængde fra destillat");
        this.add(lblMængde, 2, 5);
        TextField txtMængdeFraDestillat = new TextField();
        this.add(txtMængdeFraDestillat, 2, 6);

        Button btnTilføjDestillat = new Button("Tilføj destillat");
        this.add(btnTilføjDestillat, 2, 7);

        btnTilføjDestillat.setOnAction(event -> {
            try {
                Produkt produkt = cobProduktTilSammensætning.getSelectionModel().getSelectedItem();
                Destillat destillat = cobDestillater.getSelectionModel().getSelectedItem();
                double mængde = Double.parseDouble(txtMængdeFraDestillat.getText());

                if (produkt == null) {
                    throw new IllegalArgumentException("Vælg et produkt");
                }
                if (destillat == null) {
                    throw new IllegalArgumentException("Vælg et destillat");
                }

                controller.createWhiskySammensætning(mængde, produkt, destillat);

                txtMængdeFraDestillat.clear();

                updateControls();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Destillat tilføjet");
                alert.setContentText("Destillatet blev tilføjet til produktet.");
                alert.showAndWait();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldigt tal");
                alert.setContentText("Mængde skal være et tal.");
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

    public void opretFlaskerPane() {
        Label lblTitel = new Label("Opret flasker");
        this.add(lblTitel, 4, 0);

        Label lblProdukt = new Label("Vælg produkt");
        this.add(lblProdukt, 4, 1);
        cobProduktTilFlasker = new ComboBox<>();
        cobProduktTilFlasker.setMaxWidth(300);
        this.add(cobProduktTilFlasker, 4, 2);

        Label lblStørrelse = new Label("Indtast størrelse i ml på flasken");
        this.add(lblStørrelse, 4, 3);
        TextField txtFlaskeStørrelse = new TextField();
        this.add(txtFlaskeStørrelse, 4, 4);

        Label lblAntalFlasker = new Label("Indtast antal flasker");
        this.add(lblAntalFlasker, 4, 5);
        TextField txtAntalFlasker = new TextField();
        this.add(txtAntalFlasker, 4, 6);

        Button btnOpretFlasker = new Button("Opret flasker");
        this.add(btnOpretFlasker, 4, 7);

        btnOpretFlasker.setOnAction(event -> {
            try {
                Produkt produkt = cobProduktTilFlasker.getSelectionModel().getSelectedItem();

                if (produkt == null) {
                    throw new IllegalArgumentException("Vælg et produkt");
                }

                int størrelse = Integer.parseInt(txtFlaskeStørrelse.getText());
                int antalFlasker = Integer.parseInt(txtAntalFlasker.getText());

                controller.createFlasker(størrelse, produkt, antalFlasker);

                txtFlaskeStørrelse.clear();
                txtAntalFlasker.clear();
                updateControls();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                if(antalFlasker == 1){
                    alert.setHeaderText("Flaske oprettet");
                    alert.setContentText(antalFlasker + " flaske blev oprettet.");
                }else {
                    alert.setHeaderText("Flasker oprettet");
                    alert.setContentText(antalFlasker + " flasker blev oprettet.");
                }
                alert.showAndWait();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldigt tal");
                alert.setContentText("Størrelse og antal flasker skal være hele tal.");
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

    public void visFlaskeHistorik(){
        Label lblTitel = new Label("Flaske Historik");
        this.add(lblTitel, 6, 0);

        Label lblProdukt = new Label("Vælg produkt");
        this.add(lblProdukt, 6, 1);

        cobProduktTilHistorik = new ComboBox<>();
        cobProduktTilHistorik.setMaxWidth(300);
        this.add(cobProduktTilHistorik, 6,2);

        Label lblFlaskeNr = new Label("Indtast flaskenr");
        this.add(lblFlaskeNr, 6, 3);
        TextField txtFlaskeNr = new TextField();
        this.add(txtFlaskeNr, 6, 4);

        Button btnFlaskeHistorik = new Button("Vis historik");
        this.add(btnFlaskeHistorik, 6, 5);

        TextArea txaHistorik = new TextArea();
        txaHistorik.setEditable(false);
        txaHistorik.setPrefHeight(300);
        this.add(txaHistorik, 6, 6,1,10);

        btnFlaskeHistorik.setOnAction(event -> {
            try {
                int flaskeNr = Integer.parseInt(txtFlaskeNr.getText());
                Produkt produkt = cobProduktTilHistorik.getSelectionModel().getSelectedItem();

                if (produkt == null) {
                    throw new IllegalArgumentException("Vælg et produkt");
                }
                int produktNr = produkt.getProduktNr();

                String historik = controller.visHistorik(flaskeNr, produktNr);
                txaHistorik.setText(historik);

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldigt tal");
                alert.setContentText("Flaskenummeret skal være et helt tal.");
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
        cobProduktTilSammensætning.setItems(
                FXCollections.observableArrayList(controller.getProdukter())
        );

        cobProduktTilFlasker.setItems(
                FXCollections.observableArrayList(controller.getProdukter())
        );

        cobProduktTilHistorik.setItems
                (FXCollections.observableArrayList(controller.getProdukter())
        );

        cobDestillater.setItems(
                FXCollections.observableArrayList(controller.getDestillaterKlarTilProdukt())
        );
    }
}