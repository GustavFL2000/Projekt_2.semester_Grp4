package usecases;

import controller.Controller;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import storage.IStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestUC9VisHistorik{

    private IStorage storage;
    private Controller controller;

    @BeforeEach
    void setUp() {
        storage = mock(IStorage.class);
        controller = new Controller(storage);
    }

    @org.junit.jupiter.api.Test
    void visHistorik_returnererHistorik() {

        // Arrange
        Produkt produkt = mock(Produkt.class);
        Flaske flaske = mock(Flaske.class);

        WhiskySammensætning ws = mock(WhiskySammensætning.class);
        Destillat destillat = mock(Destillat.class);
        Påfyldning påfyldning = mock(Påfyldning.class);
        Fad fad = mock(Fad.class);

        when(flaske.getFlaskeNr()).thenReturn(1);
        when(flaske.getProdukt()).thenReturn(produkt);

        when(produkt.getProduktNr()).thenReturn(10);
        when(produkt.getVandMængde()).thenReturn(5.0);
        when(produkt.getVandOprindelse()).thenReturn("Skotland");
        when(produkt.getAlkoholProcent()).thenReturn(50.0);
        when(produkt.getBeskrivelse()).thenReturn("Test whisky");

        List<Flaske> produktFlasker = new ArrayList<>();
        produktFlasker.add(flaske);

        when(produkt.getFlasker()).thenReturn(produktFlasker);

        when(produkt.getWhiskySammensætninger())
                .thenReturn(List.of(ws));

        when(ws.getDestillat()).thenReturn(destillat);
        when(ws.getMængdeFraDestillat()).thenReturn(5.0);

        when(destillat.getDestilatNavn()).thenReturn("Destillat A");
        when(destillat.getDato()).thenReturn(LocalDate.of(2020, 1, 1));
        when(destillat.getPåfyldninger())
                .thenReturn(List.of(påfyldning));

        when(påfyldning.getFad()).thenReturn(fad);
        when(påfyldning.getDato())
                .thenReturn(LocalDate.of(2021, 1, 1));

        when(fad.getFadNr()).thenReturn(99);
        when(fad.getTidligereIndhold()).thenReturn("Sherry");
        when(fad.getLand()).thenReturn("Spanien");

        when(storage.getFlasker()).thenReturn(List.of(flaske));

        // Act
        String resultat = controller.visHistorik(1);

        // Assert
        assertTrue(resultat.contains("Historik for flaske 1"));
        assertTrue(resultat.contains("Test whisky"));
        assertTrue(resultat.contains("Destillat A"));
        assertTrue(resultat.contains("Fad nr: 99"));
        assertTrue(resultat.contains("Sherry"));
        assertTrue(resultat.contains("Spanien"));
    }

    @org.junit.jupiter.api.Test
    void visHistorik_kasterExceptionHvisFlaskeIkkeFindes() {

        // Arrange
        when(storage.getFlasker()).thenReturn(new ArrayList<>());

        // Act + Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> controller.visHistorik(1));

        assertEquals("Flaske findes ikke", exception.getMessage());
    }
}