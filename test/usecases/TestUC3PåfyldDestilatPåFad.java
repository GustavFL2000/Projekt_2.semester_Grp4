package usecases;

import model.Destillat;
import model.Fad;
import model.Leverandør;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUC3PåfyldDestilatPåFad {
        private Leverandør leverandør;
        private Fad fad;
        private Destillat destillat;

        @BeforeEach
        void setUp() {

            leverandør = new Leverandør(
                    "BoFade",
                    "Italien",
                    "123");

            fad = new Fad(
                    1,
                    "Spanien",
                    100,
                    "Sherry",
                    leverandør);

            destillat = new Destillat(
                    "Test",
                    LocalDate.now(),
                    200,
                    70);
        }

    @Test
    void testCreatePåfyldning_TC13() {

        destillat.createPåfyldning(
                50,
                LocalDate.now(),
                fad);

        assertEquals(
                50,
                fad.getPåfyldtMængde());
    }

    @Test
    void testCreatePåfyldning_TC14() {

        destillat.createPåfyldning(
                100,
                LocalDate.now(),
                fad);

        assertEquals(100,
                fad.getPåfyldtMængde());
    }
    @Test
    void testCreatePåfyldning_TC15() {

        destillat.createPåfyldning(
                0.1,
                LocalDate.now(),
                fad);

        assertEquals(
                0.1,
                fad.getPåfyldtMængde());
    }

    @Test
    void testCreatePåfyldning_TC16() {

        Destillat lilleDestillat =
                new Destillat(
                        "Test",
                        LocalDate.now(),
                        100,
                        70);

        lilleDestillat.createPåfyldning(
                100,
                LocalDate.now(),
                fad);

        assertEquals(
                100,
                fad.getPåfyldtMængde());

        assertEquals(
                0,
                lilleDestillat.getRestMængde());
    }

    @Test
    void TC17_mængde0() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(
                            0,
                            LocalDate.now(),
                            fad);
                });
    }

    @Test
    void TC18_negativMængde() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(
                            -1,
                            LocalDate.now(),
                            fad);
                });
    }

    @Test
    void TC19_forStorMængde() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(
                            201,
                            LocalDate.now(),
                            fad);
                });
    }

    @Test
    void TC20_datoNull() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(
                            50,
                            null,
                            fad);
                });
    }

    @Test
    void TC21_fadNull() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(
                            50,
                            LocalDate.now(),
                            null);
                });
    }

    @Test
    void TC22_fadFuldt() {

        destillat.createPåfyldning(
                100,
                LocalDate.now(),
                fad);

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    destillat.createPåfyldning(
                            1,
                            LocalDate.now(),
                            fad);
                });
    }
    }
