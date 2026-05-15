package usecases;

import controller.Controller;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestUC7OpretWhiskyProdukt {

    private Produkt produkt;
    private Destillat destillat;
    private Fad fad;
    private Leverandør leverandør;
    private Controller controller;
    private Destillering destillering;
    private Maltbatch maltbatch;


    @BeforeEach
    void setUp() {
        IStorage storage = new Storage();
        controller = new Controller(storage);

        maltbatch = new Maltbatch(1, Kornsort.EVERGREEN);

        produkt = new Produkt(1, 10, 40, "Single Malt", KvalitetsStempel.SINGLE_MALT);

        destillat = new Destillat("TestDestillat", LocalDate.now(), 70);

        // Gør destillatet klar til produkter
        // så harNokTilProdukt() bliver true

        destillering = controller.createDestillering(LocalDate.now().minusYears(5), LocalDate.now().minusYears(5).plusDays(1), 70, false, "Test", 100, maltbatch);

        destillering.createDestilleringsMængde(100, destillat);

        leverandør = new Leverandør("BoFade", "Spanien", "12345678");

        fad = new Fad(1, "Spanien", 100, "Sherry", leverandør);

        destillat.createPåfyldning(100, LocalDate.now().minusYears(3), fad);
    }

    @Test
    void TC23_createWhiskySammensætning1() {

        WhiskySammensætning ws = produkt.createWhiskySammensætning(20, destillat);

        assertNotNull(ws);

        assertEquals(20, ws.getMængdeFraDestillat());
    }

    @Test
    void TC24_createWhiskySammensætning2() {

        WhiskySammensætning ws = produkt.createWhiskySammensætning(50, destillat);

        assertEquals(50, ws.getMængdeFraDestillat());
    }

    @Test
    void TC25_createWhiskySammensætning3() {

        WhiskySammensætning ws = produkt.createWhiskySammensætning(0.1, destillat);

        assertEquals(0.1, ws.getMængdeFraDestillat());
    }

    @Test
    void TC26_createWhiskySammensætning4() {

        WhiskySammensætning ws = produkt.createWhiskySammensætning(100, destillat);

        assertEquals(100, ws.getMængdeFraDestillat());
    }

    // -------------------------------------------------
    // Ugyldige testcases createWhiskySammensætning()
    // -------------------------------------------------

    @Test
    void TC27_mængde0() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(0, destillat);
                });
    }

    @Test
    void TC28_mængdeOverTilgængelig() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(101, destillat);
                });
    }

    @Test
    void TC29_negativMængde() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(-1, destillat);
                });
    }

    @Test
    void TC30_destillatNull() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(20, null);
                });
    }

    @Test
    void TC31_destillatUdenNokWhisky() {

        Destillat lilleDestillat = new Destillat("Lille", LocalDate.now(), 70);

        assertThrows(IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(20, lilleDestillat);
                });
    }

    // -------------------------------------------------
    // Gyldige testcases createProdukt()
    // -------------------------------------------------

    @Test
    void TC32_createProdukt1() {

        Produkt produkt = new Produkt(1, 10, 40, "Single Malt", KvalitetsStempel.SINGLE_MALT);

        assertNotNull(produkt);
    }

    @Test
    void TC33_createProdukt2() {

        Produkt produkt = new Produkt(2, 0, 45, "Premium", KvalitetsStempel.BLENDED);

        assertNotNull(produkt);
    }

    @Test
    void TC34_createProdukt3() {

        Produkt produkt = new Produkt(3, 0, 0.1, "Test", KvalitetsStempel.SINGLE_CASK);

        assertNotNull(produkt);
    }

    // -------------------------------------------------
    // Ugyldige testcases createProdukt()
    // -------------------------------------------------

    @Test
    void TC36_produktNr0() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    new Produkt(0, 10, 40, "Test", KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC37_negativProduktNr() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    new Produkt(-1, 10, 40, "Test", KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC38_negativVandMængde() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    new Produkt(1, -1, 40, "Test", KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC39_alkoholProcent0() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    new Produkt(1, 10, 0, "Test", KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC40_negativAlkoholProcent() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    new Produkt(1, 10, -1, "Test", KvalitetsStempel.SINGLE_MALT);
                });
    }

//    @Test
//    void TC41_vandOprindelseNull() {
//
//        assertThrows(IllegalArgumentException.class,
//
//                () -> {
//                    new Produkt(1, 10, 40, "Test", KvalitetsStempel.SINGLE_MALT);
//                });
//    }

    @Test
    void TC42_beskrivelseNull() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    new Produkt(1, 10, 40, null, KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC43_kvalitetsStempelNull() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    new Produkt(1, 10, 40, "Test", null);
                });
    }
}