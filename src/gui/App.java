package gui;

import controller.Controller;
import javafx.application.Application;
import model.*;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;

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
        Maltbatch maltbatch = controller.createMaltBatch(Kornsort.EVERGREEN);

        //Leverandør og fad
        Leverandør leverandør = controller.createLeverandør("Bo´s fade", "Italien", "25342123");
        controller.createFad("Spanien", 50, "Cherry", leverandør);

        //Destillering
        Destillering destillering1 = controller.createDestillering(LocalDate.of(2020,11,4),
                LocalDate.of(2022,11,5), 70, false,"Ny november batch", 200, maltbatch);

        //Destillat
        Destillat destillat1 = controller.createDestillat("Destilat 1" ,destillering1.getSlutDato(),70);

        //Destilleringsmængde
        controller.createDestilleringsMængde(100, destillering1, destillat1);

        // lager
        Lager lager = controller.createLagerMedReoler("Lager 1", "Solskinvej 1, 8000, Århus", 2);
        Lager lager2 = controller.createLagerMedReoler("Lager 2", "Solskinvej 2, 8000, Århus", 3);
    }
}