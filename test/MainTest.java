
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

        System.out.println(controller.getDestilleringer());

        Destillat destillat1 = controller.createDestillat("Destilat 1" ,destillering1.getSlutDato(),1000,70);
        Destillat destillat2 = destillering2.createDestillat("Destilat 2" ,destillering2.getSlutDato(),1000,70);


        Fad fad1 = new Fad(1,"Spanien",200,"Brandy","Hans");
        System.out.println("fad 1 har en kapicitet på: " + fad1.getLedigKapacitet() + " liter");
        Påfyldning påfyldning1 = destillat1.createPåfyldning(100,destillering1.getSlutDato(),fad1);
        System.out.println("fad 1 er blevet påfyldt: " + fad1.getPåfyldtMængde() + "liter");
        Påfyldning påfyldning2 = destillat2.createPåfyldning(100,destillering2.getSlutDato(),fad1);
        System.out.println("fad 1 er blevet påfyldt: " + fad1.getPåfyldtMængde() + "liter");

        System.out.println(fad1.getPåfyldninger());
        System.out.println("Er fad 1 klar til aftapning: " + fad1.erKlarTilAftapning(LocalDate.now()));
        System.out.println(fad1.getLedigKapacitet());

        //Destillat 1 metoder
        System.out.println("Destillat 1 har påfyldt: " + destillat1.getPåfyldtMængde() + " liter på fade");
        Produkt prod1 = controller.createProdukt(1,5,"Brønd",50,"yap",KvalitetsStempel.SINGLE_MALT);
        prod1.createWhiskySammensætning(5,destillat1);

        System.out.println("Destillat 1 har brugt: " + destillat1.getBrugtTilProdukter() + " liter på produkter");
        System.out.println("Destillat 1 har: " + destillat1.getTilgængeligMængdeTilProdukt() + " liter tilgængeligt til produkter");


        Produkt prod2 = controller.createProdukt(2,5,"Brønd",50,"yap",KvalitetsStempel.SINGLE_MALT);
        prod2.createWhiskySammensætning(1,destillat2);

    }
}
