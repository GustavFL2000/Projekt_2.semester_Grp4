package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class LagerPane extends GridPane {
    private Controller controller;
    private ListView lagerlist;

    public LagerPane(Controller controller) {
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        opretLager();

        lagerlist = new ListView<>();
        lagerlist.setItems(FXCollections.observableArrayList(controller.getLager()));
        this.add(lagerlist, 2, 1, 1, 10);
    }

    public void opretLager (){
        //String lagerNavn, String adresse
        Label lbl = new Label("Opret lager");
        this.add(lbl, 0, 0);

        //lagerNavn
        Label lblLagerNavn = new Label("Indtast lagernavn");
        this.add(lblLagerNavn, 0, 1);
        TextField txtLagerNavn = new TextField();
        this.add(txtLagerNavn, 0, 2);

        //adresse
        Label lblAdresse = new Label("Indtast adresse");
        this.add(lblAdresse, 0, 3);
        TextField txtAdresse = new TextField();
        this.add(txtAdresse, 0, 4);

        //antal reoler
        Label lblAntalReoler = new Label("Indtast antal reoler");
        this.add(lblAntalReoler, 0, 5);

        TextField txtAntalReoler = new TextField();
        this.add(txtAntalReoler, 0, 6);

        //Knap til opretlager
        Button btnFordel = new Button("Opret lager");
        this.add(btnFordel, 0, 7);
        btnFordel.setOnAction(event -> {
            try {
                String lagerNavn = txtLagerNavn.getText();
                String adresse = txtAdresse.getText();
                int antalReoler = Integer.parseInt(txtAntalReoler.getText());

                controller.createLagerMedReoler(lagerNavn, adresse, antalReoler);

                //Opdaterer listView efter man har trykket på opret knappen
                updateControls();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("Lager oprettet");
                alert.setContentText("Lager er nu oprettet");
                alert.showAndWait();

                //Fjerner tekst efter oprettelse
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
    }
}
