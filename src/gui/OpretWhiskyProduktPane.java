package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Destillat;
import model.Destillering;
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

    }

    public void fordelDestillatPåFad(){
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
}