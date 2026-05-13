package gui;

import controller.Controller;
import javafx.application.Application;
import storage.IStorage;
import storage.Storage;

public class App {

    public static void main(String[] args) {

        IStorage storage = new Storage();
        Controller controller = new Controller(storage);

        Gui.setController(controller);

        Application.launch(Gui.class);
    }
}