package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Destillat;
import model.Destillering;
import model.Maltbatch;

import java.time.LocalDate;

public class OpretDestillatPane extends GridPane {
    private Controller controller;
    private ComboBox<Destillering> cobDestillering;
    private ComboBox<Destillat> cobDestillat;


    public OpretDestillatPane(Controller controller) {
        this.controller = controller;


        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        registrerDestillat();
        fordelDestillat();
    }

    public void registrerDestillat() {
        Label lbl = new Label("Registrer Destillat");
        this.add(lbl, 0, 0);

        // destillatNavn
        Label lblStartDato = new Label("Indtast destillat navn");
        this.add(lblStartDato, 0, 1);
        TextField txtDestillatNavn = new TextField();
        this.add(txtDestillatNavn, 0, 2);

        //dato
        Label lblDato = new Label("Vælg dato");
        this.add(lblDato, 0, 3);
        DatePicker dpDato = new DatePicker();
        this.add(dpDato, 0, 4);

        //mængde
        Label lblMængde = new Label("Indtast mængde");
        this.add(lblMængde, 0, 5);
        TextField txtMængde = new TextField();
        this.add(txtMængde, 0, 6);

        //alkoholProcent
        Label lblAlkoholProcent = new Label("Indtast alkoholprocent");
        this.add(lblAlkoholProcent, 0, 7);
        TextField txtAlkoholProcent = new TextField();
        this.add(txtAlkoholProcent, 0, 8);


        //Opret knap
        Button btnOpretKnap = new Button("Opret destillat");
        this.add(btnOpretKnap, 0, 9);
        btnOpretKnap.setOnAction(event -> {
            try {
                String destillatNavn = txtDestillatNavn.getText();
                LocalDate dato = dpDato.getValue();
                double mængde = Double.parseDouble(txtMængde.getText());
                double alkoholProcent = Double.parseDouble(txtAlkoholProcent.getText());
                controller.createDestillat(destillatNavn, dato, mængde, alkoholProcent);

                //Opdaterer comboBoxne efter destillat er oprettet
                updateControls();

                //Fjerner alt tekst efter oprettelse
                txtDestillatNavn.clear();
                dpDato.setValue(null);
                txtMængde.clear();
                txtAlkoholProcent.clear();

                //Bekræftelse alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Succes");
                alert.setHeaderText("Destillat oprettet");
                alert.setContentText("Desilattet blev oprettet korrekt.");

                alert.showAndWait();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldigt tal");
                alert.setContentText("Alkoholprocent skal være et tal.");
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

    public void fordelDestillat() {
        //Destillering
        Label lblDestillering = new Label("Vælg destillering");
        this.add(lblDestillering, 2, 1);
        cobDestillering = new ComboBox<>();
        cobDestillering.setItems(FXCollections.observableArrayList(controller.getDestilleringer()));
        this.add(cobDestillering, 2, 2);

        //Destillat
        Label lblDestillat = new Label("Vælg destillat");
        this.add(lblDestillat, 2, 3);
        cobDestillat = new ComboBox<>();
        cobDestillat.setItems(FXCollections.observableArrayList(controller.getDestillater()));
        this.add(cobDestillat, 2, 4);
        updateControls();

        //Mængde
        Label lblMængde = new Label("Indtast mængde");
        this.add(lblMængde, 2, 5);
        TextField txtMængde = new TextField();
        this.add(txtMængde, 2, 6);

        //Knap til whiskysammensætning
        Button btnFordel = new Button("Tilføj mængde til destillat");
        this.add(btnFordel, 2, 7);
        btnFordel.setOnAction(event -> {
            try {
                Destillering destillering = cobDestillering.getSelectionModel().getSelectedItem();
                Destillat destillat = cobDestillat.getSelectionModel().getSelectedItem();
                double mængde = Double.parseDouble(txtMængde.getText());

                controller.createDestilleringsMængde(mængde, destillering, destillat);

                destillering.createDestilleringsMængde(mængde, destillat);

                //Opdaterer comboBoksne efter at man har trykket på knappen
                updateControls();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Mængde tilføjet");
                alert.setContentText("Destilleringsmængden blev koblet på destillatet.");
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

    //Opdaterer comboBoxne så der kommer destilleringer og destillater ind
    public void updateControls() {
        cobDestillering.setItems(
                FXCollections.observableArrayList(controller.getDestilleringer())
        );

        cobDestillat.setItems(
                FXCollections.observableArrayList(controller.getDestillater())
        );
    }
}
