package usecases;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestUC6FindFadeKlarTilAftapning {

    private Fad fad;
    private Destillat destillat;
    private Leverandør leverandør;

    @BeforeEach
    void setUp() {
        // Arrange
        leverandør = new Leverandør("test Supplier", "Skotland", "hej");

        fad = new Fad(1, "Skotland", 100, "Sherry", leverandør);

        destillat = new Destillat("TestDestillat", LocalDate.now().minusYears(5), 70);
    }


    // -------------------------------------------------
    // Gyldige testcases erKlarTilAftapning()
    // -------------------------------------------------

    @Test
    void TC63_erKlarTilAftapning1() {

        // Arrange
        destillat.createPåfyldning(50, LocalDate.now().minusYears(4), fad);

        // Act
        boolean resultat = fad.erKlarTilAftapning(LocalDate.now());

        // Assert
        assertTrue(resultat);
    }

    @Test
    void TC64_erKlarTilAftapning2() {

        // Arrange
        destillat.createPåfyldning(50, LocalDate.now().minusYears(3), fad);

        // Act
        boolean resultat = fad.erKlarTilAftapning(LocalDate.now());

        // Assert
        assertTrue(resultat);
    }

    @Test
    void TC65_erKlarTilAftapning3() {

        // Arrange
        destillat.createPåfyldning(50, LocalDate.now().minusYears(5), fad);

        // Act
        boolean resultat = fad.erKlarTilAftapning(LocalDate.now());

        // Assert
        assertTrue(resultat);
    }

    // -------------------------------------------------
    // Ugyldige testcases erKlarTilAftapning()
    // -------------------------------------------------

    @Test
    void TC66_ikke3ÅrEndnu() {

        // Arrange
        destillat.createPåfyldning(50, LocalDate.now().minusYears(2), fad);

        // Act
        boolean resultat = fad.erKlarTilAftapning(LocalDate.now());

        // Assert
        assertFalse(resultat);
    }

    @Test
    void TC67_dagsDatoNull() {

        // Arrange
        destillat.createPåfyldning(50, LocalDate.now().minusYears(4), fad);

        // Act + Assert
        assertThrows(IllegalArgumentException.class,

                () -> {
                    fad.erKlarTilAftapning(null);
                });
    }

    @Test
    void TC68_ingenPåfyldninger() {

        // Act + Assert
        assertThrows(IllegalArgumentException.class,

                () -> {
                    fad.erKlarTilAftapning(LocalDate.now());
                });
    }
}