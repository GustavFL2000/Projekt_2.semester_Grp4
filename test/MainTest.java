package test;

import controller.Controller;
import model.*;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {
        IStorage storage = new Storage();
        Controller controller = new Controller(storage);
        Maltbatch maltbatch1 = new Maltbatch(1, Kornsort.EVERGREEN);
        Maltbatch maltbatch2 = new Maltbatch(1, Kornsort.STAIRWAY);

       Destillering destillering1 = controller.createDestillering(1,LocalDate.of(2020,11,4),LocalDate.of(2022,11,5), 70, false,"Ny november batch", 1000, maltbatch1);
        Destillering destillering2 = controller.createDestillering(2,LocalDate.of(2020,1,1),LocalDate.of(2022,1,2), 70, false,"Nyårs bryg", 1000, maltbatch1);
        controller.createDestillering(3,LocalDate.of(2020,7,4),LocalDate.of(2023,7,5), 70, false,"Fyrværkri med goffeluffe whiskey", 1000, maltbatch2);

        System.out.println(controller.getDestilleringer());

        Destillat destillat1 = destillering1.createDestillat("Destilat 1" ,destillering1.getSlutDato(),1000,70);
        Destillat destillat2 = destillering2.createDestillat("Destilat 2" ,destillering2.getSlutDato(),1000,70);

        Fad fad1 = new Fad(1,"Spanien",200,"Brandy","Hans");
        Påfyldning påfyldning1 = destillat1.createPåfyldning(100,destillering1.getSlutDato(),fad1);
        Påfyldning påfyldning2 = destillat2.createPåfyldning(100,destillering2.getSlutDato(),fad1);


        System.out.println(fad1.getPåfyldninger());
        System.out.println(fad1.erKlarTilAftapning(LocalDate.now()));

        Produkt prod1 = controller.createProdukt(1,5,"Brønd",50,"yap",KvalitetsStempel.SINGLE_MALT);
        prod1.createWhiskySammensætning(1,fad1);

        Produkt prod2 = controller.createProdukt(1,5,"Brønd",50,"yap",KvalitetsStempel.SINGLE_MALT);
        prod2.createWhiskySammensætning(1,fad1);


        System.out.println(fad1.getLedigKapacitet());
        System.out.println(fad1.getBrugtTilProdukter());


    }
}
