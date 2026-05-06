package model;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    private Fad fad;
    private Destillat destillat;

    @BeforeEach
    void setUp() {
        fad = new Fad(1, "DK", "Eg", 100, "Sherry", "Leverandør");

        destillat = new Destillat(
                "TestDestillat",
                LocalDate.now().minusYears(5),
                100,
                60,
                null // ok hvis ikke brugt
        );
    }

    // ---------------------------
    // Gyldige cases
    // ---------------------------

    @Test
    void erKlarTilAftapning_true_over3År() {
        destillat.createPåfyldning(50, LocalDate.now().minusYears(4), fad);

        assertTrue(fad.erKlarTilAftapning(LocalDate.now()));
    }

    @Test
    void erKlarTilAftapning_false_under3År() {
        destillat.createPåfyldning(50, LocalDate.now().minusYears(2), fad);

        assertFalse(fad.erKlarTilAftapning(LocalDate.now()));
    }

    // ---------------------------
    // Boundary tests
    // ---------------------------

    @Test
    void erKlarTilAftapning_præcis3År() {
        destillat.createPåfyldning(50, LocalDate.now().minusYears(3), fad);

        assertTrue(fad.erKlarTilAftapning(LocalDate.now()));
    }

    @Test
    void erKlarTilAftapning_true_3ÅrPlus1Dag() {
        destillat.createPåfyldning(50, LocalDate.now().minusYears(3).minusDays(1), fad);

        assertTrue(fad.erKlarTilAftapning(LocalDate.now()));
    }

    // ---------------------------
    // Ugyldige cases
    // ---------------------------

    @Test
    void erKlarTilAftapning_exception_nårDatoErNull() {
        destillat.createPåfyldning(50, LocalDate.now().minusYears(4), fad);

        assertThrows(RuntimeException.class, () ->
                fad.erKlarTilAftapning(null)
        );
    }

    @Test
    void erKlarTilAftapning_exception_nårIngenPåfyldninger() {
        assertThrows(Exception.class, () ->
                fad.erKlarTilAftapning(LocalDate.now())
        );
    }
}