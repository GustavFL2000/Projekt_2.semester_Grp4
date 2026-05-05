package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillering {
    int destilleringsID;
    LocalDate startDato;
    LocalDate slutDato;
    double væskeMængde;
    double alkoholProcent;
    boolean rygematriale;
    String kommentar;
    Maltbatch maltbatch;

    //Composition --> 0..* Påfyldning
    private List<Destillat> påfyldninger = new ArrayList<>();

    public Destillering(int destilleringsID, LocalDate startDato, LocalDate slutDato, double alkoholProcent, boolean rygematriale, String kommentar, double væskeMængde, Maltbatch maltbatch) {
        this.destilleringsID = destilleringsID;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.alkoholProcent = alkoholProcent;
        this.rygematriale = rygematriale;
        this.kommentar = kommentar;
        this.væskeMængde = væskeMængde;
        this.maltbatch = maltbatch;
    }

    public List<Destillat> getPåfyldninger() {
        return new ArrayList<>(påfyldninger);
    }

    public Destillat createDestillat(String destilatNavn, LocalDate dato, double mængde, double alkoholProcent){
        Destillat destillat = new Destillat(destilatNavn,dato,mængde,alkoholProcent,this);
        påfyldninger.add(destillat);
        return destillat;
    }

    public int getDestilleringsID() {
        return destilleringsID;
    }

    @Override
    public String toString() {
        return kommentar + " Nr: " + destilleringsID;
    }
}
