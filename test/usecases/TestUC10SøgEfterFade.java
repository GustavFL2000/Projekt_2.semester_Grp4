package usecases;

import controller.Controller;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    void setUp() {

        // Arrange
        controller = new Controller(new Storage());

        leverandør = new Leverandør(
                "Macallan",
                "Skotland",
                "kontakt@macallan.com");

        lager1 = controller.createLager(
                "Lager 1",
                "Adresse 1");

        lager2 = controller.createLager(
                "Lager 2",
                "Adresse 2");

        reol1 = new Reol(1, lager1);
        reol2 = new Reol(2, lager2);

        fad1 = controller.createFad(
                1,
                "Skotland",
                100,
                "Sherry",
                leverandør);

        fad2 = controller.createFad(
                2,
                "Irland",
                120,
                "Bourbon",
                leverandør);

        fad3 = controller.createFad(
                3,
                "USA",
                90,
                "Sherry",
                leverandør);

        reol1.placerFad(fad1, 0, 0);
        reol1.placerFad(fad2, 0, 1);
        reol2.placerFad(fad3, 0, 0);

        destillat = new Destillat(
                "TestDestillat",
                LocalDate.now(),
                500,
                70);

        // Påfyldninger til alderstest
        destillat.createPåfyldning(
                50,
                LocalDate.now().minusYears(4),
                fad1);

        destillat.createPåfyldning(
                50,
                LocalDate.now().minusYears(1),
                fad2);

        destillat.createPåfyldning(
                50,
                LocalDate.now().minusYears(5),
                fad3);
    }

    // -------------------------------------------------
    // Gyldige testcases
    // -------------------------------------------------

    @Test
    void TC69_søgEfterFade1() {

        // Act
        List<Fad> result =
                controller.søgEfterFade(
                        1,
                        null,
                        null,
                        null);

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(fad1));
    }

    @Test
    void TC70_søgEfterFade2() {

        // Act
        List<Fad> result =
                controller.søgEfterFade(
                        null,
                        lager1,
                        null,
                        null);

        // Assert
        assertEquals(2, result.size());

        assertTrue(result.contains(fad1));
        assertTrue(result.contains(fad2));
    }

    @Test
    void TC71_søgEfterFade3() {

        // Act
        List<Fad> result =
                controller.søgEfterFade(
                        null,
                        null,
                        "Sherry",
                        null);

        // Assert
        assertEquals(2, result.size());

        assertTrue(result.contains(fad1));
        assertTrue(result.contains(fad3));
    }

    @Test
    void TC72_søgEfterFade4() {

        // Act
        List<Fad> result =
                controller.søgEfterFade(
                        null,
                        null,
                        null,
                        3);

        // Assert
        assertEquals(2, result.size());

        assertTrue(result.contains(fad1));
        assertTrue(result.contains(fad3));
    }

    @Test
    void TC73_søgEfterFade5() {

        // Act
        List<Fad> result =
                controller.søgEfterFade(
                        1,
                        lager1,
                        "Sherry",
                        3);

        // Assert
        assertEquals(1, result.size());

        assertTrue(result.contains(fad1));
    }

    @Test
    void TC74_søgEfterFade6() {

        // Act
        List<Fad> result =
                controller.søgEfterFade(
                        null,
                        null,
                        null,
                        null);

        // Assert
        assertEquals(3, result.size());
    }

    // -------------------------------------------------
    // Ugyldige testcases
    // -------------------------------------------------

    @Test
    void TC76_fadNr0() {

        // Act
        List<Fad> result =
                controller.søgEfterFade(
                        0,
                        null,
                        null,
                        null);

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void TC77_negativFadNr() {

        // Act
        List<Fad> result =
                controller.søgEfterFade(
                        -1,
                        null,
                        null,
                        null);

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void TC78_negativAlder() {

        // Act
        List<Fad> result =
                controller.søgEfterFade(
                        null,
                        null,
                        null,
                        -1);

        // Assert
        assertEquals(0, result.size());
    }
}