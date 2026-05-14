package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Fad;
import model.Lager;
import model.Reol;

public class LagerPane extends GridPane {

    private Controller controller;
    private ListView<Lager> lagerlist;
    private ListView<Reol> reolList;
    private ComboBox<Fad> cobFad;

    public LagerPane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        opretLager();
        placerFadpåLager();

        // Liste med lagre
        lagerlist = new ListView<>();
        lagerlist.setItems(
                FXCollections.observableArrayList(controller.getLager())
        );

        this.add(lagerlist, 2, 1, 1, 10);

        // Når man vælger et lager
        lagerlist.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldLager, selectedLager) -> {

                    if (selectedLager != null) {

                        // Vis reoler fra valgt lager
                        reolList.setItems(
                                FXCollections.observableArrayList(
                                        selectedLager.getReoler()
                                )
                        );
                    }
                });
    }

    public void placerFadpåLager() {

        // Vælg fad
        Label lblFad = new Label("Vælg fad");
        this.add(lblFad, 4, 0);

        cobFad = new ComboBox<>();
        cobFad.setItems(
                FXCollections.observableArrayList(controller.getFade())
        );

        this.add(cobFad, 4, 1);

        // Vælg reol fra lager

        reolList = new ListView<>();
        this.add(reolList, 3, 1, 1, 10);

        // Hvis man vælger en reol
        reolList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldReol, selectedReol) -> {

                    if (selectedReol != null) {
                        System.out.println("Valgt reol: " + selectedReol);
                    }
                });
    }

    public void opretLager() {

        // String lagerNavn, String adresse
        Label lbl = new Label("Opret lager");
        this.add(lbl, 0, 0);

        // lagerNavn
        Label lblLagerNavn = new Label("Indtast lagernavn");
        this.add(lblLagerNavn, 0, 1);

        TextField txtLagerNavn = new TextField();
        this.add(txtLagerNavn, 0, 2);

        // adresse
        Label lblAdresse = new Label("Indtast adresse");
        this.add(lblAdresse, 0, 3);

        TextField txtAdresse = new TextField();
        this.add(txtAdresse, 0, 4);

        // antal reoler
        Label lblAntalReoler = new Label("Indtast antal reoler");
        this.add(lblAntalReoler, 0, 5);

        TextField txtAntalReoler = new TextField();
        this.add(txtAntalReoler, 0, 6);

        // Knap til opretlager
        Button btnFordel = new Button("Opret lager");
        this.add(btnFordel, 0, 7);

        btnFordel.setOnAction(event -> {

            try {

                String lagerNavn = txtLagerNavn.getText();
                String adresse = txtAdresse.getText();
                int antalReoler = Integer.parseInt(
                        txtAntalReoler.getText()
                );

                controller.createLagerMedReoler(
                        lagerNavn,
                        adresse,
                        antalReoler
                );

                // Opdaterer ListView
                updateControls();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Lager oprettet");
                alert.setContentText("Lager er nu oprettet");
                alert.showAndWait();

                // Ryd felter
                txtLagerNavn.clear();
                txtAdresse.clear();
                txtAntalReoler.clear();

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

        lagerlist.setItems(
                FXCollections.observableArrayList(controller.getLager())
        );

        cobFad.setItems(
                FXCollections.observableArrayList(controller.getFade())
        );
    }
}