package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Leverandør;
import model.Maltbatch;

import java.time.LocalDate;

public class FadPane extends GridPane {
    private Controller controller;

    public FadPane(Controller controller) {
        this.controller=controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Label lbl = new Label("Opret fad");
        this.add(lbl, 0, 0);

        //(fadNr, land, størrelse, tidligereIndhold, leverandør);

        // land
        Label lblLand = new Label("Indtast et land");
        this.add(lblLand,0, 1);
        TextField txtLand = new TextField();
        this.add(txtLand, 0,2);

        //størrelse
        Label lblStørrelse = new Label("Indtast størrelse på fad");
        this.add(lblStørrelse,0, 3);
        TextField txtStørrelse = new TextField();
        this.add(txtStørrelse, 0,4);

        //tidligereIndhold
        Label lblTidligereIndhold = new Label("Indtast tidligere indhold");
        this.add(lblTidligereIndhold, 0, 5);
        TextField txtTidligereIndhold = new TextField();
        this.add(txtTidligereIndhold, 0, 6);

        //Leverandør
        Label lblLeverandør = new Label("Leverandør");
        this.add(lblLeverandør, 0, 7);
        ComboBox<Leverandør> cobLeverandør = new ComboBox<>();
        cobLeverandør.setItems(FXCollections.observableArrayList(controller.getLeverandører()));
        this.add(cobLeverandør, 0, 8);


        //Opret knap
        Button btnOpretKnap = new Button("Opret fad");
        this.add(btnOpretKnap,0, 9);
        btnOpretKnap.setOnAction(event ->{
            try {
                String land = txtLand.getText();
                double størrelse = Double.parseDouble(txtStørrelse.getText());
                String tidligereIndhold = txtTidligereIndhold.getText();
                Leverandør leverandør = cobLeverandør.getSelectionModel().getSelectedItem();
                controller.createFad(land, størrelse, tidligereIndhold, leverandør);

                //Bekræftelse alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Succes");
                alert.setHeaderText("Fad oprettet");
                alert.setContentText("Fad blev oprettet korrekt.");

                alert.showAndWait();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldigt tal");
                alert.setContentText("Du kan kun indtaste tal i størrelse");

                alert.showAndWait();

            }catch (IllegalArgumentException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldige data");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });

    }
}