package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Destillat;
import model.Fad;

import java.time.LocalDate;

public class OpretWhiskyProduktPane extends GridPane {

    private Controller controller;
    private ComboBox<Fad> cobFad;
    private ComboBox<Destillat> cobDestillat;

    public OpretWhiskyProduktPane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        fordelDestillatPåFad();
    }

    public void fordelDestillatPåFad(){
        //Destillat
        Label lblDestillering = new Label("Vælg destillat");
        this.add(lblDestillering, 0, 1);
        cobDestillat = new ComboBox<>();
        cobDestillat.setItems(FXCollections.observableArrayList(controller.getDestillater()));
        this.add(cobDestillat, 0, 2);

        //Fad
        Label lblFad = new Label("Vælg fad");
        this.add(lblFad, 0, 3);
        cobFad = new ComboBox<>();
        cobFad.setItems(FXCollections.observableArrayList(controller.getFade()));
        this.add(cobFad, 0, 4);

        //Mængde
        Label lblMængde = new Label("Indtast mængde");
        this.add(lblMængde, 0, 5);
        TextField txtMængde = new TextField();
        this.add(txtMængde, 0, 6);

        // StartDato
        Label lblDato = new Label("Vælg dato");
        this.add(lblDato,0, 7);
        DatePicker dpDato = new DatePicker();
        this.add(dpDato, 0,8);

        //Knap til whiskysammensætning
        Button btnFordel = new Button("Tilføj mængde til fad");
        this.add(btnFordel, 0, 9);
        btnFordel.setOnAction(event -> {
            try {
                Destillat destillat = cobDestillat.getSelectionModel().getSelectedItem();
                Fad fad = cobFad.getSelectionModel().getSelectedItem();
                double mængde = Double.parseDouble(txtMængde.getText());
                LocalDate dato = dpDato.getValue();

                controller.createPåfyldning(mængde, dato, destillat, fad);

                //Opdaterer comboBoksne efter at man har trykket på knappen
                updateControls();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Mængde tilføjet");
                alert.setContentText("Påfyldningen blev koblet på fadet.");
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
        cobDestillat.setItems(
                FXCollections.observableArrayList(controller.getDestillater())
        );

        cobFad.setItems(
                FXCollections.observableArrayList(controller.getFade())
        );
    }
}