package test.usecases;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestUC7OpretWhiskyProdukt {

    private Produkt produkt;
    private Destillat destillat;
    private Fad fad;
    private Leverandør leverandør;

    @BeforeEach
    void setUp() {

        produkt = new Produkt(
                1,
                10,
                "Kildevand",
                40,
                "Single Malt",
                KvalitetsStempel.SINGLE_MALT);

        destillat = new Destillat(
                "TestDestillat",
                LocalDate.now(),
                100,
                70);

        // Gør destillatet klar til produkter
        // så harNokTilProdukt() bliver true

        leverandør = new Leverandør("BoFade", "Spanien", "12345678");

        fad = new Fad(1, "Spanien", 100, "Sherry", leverandør);

        destillat.createPåfyldning(
                100,
                LocalDate.now().minusYears(3),
                fad);
    }

    @Test
    void TC23_createWhiskySammensætning1() {

        WhiskySammensætning ws =
                produkt.createWhiskySammensætning(
                        20,
                        destillat);

        assertNotNull(ws);

        assertEquals(
                20,
                ws.getMængdeFraDestillat());
    }

    @Test
    void TC24_createWhiskySammensætning2() {

        WhiskySammensætning ws =
                produkt.createWhiskySammensætning(
                        50,
                        destillat);

        assertEquals(
                50,
                ws.getMængdeFraDestillat());
    }

    @Test
    void TC25_createWhiskySammensætning3() {

        WhiskySammensætning ws =
                produkt.createWhiskySammensætning(
                        0.1,
                        destillat);

        assertEquals(
                0.1,
                ws.getMængdeFraDestillat());
    }

    @Test
    void TC26_createWhiskySammensætning4() {

        WhiskySammensætning ws =
                produkt.createWhiskySammensætning(
                        100,
                        destillat);

        assertEquals(
                100,
                ws.getMængdeFraDestillat());
    }

    // -------------------------------------------------
    // Ugyldige testcases createWhiskySammensætning()
    // -------------------------------------------------

    @Test
    void TC27_mængde0() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(
                            0,
                            destillat);
                });
    }

    @Test
    void TC28_mængdeOverTilgængelig() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(
                            101,
                            destillat);
                });
    }

    @Test
    void TC29_negativMængde() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(
                            -1,
                            destillat);
                });
    }

    @Test
    void TC30_destillatNull() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(
                            20,
                            null);
                });
    }

    @Test
    void TC31_destillatUdenNokWhisky() {

        Destillat lilleDestillat =
                new Destillat(
                        "Lille",
                        LocalDate.now(),
                        10,
                        70);

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    produkt.createWhiskySammensætning(
                            20,
                            lilleDestillat);
                });
    }

    // -------------------------------------------------
    // Gyldige testcases createProdukt()
    // -------------------------------------------------

    @Test
    void TC32_createProdukt1() {

        Produkt produkt = new Produkt(
                1,
                10,
                "Kildevand",
                40,
                "Single Malt",
                KvalitetsStempel.SINGLE_MALT);

        assertNotNull(produkt);
    }

    @Test
    void TC33_createProdukt2() {

        Produkt produkt = new Produkt(
                2,
                0,
                "Kilde",
                45,
                "Premium",
                KvalitetsStempel.BLENDED);

        assertNotNull(produkt);
    }

    @Test
    void TC34_createProdukt3() {

        Produkt produkt = new Produkt(
                3,
                0,
                "Kilde",
                0.1,
                "Test",
                KvalitetsStempel.SINGLE_CASK);

        assertNotNull(produkt);
    }

    // -------------------------------------------------
    // Ugyldige testcases createProdukt()
    // -------------------------------------------------

    @Test
    void TC36_produktNr0() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    new Produkt(
                            0,
                            10,
                            "Kilde",
                            40,
                            "Test",
                            KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC37_negativProduktNr() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    new Produkt(
                            -1,
                            10,
                            "Kilde",
                            40,
                            "Test",
                            KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC38_negativVandMængde() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    new Produkt(
                            1,
                            -1,
                            "Kilde",
                            40,
                            "Test",
                            KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC39_alkoholProcent0() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    new Produkt(
                            1,
                            10,
                            "Kilde",
                            0,
                            "Test",
                            KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC40_negativAlkoholProcent() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    new Produkt(
                            1,
                            10,
                            "Kilde",
                            -1,
                            "Test",
                            KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC41_vandOprindelseNull() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    new Produkt(
                            1,
                            10,
                            null,
                            40,
                            "Test",
                            KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC42_beskrivelseNull() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    new Produkt(
                            1,
                            10,
                            "Kilde",
                            40,
                            null,
                            KvalitetsStempel.SINGLE_MALT);
                });
    }

    @Test
    void TC43_kvalitetsStempelNull() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    new Produkt(
                            1,
                            10,
                            "Kilde",
                            40,
                            "Test",
                            null);
                });
    }
}