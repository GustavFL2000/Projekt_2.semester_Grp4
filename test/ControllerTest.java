package test;

import controller.Controller;
import model.Destillering;
import model.Kornsort;
import model.Maltbatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    void setUp() {
        IStorage storage = new Storage();
        controller = new Controller(storage);
    }

    @Test
    void testCreateDestillering() {
        Maltbatch maltbatch = new Maltbatch(1, Kornsort.EVERGREEN);

        Destillering d = controller.createDestillering(
                1,
                LocalDate.of(2026, 5, 1),
                LocalDate.of(2026, 5, 10),
                45.0,
                true,
                "Test destillering",
                100.0,
                maltbatch
        );

        assertNotNull(d);
        assertEquals(1, d.getDestilleringsID());
    }

    @Test
    void testStorageGetsUpdated() {
        Maltbatch maltbatch = new Maltbatch(1, Kornsort.IRINA);

        controller.createDestillering(
                2,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 5),
                50.0,
                false,
                "Ny",
                200.0,
                maltbatch
        );

        List<Destillering> list = controller.getDestilleringer();

        assertEquals(1, list.size());
    }

    @Test
    void testMultipleDestilleringer() {
        Maltbatch maltbatch = new Maltbatch(1, Kornsort.STAIRWAY);

        controller.createDestillering(1, LocalDate.now(), LocalDate.now(), 40, true, "A", 100, maltbatch);
        controller.createDestillering(2, LocalDate.now(), LocalDate.now(), 42, false, "B", 200, maltbatch);

        List<Destillering> list = controller.getDestilleringer();

        assertEquals(2, list.size());
    }
}