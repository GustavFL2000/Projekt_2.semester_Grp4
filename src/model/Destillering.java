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

    //Komposition --> 0..* DestillingsMængde
    private List<DestilleringsMængde> destilleringsMængder = new ArrayList<>();

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

    //DestilleringsMængde metoder
    public List<DestilleringsMængde> getDestilleringsMængder() {
        return new ArrayList<>(destilleringsMængder);
    }

     void addDestilleringsMængde (DestilleringsMængde destilleringsMængde){
        if (!destilleringsMængder.contains(destilleringsMængde)) {
            destilleringsMængder.add(destilleringsMængde);
        }
    }

    public DestilleringsMængde createDestilleringsMængde (double mængde, Destillat destillat) {
        //todo tilføj exceptions

        return new DestilleringsMængde(mængde, this, destillat);
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
