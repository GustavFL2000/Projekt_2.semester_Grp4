package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Gui extends Application {

    private static Controller controller;

    public static void setController(Controller c) {
        controller = c;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Whisky System");

        TabPane tabPane = new TabPane();

        Tab tabFrontpage = new Tab("Forside");
        tabFrontpage.setContent(new FrontPagePane());

        Tab tabDestillering = new Tab("Registrer Destillering");
        tabDestillering.setContent(new RegistrerDestilleringPane(controller));

        OpretDestillatPane opretDestillatPane = new OpretDestillatPane(controller);
        Tab tabDestillat = new Tab("Registrer Destillat");
        tabDestillat.setContent(opretDestillatPane);

        //Opdaterer comboBoxne når du trykker på tabben
        tabDestillat.setOnSelectionChanged(event -> {
            if (tabDestillat.isSelected()){
             opretDestillatPane.updateControls();
            }
        });

        Tab tabFad = new Tab("Fad");
        tabFad.setContent(new FadPane(controller));

        Tab tabWhisky = new Tab("Opret Whisky Produkt");
        tabWhisky.setContent(new OpretWhiskyProduktPane(controller));

        Tab tabLager = new Tab("Lager");
        tabLager.setContent(new LagerPane(controller));

        tabFrontpage.setClosable(false);
        tabDestillering.setClosable(false);
        tabDestillat.setClosable(false);
        tabFad.setClosable(false);
        tabWhisky.setClosable(false);
        tabLager.setClosable(false);

        tabPane.getTabs().addAll(
                tabFrontpage,
                tabDestillering,
                tabDestillat,
                tabFad,
                tabWhisky,
                tabLager
        );

        Scene scene = new Scene(tabPane, 800, 600);

        stage.setScene(scene);
        stage.show();
    }
}