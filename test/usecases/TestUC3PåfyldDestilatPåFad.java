package usecases;

import controller.Controller;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUC3PåfyldDestilatPåFad {
    private Leverandør leverandør;
    private Fad fad;
    private Destillat destillat;
    private Destillering destillering;
    private DestilleringsMængde destilleringsMængde;
    private Controller controller;
    private Maltbatch maltbatch;

    @BeforeEach
    void setUp() {
        IStorage storage = new Storage();
        controller = new Controller(storage);
        maltbatch = new Maltbatch(1, Kornsort.EVERGREEN);
        leverandør = new Leverandør("BoFade", "Italien", "123");

        fad = new Fad(1, "Spanien", 100, "Sherry", leverandør);

        destillat = new Destillat("Test", LocalDate.now(), 70);

        destillering = controller.createDestillering(LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 10), 45, true, "Test", 100, maltbatch);

        destilleringsMængde = new DestilleringsMængde(100, destillering, destillat);
    }

    @Test
    void testCreatePåfyldning_TC13() {

        destillat.createPåfyldning(50, LocalDate.now(), fad);

        assertEquals(50, fad.getPåfyldtMængde());
    }

    @Test
    void testCreatePåfyldning_TC14() {

        destillat.createPåfyldning(100, LocalDate.now(), fad);

        assertEquals(100, fad.getPåfyldtMængde());
    }

    @Test
    void testCreatePåfyldning_TC15() {

        destillat.createPåfyldning(0.1, LocalDate.now(), fad);

        assertEquals(0.1, fad.getPåfyldtMængde());
    }

    @Test
    void testCreatePåfyldning_TC16() {

        // Arrange
        Destillat lilleDestillat = new Destillat("Test", LocalDate.now(), 70);

        new DestilleringsMængde(100, destillering, lilleDestillat);

        // Act
        lilleDestillat.createPåfyldning(100, LocalDate.now(), fad);

        // Assert
        assertEquals(100, fad.getPåfyldtMængde());

        assertEquals(0, lilleDestillat.getRestMængde());
    }

    @Test
    void TC17_mængde0() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(0, LocalDate.now(), fad);
                });
    }

    @Test
    void TC18_negativMængde() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(-1, LocalDate.now(), fad);
                });
    }

    @Test
    void TC19_forStorMængde() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(201, LocalDate.now(), fad);
                });
    }

    @Test
    void TC20_datoNull() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(50, null, fad);
                });
    }

    @Test
    void TC21_fadNull() {

        assertThrows(IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(50, LocalDate.now(), null);
                });
    }

    @Test
    void TC22_fadFuldt() {

        destillat.createPåfyldning(100, LocalDate.now(), fad);

        assertThrows(IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(1, LocalDate.now(), fad);
                });
    }
}
