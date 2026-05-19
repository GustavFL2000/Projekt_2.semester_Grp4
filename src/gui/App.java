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

    public static void initStorage(Controller controller) {
        // Maltbatch
        Maltbatch maltbatch = controller.createMaltBatch(Kornsort.EVERGREEN);

        // Destillering
        Destillering destillering1 = controller.createDestillering(LocalDate.of(2020, 11, 4), LocalDate.of(2022, 11, 5), 70, false, "Ny november batch", 100, maltbatch);

        // Destillater
        Destillat destillat1 = controller.createDestillat("Destillat 1", destillering1.getSlutDato(), 70);

        Destillat destillat2 = controller.createDestillat("Destillat 2", destillering1.getSlutDato(), 70);

        // Destilleringsmængder
        controller.createDestilleringsMængde(50, destillering1, destillat1);
        controller.createDestilleringsMængde(50, destillering1, destillat2);

        // Leverandør og fade
        Leverandør leverandør = controller.createLeverandør("Bo´s fade", "Italien", "25342123");

        Fad fad1 = controller.createFad("Spanien", 50, "Cherry", leverandør);
        Fad fad2 = controller.createFad("Spanien", 100, "Cherry", leverandør);

        // Påfyldninger
        // Klar til produkt
        controller.createPåfyldning(50, LocalDate.of(2023, 5, 14), destillat1, fad1);

        // Ikke klar til produkt endnu
        controller.createPåfyldning(50, LocalDate.of(2026, 5, 14), destillat2, fad2);

        // Lager
        Lager lager1 = controller.createLagerMedReoler("Lager 1", "Solskinvej 1, 8000, Århus", 2);

        Lager lager2 = controller.createLagerMedReoler("Lager 2", "Solskinvej 2, 8000, Århus", 3);

        // Placering af fade
        Reol reol1 = lager1.getReoler().get(0);
        reol1.placerFad(fad1, 0, 0);
        reol1.placerFad(fad2, 0, 1);

        // Produkt
        Produkt produkt = controller.createProdukt(10, 45, "God whisky", KvalitetsStempel.SINGLE_MALT);

        // Whisky sammensætning
        produkt.createWhiskySammensætning(10, destillat1);

        //flasker
        controller.createFlasker(750, produkt, 10);
    }
}