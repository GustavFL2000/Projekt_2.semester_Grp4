package controller;

import model.*;
import storage.IStorage;

import java.time.LocalDate;
import java.util.List;


public class Controller {
    IStorage storage;

    public Controller(IStorage storage) {
        this.storage = storage;
    }

    public Destillering createDestillering(int destilleringsID,LocalDate startDato, LocalDate slutDato, double alkoholProcent, boolean rygematriale, String kommentar, double væskeMængde, Maltbatch maltbatch){
        Destillering destillering = new Destillering(destilleringsID,startDato,slutDato,alkoholProcent, rygematriale,kommentar,væskeMængde,maltbatch);
        storage.addDestillering(destillering);
        return destillering;
    }

    public List<Destillering> getDestilleringer(){
        return storage.getDestilleringer();
    }

    public Produkt createProdukt(int produktNr, double vandMængde, String vandOprindelse, double alkoholProcent, String beskrivelse, KvalitetsStempel kvalitetsStempel){
        Produkt produkt = new Produkt(produktNr,vandMængde,vandOprindelse,alkoholProcent,beskrivelse,kvalitetsStempel);
        storage.addProdukt(produkt);
        return produkt;
    }

    public Destillat createDestillat(String destilatNavn,LocalDate dato, double mængde, double alkoholProcent){
        Destillat destillat = new Destillat(destilatNavn, dato, mængde, alkoholProcent);

        return destillat;
    }

}
