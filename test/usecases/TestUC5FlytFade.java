package test.usecases;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUC5FlytFade {

    private Lager lager1;
    private Lager lager2;

    private Reol reol1;
    private Reol reol2;

    private Fad fad1;
    private Fad fad2;

    private Leverandør leverandør;

    @BeforeEach
    void setUp() {

        lager1 = new Lager(1,"Lager aarhus", "Aarhus" );

        lager2 = new Lager(1,"Lager Vejle", "Vejle");

        reol1 = new Reol(1, lager1);

        reol2 = new Reol(2, lager2);

        leverandør = new Leverandør("test Supplier", "Skotland", "hej");

        fad1 = new Fad(1, "Skotland", 100, "Sherry", leverandør);

        fad2 = new Fad(2, "Irland", 100, "Portvin", leverandør);

        // Startplacering
        reol1.placerFad(fad1, 0, 0);
    }

    // -------------------------------------------------
    // Gyldige testcases flytFad()
    // -------------------------------------------------

    @Test
    void TC53_flytFad1() {

        // Act
        reol1.flytFad(
                fad1,
                reol2,
                1,
                1);

        // Assert
        assertEquals(
                fad1,
                reol2.getFad(1,1));

        assertNull(
                reol1.getFad(0,0));
    }

    @Test
    void TC54_flytFad2() {

        // Act
        reol1.flytFad(
                fad1,
                reol2,
                2,
                2);

        // Assert
        assertEquals(
                fad1,
                reol2.getFad(2,2));

        assertNull(
                reol1.getFad(0,0));
    }

    @Test
    void TC55_flytFad3() {

        // Act
        reol1.flytFad(
                fad1,
                reol1,
                0,
                2);

        // Assert
        assertEquals(
                fad1,
                reol1.getFad(0,2));

        assertNull(
                reol1.getFad(0,0));
    }

    // -------------------------------------------------
    // Ugyldige testcases flytFad()
    // -------------------------------------------------

    @Test
    void TC56_nullFad() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    reol1.flytFad(
                            null,
                            reol2,
                            1,
                            1);
                });
    }

    @Test
    void TC57_nullReol() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    reol1.flytFad(
                            fad1,
                            null,
                            1,
                            1);
                });
    }

    @Test
    void TC58_negativHylde() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    reol1.flytFad(
                            fad1,
                            reol2,
                            -1,
                            1);
                });
    }

    @Test
    void TC59_hyldeUdenforArray() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    reol1.flytFad(
                            fad1,
                            reol2,
                            5,
                            1);
                });
    }

    @Test
    void TC60_negativPlads() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    reol1.flytFad(
                            fad1,
                            reol2,
                            1,
                            -1);
                });
    }

    @Test
    void TC61_pladsUdenforArray() {

        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    reol1.flytFad(
                            fad1,
                            reol2,
                            1,
                            5);
                });
    }

    @Test
    void TC62_pladsOptaget() {

        // Arrange
        reol2.placerFad(
                fad2,
                0,
                0);

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,

                () -> {
                    reol1.flytFad(
                            fad1,
                            reol2,
                            0,
                            0);
                });
    }
}