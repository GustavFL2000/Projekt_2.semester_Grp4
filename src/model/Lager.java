package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lager {
    private int lagerNr;
    private String lagerNavn;
    private String adresse;
    private LocalDate oprettelsesDato;
    private int ledigePladser;

    //Komposition --> 0..* Reol
    private List<Reol> reoler = new ArrayList<>();

    public Lager(int lagerNr ,String lagerNavn, String adresse) {
        if (lagerNavn == null){
            throw new IllegalArgumentException("Lager navn skal udfyldes");
        }
        if (adresse == null){
            throw new IllegalArgumentException("Adressen skal udfyldes");
        }
        this.lagerNavn = lagerNavn;
        this.adresse = adresse;
        this.lagerNr = lagerNr;
    }

    public List<Reol> getReoler() {
        return new ArrayList<>(reoler);
    }

    public Reol createReol (int reolNr){
        Reol reol = new Reol(reolNr, this);
        reoler.add(reol);
        return reol;
    }

    public int getLedigePladser(){
        for (Reol reol : reoler) {
           ledigePladser += reol.getLedigePladser();
        }
        return ledigePladser;
    }

    public String getLagerNavn() {
        return lagerNavn;
    }

    public String getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return "Lager{" +
                "lagerNavn='" + lagerNavn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", reoler=" + reoler +
                '}';
    }
}
