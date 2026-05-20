package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lager {
    private int lagerNr;
    private String lagerNavn;
    private String adresse;
    private LocalDate oprettelsesDato;

    //Komposition --> 0..* Reol
    private List<Reol> reoler = new ArrayList<>();

    public Lager(int lagerNr ,String lagerNavn, String adresse) {
        if (lagerNr <= 0){
            throw new IllegalArgumentException("Lager nr skal være større end 0");
        }
        if (lagerNavn == null){
            throw new IllegalArgumentException("Lager navn skal udfyldes");
        }
        if (adresse == null){
            throw new IllegalArgumentException("Adressen skal udfyldes");
        }
        this.lagerNavn = lagerNavn;
        this.adresse = adresse;
        this.lagerNr = lagerNr;
        this.oprettelsesDato = LocalDate.now();
    }

     void addReol(Reol reol){
        if (!reoler.contains(reol)){
            reoler.add(reol);
        }
    }

    public List<Reol> getReoler() {
        return new ArrayList<>(reoler);
    }

    public Reol createReol (){
        int reolNr = reoler.size() + 1;
        Reol reol = new Reol(reolNr, this);
        return reol;
    }

    public List<Reol> createReoler (int antalReoler){
        if (antalReoler <= 0){
            throw new IllegalArgumentException("Antal reoler skal være større end 0");
        }
        List<Reol> oprettedeReoler = new ArrayList<>();
        for (int i = 0; i < antalReoler; i++) {
            int reolNr = reoler.size() + 1;
            Reol reol = new Reol(reolNr, this);
            oprettedeReoler.add(reol);
        }
        return oprettedeReoler;
    }

    public int getLedigePladser() {

        int ledigePladser = 0;

        for (Reol reol : reoler) {
            ledigePladser += reol.getLedigePladser();
        }

        return ledigePladser;
    }

    public String getLagerNavn() {
        return lagerNavn;
    }

    public int getLagerNr() {
        return lagerNr;
    }

    @Override
    public String toString() {
        return "LagerNr " + lagerNr + " - " + lagerNavn + " (" + reoler.size() + " reoler)";
    }
}
