package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class FrontPagePane extends GridPane {

    public FrontPagePane(Gui gui) {

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Button btnPage1 = new Button("Registrer Destillering");
        Button btnPage2 = new Button("Fad");
        Button btnPage3 = new Button("Opret Whisky Produkt");

        this.add(btnPage1, 0, 0);
        this.add(btnPage2, 1, 0);
        this.add(btnPage3, 2, 0);

        btnPage1.setOnAction(e -> gui.setPane(new RegistrerDestilleringPane(gui)));
        btnPage2.setOnAction(e -> gui.setPane(new FadPane(gui)));
        btnPage3.setOnAction(e -> gui.setPane(new OpretWhiskyProduktPane(gui)));
    }
}