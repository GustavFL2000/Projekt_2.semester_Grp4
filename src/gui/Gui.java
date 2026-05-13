package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Gui extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Whisky System");

        TabPane tabPane = new TabPane();

        // Forside
        Tab tabFrontpage = new Tab("Forside");
        tabFrontpage.setContent(new FrontPagePane());

        // Registrer destillering
        Tab tabDestillering = new Tab("Registrer Destillering");
        tabDestillering.setContent(new RegistrerDestilleringPane());

        // Fad
        Tab tabFad = new Tab("Fad");
        tabFad.setContent(new FadPane());

        // Whisky produkt
        Tab tabWhisky = new Tab("Opret Whisky Produkt");
        tabWhisky.setContent(new OpretWhiskyProduktPane());

        //Lager side
        Tab tabLager = new Tab("Lager");
        tabLager.setContent(new LagerPane());

        // Tabs må ikke lukkes
        tabFrontpage.setClosable(false);
        tabDestillering.setClosable(false);
        tabFad.setClosable(false);
        tabWhisky.setClosable(false);
        tabLager.setClosable(false);

        tabPane.getTabs().addAll(
                tabFrontpage,
                tabDestillering,
                tabFad,
                tabWhisky,
                tabLager
        );

        Scene scene = new Scene(tabPane, 800, 600);

        stage.setScene(scene);
        stage.show();
    }
}