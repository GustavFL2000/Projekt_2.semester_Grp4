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

    public Påfyldning createPåfyldning(double mængde, LocalDate dato, Fad fad, Destillat destillat) {
        Påfyldning p = destillat.createPåfyldning(mængde, dato, fad);
        return p;
    }

    public List<Destillering> getDestilleringer(){
        return storage.getDestilleringer();
    }

}
