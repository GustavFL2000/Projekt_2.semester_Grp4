
import controller.Controller;
import model.*;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        IStorage storage = new Storage();
        Controller controller = new Controller(storage);

        // Opretter maltbatch, som bruges til destilleringerne
        Maltbatch maltbatch1 = controller.createMaltBatch( Kornsort.EVERGREEN);

        // Registrerer færdige destilleringer
       Destillering destillering1 = controller.createDestillering(LocalDate.of(2020,11,4),
               LocalDate.of(2022,11,5), 70, false,"Ny november batch", 200, maltbatch1);
        Destillering destillering2 = controller.createDestillering(LocalDate.of(2020,1,1),
                LocalDate.of(2022,1,2), 70, false,"Nyårs bryg", 200, maltbatch1);

        System.out.println("Registrerede destilleringer:");
        System.out.println(controller.getDestilleringer());

        // Opretter destillater
        Destillat destillat1 = controller.createDestillat("Destilat 1" ,destillering1.getSlutDato(),70);
        Destillat destillat2 = controller.createDestillat("Destilat 2" ,destillering2.getSlutDato(),70);

        // Fordeler mængde fra destillering til destillat
        destillering1.createDestilleringsMængde(200, destillat1);
        destillering2.createDestilleringsMængde(200, destillat2);

        System.out.println();
        System.out.println("Destillering 1 har fordelt: " + destillering1.getPådeltMængde() + " liter");
        System.out.println("Destillering 1 har tilbage: " + destillering1.getRestMængde() + " liter");

        System.out.println("Destillering 2 har fordelt: " + destillering2.getPådeltMængde() + " liter");
        System.out.println("Destillering 2 har tilbage: " + destillering2.getRestMængde() + " liter");

        //Oprettet en leverandør til fade
     Leverandør fad1Leverandør = controller.createLeverandør("Bo´fade", "Italien", "20212021");

        // Opretter et fad
        Fad fad1 = controller.createFad("Spanien",200,"Brandy", fad1Leverandør);
        Fad fad2 = controller.createFad("Spanien",200,"Rom", fad1Leverandør);


        System.out.println();
        System.out.println("Fad 1 har en kapacitet på: " + fad1.getLedigKapacitet() + " liter");

        // Påfylder destillat på fad
        destillat1.createPåfyldning(100, destillering1.getSlutDato(), fad1);
        System.out.println("Fad 1 er blevet påfyldt: " + fad1.getPåfyldtMængde() + " liter");

        destillat2.createPåfyldning(100, destillering2.getSlutDato(), fad2);
        System.out.println("Fad 1 er blevet påfyldt: " + fad2.getPåfyldtMængde() + " liter");

        // Viser fadets påfyldningshistorik
        System.out.println();
        System.out.println("Påfyldninger på fad 1:");
        System.out.println(fad1.getPåfyldninger());

        // Tjekker om fadet er klar til aftapning
        System.out.println("Er fad 1 klar til aftapning: " + fad1.erKlarTilAftapning(LocalDate.now()));
        System.out.println("Fad 1 har: " + fad1.getLedigKapacitet() + " liter ledig kapacitet");

        // Viser hvor meget destillat 1 der er brugt på fade
        System.out.println();
        System.out.println("Destillat 1 har påfyldt: " + destillat1.getPåfyldtMængde() + " liter på fade");

        // Opretter et whiskyprodukt
        Produkt prod1 = controller.createProdukt(
                5,
                50,
                "Single malt lavet på november batch",
                KvalitetsStempel.SINGLE_MALT
        );

        // Sammensætter produktet med destillat
        prod1.createWhiskySammensætning(5, destillat1);

        System.out.println("Destillat 1 har brugt: " + destillat1.getBrugtTilProdukter() + " liter på produkter");
        System.out.println("Destillat 1 har: " + destillat1.getTilgængeligMængdeTilProdukt() + " liter tilgængeligt til produkter");

        // Opretter endnu et whiskyprodukt
        Produkt prod2 = controller.createProdukt(
                5,
                50,
                "Single malt lavet på nyårs bryg",
                KvalitetsStempel.SINGLE_MALT
        );

        // Sammensætter produktet med et andet destillat
        prod1.createWhiskySammensætning(1, destillat2);

        System.out.println("Destillat 2 har brugt: " + destillat2.getBrugtTilProdukter() + " liter på produkter");
        System.out.println("Destillat 2 har: " + destillat2.getTilgængeligMængdeTilProdukt() + " liter tilgængeligt til produkter");

        // Viser koblingen mellem destillat og destillering
        System.out.println();
        System.out.println("Destilleringsmængder for destillat 1:");
        System.out.println(destillat1.getDestilleringsMængder());

        System.out.println();
        System.out.println(fad1.getLeverandør());

        //Opretter lager
        Lager lager1 = controller.createLager("ceresPark", "Forenden af satdion alle");
        Reol reol1 = lager1.createReol();
        System.out.println("Antal ledige pladser: " + lager1.getLedigePladser());

        //Placerer fade
        reol1.placerFad(fad1,1,1);
        reol1.placerFad(fad2,1,2);

        System.out.println("Antal ledige pladser: " + lager1.getLedigePladser());

        System.out.println("Placering: "+fad1.getReol().getFad(1,1));
        fad1.getReol().flytFad(fad1,reol1,2,2);
        System.out.println("Placering: "+fad1.getReol().getFad(1,1));
        System.out.println("Placering: "+fad1.getReol().getFad(2,2));

        System.out.println(reol1.getFadPlacering(fad1));
        System.out.println(reol1.getAlleFade());

        // produkt / flaske
        Lager lager2 = controller.createLager( "a", "a");

        // controller søgEfterFade
        System.out.println("søg");
        System.out.println(controller.søgEfterFade(null, null, null, 4));

        System.out.println(lager1.getLagerNr());
        System.out.println(lager2.getLagerNr());

        List<Flaske> flasker = controller.createFlasker(100,prod1,10);

        for (Flaske f : flasker) {
            System.out.println(f.getFlaskeNr());
        }

        List<Flaske> flasker1 = controller.createFlasker(100,prod1,10);

        for (Flaske f : flasker1) {
            System.out.println(f.getFlaskeNr());
        }

        System.out.println(controller.visHistorik(10));
    }
}
