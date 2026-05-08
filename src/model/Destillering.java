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
            throw new IllegalArgumentException("Startdato skal udfyldes");
        if (slutDato == null)
            throw new IllegalArgumentException("Slutdato skal udfyldes");
        if (slutDato.isBefore(startDato))
            throw new IllegalArgumentException("Slutdato må ikke være før startdato");
        if (alkoholProcent <= 0 || alkoholProcent > 100)
            throw new IllegalArgumentException("Alkoholprocent skal være mellem 0 og 100");
        if (væskeMængde <= 0)
            throw new IllegalArgumentException("Væskemængde skal være større end 0");
        if (maltbatch == null)
            throw new IllegalArgumentException("Maltbatch skal vælges");

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
        if (mængde <= 0 ){
            throw new IllegalArgumentException("Mængde skal værre større end 0");
        }
        if (destillat == null){
            throw new IllegalArgumentException("Destillat skal vælges");
        }
        if (mængde > getRestMængde()){
            throw new IllegalArgumentException("Der er ikke nok væske tilbage fra destilleringen");
        }
        return new DestilleringsMængde(mængde, this, destillat);
    }

    public double getPådeltMængde (){
        double sum = 0;
        for (DestilleringsMængde destilleringsMængde : destilleringsMængder) {
            sum+=destilleringsMængde.getMængde();
        }
        return sum;
    }

    public double getRestMængde() {
        return væskeMængde-getPådeltMængde();
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
