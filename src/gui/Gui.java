package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.Lager;

public class Gui extends Application {

    private static Controller controller;

    public static void setController(Controller c) {
        controller = c;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Whisky System");

        TabPane tabPane = new TabPane();

        //Forside
        Tab tabFrontpage = new Tab("Forside");
        tabFrontpage.setContent(new FrontPagePane());

        //Destillering
        Tab tabDestillering = new Tab("Registrer Destillering");
        tabDestillering.setContent(new RegistrerDestilleringPane(controller));

        //Destillat
        OpretDestillatPane opretDestillatPane = new OpretDestillatPane(controller);
        Tab tabDestillat = new Tab("Registrer Destillat");
        tabDestillat.setContent(opretDestillatPane);

        //Opdaterer comboBoxne når du trykker på tabben for destillat pane
        tabDestillat.setOnSelectionChanged(event -> {
            if (tabDestillat.isSelected()){
             opretDestillatPane.updateControls();
            }
        });

        //Fad
        FadPane fadPane = new FadPane(controller);
        Tab tabFad = new Tab("Fad");
        tabFad.setContent(fadPane);

        //Opdaterer listview når du trykker på tabben for destillat pane
        tabFad.setOnSelectionChanged(event -> {
            if (tabFad.isSelected()){
                fadPane.updateControls();
            }
        });

        //Whisky produkt
        Tab tabWhisky = new Tab("Opret Whisky Produkt");
        tabWhisky.setContent(new OpretWhiskyProduktPane(controller));

        //Lager
        LagerPane lagerPane = new LagerPane(controller);
        Tab tabLager = new Tab("Lager");
        tabLager.setContent(lagerPane);

        tabLager.setOnSelectionChanged(event -> {
            if (tabLager.isSelected()){
                lagerPane.updateControls();
            }
        });


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