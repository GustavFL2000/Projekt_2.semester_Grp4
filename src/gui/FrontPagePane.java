package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class FrontPagePane extends VBox {

    public FrontPagePane() {

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        Label lblWelcome = new Label("Velkommen til Whisky Systemet");

        this.getChildren().add(lblWelcome);
    }
}