package model;

import java.time.LocalDate;

public class Destillering {
    int destilleringsID;
    LocalDate startDato;
    LocalDate slutDato;
    double væskeMængde;
    double alkoholProcent;
    boolean rygematriale;
    String kommentar;
    Maltbatch maltbatch;

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

    public int getDestilleringsID() {
        return destilleringsID;
    }

    @Override
    public String toString() {
        return kommentar + " Nr: " + destilleringsID;
    }
}
