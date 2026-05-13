package gui;

import controller.Controller;
import javafx.application.Application;
import model.Kornsort;
import storage.IStorage;
import storage.Storage;

public class App {

    public static void main(String[] args) {

        IStorage storage = new Storage();
        Controller controller = new Controller(storage);
        initStorage(controller);

        Gui.setController(controller);

        Application.launch(Gui.class);
    }

    public static void initStorage (Controller controller){
        // Maltbatch
        controller.createMaltBatch( Kornsort.EVERGREEN);

        //Leverandør
        controller.createLeverandør("Bo´s fade", "Italien", "25342123");
    }
}