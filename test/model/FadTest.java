package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    private Fad fad;
    private Destillat destillat;

    @BeforeEach
    void setUp() {
        // Arrange
        fad = new Fad(1, "DK", "Eg", 100, "Sherry", "Lev");

        destillat = new Destillat(
                "Test",
                LocalDate.of(2020,1,1),
                100,
                60,
                null
        );
    }

    // ---------------------------
    // erKlarTilAftapning
    // ---------------------------

    @Test
    void T1_klar_over3år() {
        // Arrange
        LocalDate dagsDato = LocalDate.of(2026,1,1);
        destillat.createPåfyldning(50, dagsDato.minusYears(4), fad);

        // Act
        boolean result = fad.erKlarTilAftapning(dagsDato);

        // Assert
        assertTrue(result);
    }

    @Test
    void T2_klar_præcis3år() {
        // Arrange
        LocalDate dagsDato = LocalDate.of(2026,1,1);
        destillat.createPåfyldning(50, dagsDato.minusYears(3), fad);

        // Act
        boolean result = fad.erKlarTilAftapning(dagsDato);

        // Assert
        assertTrue(result);
    }

    @Test
    void T3_ikkeKlar() {
        // Arrange
        LocalDate dagsDato = LocalDate.of(2026,1,1);
        destillat.createPåfyldning(50, dagsDato.minusYears(2), fad);

        // Act
        boolean result = fad.erKlarTilAftapning(dagsDato);

        // Assert
        assertFalse(result);
    }

    @Test
    void T4_exception_datoNull() {
        // Act + Assert
        assertThrows(RuntimeException.class,
                () -> fad.erKlarTilAftapning(null));
    }

    @Test
    void T5_exception_ingenPåfyldning() {
        // Arrange
        LocalDate dagsDato = LocalDate.of(2026,1,1);

        // Act + Assert
        assertThrows(Exception.class,
                () -> fad.erKlarTilAftapning(dagsDato));
    }
}