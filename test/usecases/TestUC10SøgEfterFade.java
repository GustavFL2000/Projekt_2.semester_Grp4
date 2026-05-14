package usecases;

import controller.Controller;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUC10SøgEfterFade {

    private Controller controller;

    private Lager lager1;
    private Lager lager2;

    private Reol reol1;
    private Reol reol2;

    private Fad fad1;
    private Fad fad2;
    private Fad fad3;

    private Destillat destillat;

    private Leverandør leverandør;
    private Destillering destillering;
    private Maltbatch maltbatch;

    @BeforeEach
    void setUp() {

        IStorage storage = new Storage();
        controller = new Controller(storage);

        maltbatch = new Maltbatch(1, Kornsort.EVERGREEN);

        leverandør = new Leverandør(
                "test Supplier",
                "Skotland",
                "hej"
        );

        lager1 = controller.createLager(
                "Lager 1",
                "Adresse 1"
        );

        lager2 = controller.createLager(
                "Lager 2",
                "Adresse 2"
        );

        reol1 = lager1.createReol();
        reol2 = lager2.createReol();

        fad1 = controller.createFad(
                "Skotland",
                100,
                "Sherry",
                leverandør
        );

        fad2 = controller.createFad(
                "Irland",
                120,
                "Bourbon",
                leverandør
        );

        fad3 = controller.createFad(
                "USA",
                90,
                "Sherry",
                leverandør
        );

        reol1.placerFad(fad1, 0, 0);
        reol1.placerFad(fad2, 0, 1);
        reol2.placerFad(fad3, 0, 0);

        // Opret destillat først
        destillat = new Destillat(
                "TestDestillat",
                LocalDate.now(),
                70
        );

        // Opret destillering
        destillering =
                controller.createDestillering(
                        LocalDate.now().minusYears(5),
                        LocalDate.now().minusYears(5).plusDays(1),
                        70,
                        false,
                        "Test",
                        150,
                        maltbatch
                );

        // Kobl dem sammen
        destillering.createDestilleringsMængde(
                150,
                destillat
        );

        // Nu virker påfyldninger
        destillat.createPåfyldning(
                50,
                LocalDate.now().minusYears(4),
                fad1
        );

        destillat.createPåfyldning(
                50,
                LocalDate.now().minusYears(1),
                fad2
        );

        destillat.createPåfyldning(
                50,
                LocalDate.now().minusYears(5),
                fad3
        );
    }

    // -------------------------------------------------
    // Gyldige testcases
    // -------------------------------------------------

    @Test
    void TC69_søgEfterFade1() {

        // Act
        List<Fad> result = controller.søgEfterFade(1, null, null, null);

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(fad1));
    }

    @Test
    void TC70_søgEfterFade2() {

        // Act
        List<Fad> result = controller.søgEfterFade(null, lager1, null, null);

        // Assert
        assertEquals(2, result.size());

        assertTrue(result.contains(fad1));
        assertTrue(result.contains(fad2));
    }

    @Test
    void TC71_søgEfterFade3() {

        // Act
        List<Fad> result = controller.søgEfterFade(null, null, "Sherry", null);

        // Assert
        assertEquals(2, result.size());

        assertTrue(result.contains(fad1));
        assertTrue(result.contains(fad3));
    }

    @Test
    void TC72_søgEfterFade4() {

        // Act
        List<Fad> result = controller.søgEfterFade(null, null, null, 3);

        // Assert
        assertEquals(2, result.size());

        assertTrue(result.contains(fad1));
        assertTrue(result.contains(fad3));
    }

    @Test
    void TC73_søgEfterFade5() {

        // Act
        List<Fad> result = controller.søgEfterFade(1, lager1, "Sherry", 3);

        // Assert
        assertEquals(1, result.size());

        assertTrue(result.contains(fad1));
    }

    @Test
    void TC74_søgEfterFade6() {

        // Act
        List<Fad> result = controller.søgEfterFade(null, null, null, null);

        // Assert
        assertEquals(3, result.size());
    }

    // -------------------------------------------------
    // Ugyldige testcases
    // -------------------------------------------------

    @Test
    void TC76_fadNr0() {

        // Act
        List<Fad> result = controller.søgEfterFade(0, null, null, null);

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void TC77_negativFadNr() {

        // Act
        List<Fad> result = controller.søgEfterFade(-1, null, null, null);

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void TC78_negativAlder() {

        assertThrows(
                IllegalArgumentException.class,
                () -> controller.søgEfterFade(null, null, null, -1)
        );
    }
}