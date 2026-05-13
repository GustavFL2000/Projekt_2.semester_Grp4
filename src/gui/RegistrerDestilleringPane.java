package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Maltbatch;

import java.time.LocalDate;

public class RegistrerDestilleringPane extends GridPane {
    private Controller controller;

    public RegistrerDestilleringPane(Controller controller) {
        this.controller = controller;


        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Label lbl = new Label("Registrer Destillering");
        this.add(lbl, 0, 0);

        // StartDato
        Label lblStartDato = new Label("Vælg start dato");
        this.add(lblStartDato,0, 1);
        DatePicker dpStartDato = new DatePicker();
        this.add(dpStartDato, 0,2);

        //Slutdato
        Label lblslutDato = new Label("Vælg slut dato");
        this.add(lblslutDato,0, 3);
        DatePicker dpSlutDato = new DatePicker();
        this.add(dpSlutDato, 0,4);

        //AlkoholProcent
        Label lblAlkoholProcent = new Label("Indtast alkoholprocent");
        this.add(lblAlkoholProcent, 0, 5);
        TextField txtAlkoholProcent = new TextField();
        this.add(txtAlkoholProcent, 0, 6);

        //RygeMateriale
        CheckBox chbRygeMateriale = new CheckBox("Rygemateriale");
        this.add(chbRygeMateriale, 0, 7);

        //Væskemængde
        Label lblVæskeMængde = new Label("Indtast væskemængde");
        this.add(lblVæskeMængde, 0, 8);
        TextField txtVæskeMængde = new TextField();
        this.add(txtVæskeMængde, 0, 9);

        //Maltbatch
        Label lblMaltBatch = new Label("Vælg maltbatch");
        this.add(lblMaltBatch, 0, 10);
        ComboBox<Maltbatch> cobMaltBatch = new ComboBox<>();
        cobMaltBatch.setItems(FXCollections.observableArrayList(controller.getMaltbatches()));
        this.add(cobMaltBatch, 0, 11);

        //Kommentar
        Label lblKommentar = new Label("Indtast kommentar");
        this.add(lblKommentar, 0, 12);
        TextField txtKommentar = new TextField();
        this.add(txtKommentar, 0, 13);

        //Opret knap
        Button btnOpretKnap = new Button("Opret destillering");
        this.add(btnOpretKnap,0, 14);
        btnOpretKnap.setOnAction(event ->{
            try {
                LocalDate startDato = dpStartDato.getValue();
                LocalDate sluttDato = dpSlutDato.getValue();
                double alkoholProcent = Double.parseDouble(txtAlkoholProcent.getText());
                boolean rygeMateriale = chbRygeMateriale.isSelected();
                String kommentar = txtKommentar.getText();
                double væskeMængde = Double.parseDouble(txtVæskeMængde.getText());
                Maltbatch maltbatch = cobMaltBatch.getSelectionModel().getSelectedItem();
                controller.createDestillering(startDato, sluttDato, alkoholProcent, rygeMateriale, kommentar, væskeMængde, maltbatch);

                //Bekræftelse alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Succes");
                alert.setHeaderText("Destillering oprettet");
                alert.setContentText("Destilleringen blev oprettet korrekt.");

                alert.showAndWait();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldigt tal");
                alert.setContentText("Indtast gyldige tal.");

                alert.showAndWait();

            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldigt dato");
                alert.setContentText("Slut dato må ikke være før start dato");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
    }
}