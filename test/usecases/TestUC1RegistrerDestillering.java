package test.usecases;

import controller.Controller;
import model.Destillering;
import model.Kornsort;
import model.Maltbatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class TestUC1RegistrerDestillering {
    private Controller controller;
    private Maltbatch maltbatch;

    @BeforeEach
    void setUp() {
        IStorage storage = new Storage();
        controller = new Controller(storage);
        maltbatch = new Maltbatch(1, Kornsort.EVERGREEN);
    }

    // -------------------------------------------------
    // Gyldige testcases
    // -------------------------------------------------

    @Test
    void TC1_createDestillering1() {

        Destillering d = controller.createDestillering(LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 10), 45, true, "Test", 100, maltbatch);

        assertNotNull(d);
        assertEquals(1, d.getDestilleringsID());
    }

    @Test
    void TC2_createDestillering2() {

        Destillering d = controller.createDestillering(LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 10), 60, false, "Ny destillering", 200, maltbatch);

        assertNotNull(d);
        assertEquals(10, d.getDestilleringsID());
    }

    @Test
    void TC3_createDestillering_Graensevaerdi() {

        Destillering d = controller.createDestillering(

                LocalDate.now(), LocalDate.now(), 0.1, true, "Grænseværdi", 1, maltbatch);

        assertNotNull(d);
    }

    @Test
    void TC4_createDestillering_Graensevaerdi() {

        Destillering d = controller.createDestillering(

                LocalDate.now(), LocalDate.now(), 100, true, "Grænseværdi", 500, maltbatch);

        assertNotNull(d);
    }

    // -------------------------------------------------
    // Ugyldige testcases
    // -------------------------------------------------

    @Test
    void TC5_destilleringsID0() {

        assertThrows(IllegalArgumentException.class, () -> {
            controller.createDestillering(

                    LocalDate.now(), LocalDate.now(), 45, true, "Fejl", 100, maltbatch);
        });
    }

    @Test
    void TC6_alkoholProcent0() {

        assertThrows(IllegalArgumentException.class, () -> {
            controller.createDestillering(

                    LocalDate.now(), LocalDate.now(), 0, true, "Fejl", 100, maltbatch);
        });
    }

    @Test
    void TC7_alkoholProcentMinus1() {

        assertThrows(IllegalArgumentException.class, () -> {
            controller.createDestillering(

                    LocalDate.now(), LocalDate.now(), -1, true, "Fejl", 100, maltbatch);
        });
    }

    @Test
    void TC8_vaeskeMaengde0() {

        assertThrows(IllegalArgumentException.class, () -> {
            controller.createDestillering(

                    LocalDate.now(), LocalDate.now(), 45, true, "Fejl", 0, maltbatch);
        });
    }

    @Test
    void TC9_vaeskeMaengdeMinus10() {

        assertThrows(IllegalArgumentException.class, () -> {
            controller.createDestillering(

                    LocalDate.now(), LocalDate.now(), 45, true, "Fejl", -10, maltbatch);
        });
    }

    @Test
    void TC10_startDatoNull() {

        assertThrows(IllegalArgumentException.class, () -> {
            controller.createDestillering(

                    null, LocalDate.now(), 45, true, "Fejl", 100, maltbatch);
        });
    }

    @Test
    void TC11_slutDatoNull() {

        assertThrows(IllegalArgumentException.class, () -> {
            controller.createDestillering(LocalDate.now(), null, 45, true, "Fejl", 100, maltbatch);
        });
    }

    @Test
    void TC12_maltbatchNull() {

        assertThrows(IllegalArgumentException.class, () -> {
            controller.createDestillering(LocalDate.now(), LocalDate.now(), 45, true, "Fejl", 100, null);
        });
    }
}
