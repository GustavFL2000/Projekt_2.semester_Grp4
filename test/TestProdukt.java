
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

class TestProdukt {

    private Produkt produkt;

    @BeforeEach
    void setUp() {
        // Arrange
        produkt = new Produkt(
                1,
                10,
                40,
                "Test produkt",
                KvalitetsStempel.SINGLE_MALT
        );
    }

    @Test
    void testCreateProduktValid() {
        // Assert
        assertEquals(1, produkt.getProduktNr());
        assertEquals(10, produkt.getVandMængde());
        assertEquals(40, produkt.getAlkoholProcent());
        assertEquals("Test produkt", produkt.getBeskrivelse());
    }

    @Test
    void testProduktNrTooLow() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Produkt(0, 10, 40, "Test",
                        KvalitetsStempel.SINGLE_MALT));

        // Assert
        assertEquals("Produktnummer skal være større end 0",
                exception.getMessage());
    }

    @Test
    void testNegativVandMængde() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Produkt(1, -1, 40, "Test",
                        KvalitetsStempel.SINGLE_MALT));

        // Assert
        assertEquals("Vandmængde må ikke være negativ",
                exception.getMessage());
    }

    @Test
    void testAlkoholProcentTooLow() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Produkt(1, 10, 0, "Test",
                        KvalitetsStempel.SINGLE_MALT));

        // Assert
        assertEquals("Alkoholprocent skal være større end 0 og mindre end 100",
                exception.getMessage());
    }

    @Test
    void testAlkoholProcentTooHigh() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Produkt(1, 10, 101, "Test",
                        KvalitetsStempel.SINGLE_MALT));

        // Assert
        assertEquals("Alkoholprocent skal være større end 0 og mindre end 100",
                exception.getMessage());
    }

    @Test
    void testNullBeskrivelse() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Produkt(1, 10, 40, null,
                        KvalitetsStempel.SINGLE_MALT));

        // Assert
        assertEquals("Beskrivelse må ikke være null",
                exception.getMessage());
    }

    @Test
    void testNullKvalitetsStempel() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Produkt(1, 10, 40, "Test", null));

        // Assert
        assertEquals("Kvalitetsstempel må ikke være null",
                exception.getMessage());
    }

    @Test
    void testCreateFlaskeValid() {
        // Arrange
        Destillat destillat = mock(Destillat.class);

        when(destillat.harNokTilProdukt(anyDouble(), any(LocalDate.class)))
                .thenReturn(true);

        produkt.createWhiskySammensætning(5, destillat);

        // Act
        Flaske flaske = produkt.createFlaske(700);

        // Assert
        assertNotNull(flaske);
        assertEquals(1, flaske.getFlaskeNr());
        assertEquals(700, flaske.getStørrelseMl());
        assertEquals(produkt, flaske.getProdukt());
    }

    @Test
    void testCreateFlaskeInvalidSize() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                produkt.createFlaske(0));

        // Assert
        assertEquals("Størrelsen på flasken skal værre større end 0",
                exception.getMessage());
    }

    @Test
    void testCreateFlaskeNotEnoughWhisky() {
        // Arrange
        Produkt tomtProdukt = new Produkt(
                1,
                0,
                40,
                "Test produkt",
                KvalitetsStempel.SINGLE_MALT
        );

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                tomtProdukt.createFlaske(700));

        // Assert
        assertEquals("Der er ikke nok whisky",
                exception.getMessage());
    }

    @Test
    void testCreateWhiskySammensætningValid() {
        // Arrange
        Destillat destillat = mock(Destillat.class);

        when(destillat.harNokTilProdukt(anyDouble(), any(LocalDate.class)))
                .thenReturn(true);

        // Act
        WhiskySammensætning ws =
                produkt.createWhiskySammensætning(5, destillat);

        // Assert
        assertNotNull(ws);
        assertEquals(5, ws.getMængdeFraDestillat());
    }

    @Test
    void testCreateWhiskySammensætningNullDestillat() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                produkt.createWhiskySammensætning(5, null));

        // Assert
        assertEquals("Destillat må ikke være null",
                exception.getMessage());
    }

    @Test
    void testCreateWhiskySammensætningInvalidMængde() {
        // Arrange
        Destillat destillat = mock(Destillat.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                produkt.createWhiskySammensætning(0, destillat));

        // Assert
        assertEquals("Mængde skal være større end 0",
                exception.getMessage());
    }

    @Test
    void testCreateWhiskySammensætningNotEnoughWhisky() {
        // Arrange
        Destillat destillat = mock(Destillat.class);

        when(destillat.harNokTilProdukt(anyDouble(), any(LocalDate.class)))
                .thenReturn(false);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                produkt.createWhiskySammensætning(5, destillat));

        // Assert
        assertEquals("Der er ikke nok whisky i fadet",
                exception.getMessage());
    }

    @Test
    void testGetWhiskySammensætninger() {
        // Arrange
        Destillat destillat = mock(Destillat.class);

        when(destillat.harNokTilProdukt(anyDouble(), any(LocalDate.class)))
                .thenReturn(true);

        produkt.createWhiskySammensætning(5, destillat);
        produkt.createWhiskySammensætning(3, destillat);

        // Act
        double resultat = produkt.getWhiskySammensætniger();

        // Assert
        assertEquals(8, resultat);
    }

    @Test
    void testGetBrugtTilFlasker() {
        // Arrange
        Destillat destillat = mock(Destillat.class);

        when(destillat.harNokTilProdukt(anyDouble(), any(LocalDate.class)))
                .thenReturn(true);

        produkt.createWhiskySammensætning(5, destillat);
        produkt.createFlaske(700);
        produkt.createFlaske(300);

        // Act
        double resultat = produkt.getBrugtTilFlasker();

        // Assert
        assertEquals(1.0, resultat);
    }

    @Test
    void testGetRestMængde() {
        // Arrange
        Destillat destillat = mock(Destillat.class);

        when(destillat.harNokTilProdukt(anyDouble(), any(LocalDate.class)))
                .thenReturn(true);

        produkt.createWhiskySammensætning(5, destillat);
        produkt.createFlaske(1000);

        // Act
        double resultat = produkt.getRestMængde();

        // Assert
        assertEquals(14, resultat);
    }

    @Test
    void testToString() {
        // Act
        String result = produkt.toString();

        // Assert
        assertTrue(result.contains("Produkt"));
    }

    @Test
    void testGetVandOprindelse() {

        // Act
        String resultat = produkt.getVandOprindelse();

        // Assert
        assertEquals("Begravet dal under destilleriet", resultat);
    }

    @Test
    void testGetFlasker() {

        // Arrange
        Destillat destillat = mock(Destillat.class);

        when(destillat.harNokTilProdukt(anyDouble(), any(LocalDate.class)))
                .thenReturn(true);

        produkt.createWhiskySammensætning(5, destillat);

        produkt.createFlaske(700);

        // Act
        int antalFlasker = produkt.getFlasker().size();

        // Assert
        assertEquals(1, antalFlasker);
    }

    @Test
    void testGetWhiskySammensætningerList() {

        // Arrange
        Destillat destillat = mock(Destillat.class);

        when(destillat.harNokTilProdukt(anyDouble(), any(LocalDate.class)))
                .thenReturn(true);

        produkt.createWhiskySammensætning(5, destillat);

        // Act
        int antal = produkt.getWhiskySammensætninger().size();

        // Assert
        assertEquals(1, antal);
    }

    @Test
    void testAddFlaskeAlreadyExists() {
        // Arrange
        Produkt produkt = new Produkt(1, 1.0, 40.0, "Test", KvalitetsStempel.BLENDED);

        Flaske flaske = produkt.createFlaske(500);

        int antalFør = produkt.getFlasker().size();

        // Act
        produkt.addFlaske(flaske);

        // Assert
        assertEquals(antalFør, produkt.getFlasker().size());
    }

    @Test
    void testAddWhiskySammensætningAlreadyExists() {
        // Arrange
        Produkt produkt = new Produkt(1, 1.0, 40.0, "Test", KvalitetsStempel.BLENDED);

        Destillat destillat = mock(Destillat.class);
        when(destillat.harNokTilProdukt(anyDouble(), any())).thenReturn(true);

        WhiskySammensætning ws =
                produkt.createWhiskySammensætning(0.5, destillat);

        int antalFør = produkt.getWhiskySammensætninger().size();

        // Act
        produkt.addWhiskySammensætning(ws);

        // Assert
        assertEquals(antalFør, produkt.getWhiskySammensætninger().size());
    }
}