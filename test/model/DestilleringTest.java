package model;

import model.Destillering;
import model.Destillat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DestilleringTest {

    private Destillering destillering;

    @BeforeEach
    void setUp() {
        // Arrange
        destillering = new Destillering(
                1,
                LocalDate.of(2020,1,1),
                LocalDate.of(2020,1,10),
                60,
                false,
                "Test",
                100,
                null
        );
    }

    // ---------------------------
    // createDestillat
    // ---------------------------

    @Test
    void T13_opretterDestillat() {
        // Arrange
        String navn = "TestDestillat";
        LocalDate dato = LocalDate.now();
        double mængde = 50;

        // Act
        Destillat d = destillering.createDestillat(navn, dato, mængde, 60);

        // Assert
        assertNotNull(d);
        assertEquals(destillering, d.getDestillering());
        assertTrue(destillering.getPåfyldninger().contains(d));
    }

    @Test
    void T14_datoNull() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> destillering.createDestillat("Test", null, 50, 60));
    }

    @Test
    void T15_mængde0() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> destillering.createDestillat("Test", LocalDate.now(), 0, 60));
    }
}