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

    //Komposition --> 0..* Påfyldning
    private List<Destillat> påfyldninger = new ArrayList<>();

    public Destillering(int destilleringsID, LocalDate startDato, LocalDate slutDato, double alkoholProcent, boolean rygematriale, String kommentar, double væskeMængde, Maltbatch maltbatch) {

        if (destilleringsID <= 0)
            throw new IllegalArgumentException("ID skal være over 0");

        if (startDato == null)
            throw new IllegalArgumentException("Startdato må ikke være null");

        if (slutDato == null)
            throw new IllegalArgumentException("Slutdato må ikke være null");

        if (alkoholProcent <= 0)
            throw new IllegalArgumentException("Alkoholprocent skal være over 0");

        if (væskeMængde <= 0)
            throw new IllegalArgumentException("Væskemængde skal være over 0");

        if (maltbatch == null)
            throw new IllegalArgumentException("Maltbatch må ikke være null");

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
        if (dato == null) throw new IllegalArgumentException("Ugyldig dato");
        if (mængde <= 0) throw new IllegalArgumentException("Mængde skal være over 0");
        Destillat destillat = new Destillat(destilatNavn,dato,mængde,alkoholProcent,this);
        påfyldninger.add(destillat);
        return destillat;
    }

    public int getDestilleringsID() {
        return destilleringsID;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    @Override
    public String toString() {
        return kommentar + " Nr: " + destilleringsID;
    }
}
