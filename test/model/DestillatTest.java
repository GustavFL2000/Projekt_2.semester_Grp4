package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DestillatTest {

    private Destillat destillat;
    private Fad fad;

    @BeforeEach
    void setUp() {
        // Arrange
        destillat = new Destillat(
                "TestDestillat",
                LocalDate.of(2020,1,1),
                100,
                60,
                null
        );

        fad = new Fad(1, "DK", 100, "Sherry", "Lev");
    }

    // ---------------------------
    // getPåfyldtMængde
    // ---------------------------

    @Test
    void T1_getPåfyldtMængde_tomListe() {
        // Arrange (allerede i setUp)

        // Act
        double result = destillat.getPåfyldtMængde();

        // Assert
        assertEquals(0, result);
    }

    @Test
    void T2_getPåfyldtMængde_en() {
        // Arrange
        destillat.createPåfyldning(50, LocalDate.now(), fad);

        // Act
        double result = destillat.getPåfyldtMængde();

        // Assert
        assertEquals(50, result);
    }

    @Test
    void T3_getPåfyldtMængde_flere() {
        // Arrange
        destillat.createPåfyldning(30, LocalDate.now(), fad);
        destillat.createPåfyldning(20, LocalDate.now(), fad);

        // Act
        double result = destillat.getPåfyldtMængde();

        // Assert
        assertEquals(50, result);
    }

    // ---------------------------
    // getRestMængde
    // ---------------------------

    @Test
    void T4_getRestMængde_start() {
        // Act
        double result = destillat.getRestMængde();

        // Assert
        assertEquals(100, result);
    }

    @Test
    void T5_getRestMængde_efterBrug() {
        // Arrange
        destillat.createPåfyldning(40, LocalDate.now(), fad);

        // Act
        double result = destillat.getRestMængde();

        // Assert
        assertEquals(60, result);
    }

    // ---------------------------
    // createPåfyldning
    // ---------------------------

    @Test
    void T7_createPåfyldning_gyldig() {
        // Arrange
        double mængde = 50;
        LocalDate dato = LocalDate.now();

        // Act
        Påfyldning p = destillat.createPåfyldning(mængde, dato, fad);

        // Assert
        assertNotNull(p);
        assertEquals(mængde, p.getMængde());
        assertEquals(destillat, p.getDestillat());
        assertEquals(fad, p.getFad());
    }

    @Test
    void T8_createPåfyldning_mængde0() {
        // Arrange
        double mængde = 0;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> destillat.createPåfyldning(mængde, LocalDate.now(), fad));
    }

    @Test
    void T9_createPåfyldning_forStor() {
        // Arrange
        double mængde = 200;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> destillat.createPåfyldning(mængde, LocalDate.now(), fad));
    }

    @Test
    void T10_createPåfyldning_datoNull() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> destillat.createPåfyldning(50, null, fad));
    }

    @Test
    void T11_createPåfyldning_fadNull() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> destillat.createPåfyldning(50, LocalDate.now(), null));
    }
}