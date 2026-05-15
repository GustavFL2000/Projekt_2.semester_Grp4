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

    // Produkt
    private TextField txtVandMængde;
    private TextField txtVandOprindelse;
    private TextField txtAlkoholProcent;
    private TextField txtBeskrivelse;
    private ComboBox<KvalitetsStempel> cobKvalitetsStempel;

    // Whisky sammensætning
    private ComboBox<Produkt> cobProdukter;
    private ComboBox<Destillat> cobDestillater;
    private TextField txtMængdeFraDestillat;

    public OpretWhiskyProduktPane(Controller controller) {

        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(15);
        this.setVgap(10);

        opretProduktPane();
        whiskySammensætningPane();

        updateControls();
    }

    // -------------------------------------------------------------------------
    // Opret produkt
    // -------------------------------------------------------------------------

    private void opretProduktPane() {

        Label lblTitel =
                new Label("Opret whiskyprodukt");

        this.add(lblTitel, 0, 0);

        // Vandmængde
        Label lblVandMængde =
                new Label("Indtast vandmængde");

        this.add(lblVandMængde, 0, 1);

        txtVandMængde = new TextField();

        this.add(txtVandMængde, 0, 2);

        // Vandoprindelse
        Label lblVandOprindelse =
                new Label("Indtast vandoprindelse");

        this.add(lblVandOprindelse, 0, 3);

        txtVandOprindelse = new TextField();

        this.add(txtVandOprindelse, 0, 4);

        // Alkoholprocent
        Label lblAlkoholProcent =
                new Label("Indtast alkoholprocent");

        this.add(lblAlkoholProcent, 0, 5);

        txtAlkoholProcent = new TextField();

        this.add(txtAlkoholProcent, 0, 6);

        // Beskrivelse
        Label lblBeskrivelse =
                new Label("Indtast beskrivelse");

        this.add(lblBeskrivelse, 0, 7);

        txtBeskrivelse = new TextField();

        this.add(txtBeskrivelse, 0, 8);

        // Kvalitetsstempel
        Label lblKvalitetsStempel =
                new Label("Vælg kvalitetsstempel");

        this.add(lblKvalitetsStempel, 0, 9);

        cobKvalitetsStempel = new ComboBox<>();

        cobKvalitetsStempel.setItems(
                FXCollections.observableArrayList(
                        KvalitetsStempel.values()
                )
        );

        this.add(cobKvalitetsStempel, 0, 10);

        // Knap
        Button btnOpretProdukt =
                new Button("Opret produkt");

        this.add(btnOpretProdukt, 0, 11);

        btnOpretProdukt.setOnAction(
                event -> opretProduktAction()
        );
    }

    // -------------------------------------------------------------------------
    // Whisky sammensætning
    // -------------------------------------------------------------------------

    private void whiskySammensætningPane() {

        Label lblTitel =
                new Label("Whisky sammensætning");

        this.add(lblTitel, 3, 0);

        // Produkt
        Label lblProdukt =
                new Label("Vælg produkt");

        this.add(lblProdukt, 3, 1);

        cobProdukter = new ComboBox<>();

        this.add(cobProdukter, 3, 2);

        // Destillat
        Label lblDestillat =
                new Label("Vælg destillat");

        this.add(lblDestillat, 3, 3);

        cobDestillater = new ComboBox<>();

        this.add(cobDestillater, 3, 4);

        // Mængde
        Label lblMængde =
                new Label("Mængde fra destillat");

        this.add(lblMængde, 3, 5);

        txtMængdeFraDestillat = new TextField();

        this.add(txtMængdeFraDestillat, 3, 6);

        // Knap
        Button btnTilføjDestillat =
                new Button("Tilføj destillat");

        this.add(btnTilføjDestillat, 3, 7);

        btnTilføjDestillat.setOnAction(
                event -> tilføjDestillatAction()
        );
    }

    // -------------------------------------------------------------------------
    // Actions
    // -------------------------------------------------------------------------

    private void opretProduktAction() {

        try {

            double vandMængde =
                    Double.parseDouble(
                            txtVandMængde.getText()
                    );

            String vandOprindelse =
                    txtVandOprindelse.getText();

            double alkoholProcent =
                    Double.parseDouble(
                            txtAlkoholProcent.getText()
                    );

            String beskrivelse =
                    txtBeskrivelse.getText();

            KvalitetsStempel kvalitetsStempel =
                    cobKvalitetsStempel
                            .getSelectionModel()
                            .getSelectedItem();

            controller.createProdukt(
                    vandMængde,
                    alkoholProcent,
                    beskrivelse,
                    kvalitetsStempel
            );

            clearProduktFields();

            updateControls();

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText("Produkt oprettet");

            alert.setContentText(
                    "Whiskyproduktet blev oprettet."
            );

            alert.showAndWait();

        } catch (NumberFormatException e) {

            showError(
                    "Vandmængde og alkoholprocent skal være tal."
            );

        } catch (IllegalArgumentException e) {

            showError(e.getMessage());
        }
    }

    private void tilføjDestillatAction() {

        try {

            Produkt produkt =
                    cobProdukter
                            .getSelectionModel()
                            .getSelectedItem();

            if (produkt == null) {
                throw new IllegalArgumentException(
                        "Vælg et produkt"
                );
            }

            Destillat destillat =
                    cobDestillater
                            .getSelectionModel()
                            .getSelectedItem();

            if (destillat == null) {
                throw new IllegalArgumentException(
                        "Vælg et destillat"
                );
            }

            double mængde =
                    Double.parseDouble(
                            txtMængdeFraDestillat.getText()
                    );

            produkt.createWhiskySammensætning(
                    mængde,
                    destillat
            );

            txtMængdeFraDestillat.clear();

            updateControls();

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText(
                    "Destillat tilføjet"
            );

            alert.setContentText(
                    "Destillatet blev tilføjet til produktet."
            );

            alert.showAndWait();

        } catch (NumberFormatException e) {

            showError("Mængde skal være et tal.");

        } catch (IllegalArgumentException e) {

            showError(e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Hjælpemetoder
    // -------------------------------------------------------------------------

    public void updateControls() {

        cobProdukter.setItems(
                FXCollections.observableArrayList(
                        controller.getProdukter()
                )
        );

        cobDestillater.setItems(
                FXCollections.observableArrayList(
                        controller.getDestillaterKlarTilProdukt()
                )
        );
    }

    private void clearProduktFields() {

        txtVandMængde.clear();

        txtVandOprindelse.clear();

        txtAlkoholProcent.clear();

        txtBeskrivelse.clear();

        cobKvalitetsStempel
                .getSelectionModel()
                .clearSelection();
    }

    private void showError(String message) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("Fejl");

        alert.setContentText(message);

        alert.showAndWait();
    }
}