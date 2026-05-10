package model;

import java.util.ArrayList;
import java.util.List;

public class Lager {
    private String lagerNavn;
    private String adresse;

    //Komposition --> 0..* Reol
    List<Reol> reoler = new ArrayList<>();

    public Lager(String lagerNavn, String adresse) {
        if (lagerNavn == null){
            throw new IllegalArgumentException("Lager navn skal udfyldes");
        }
        if (adresse == null){
            throw new IllegalArgumentException("Adressen skal udfyldes");
        }
        this.lagerNavn = lagerNavn;
        this.adresse = adresse;
    }

    public List<Reol> getReoler() {
        return new ArrayList<>(reoler);
    }

    public Reol createReol (int reolNr){
        Reol reol = new Reol(reolNr, this);
        reoler.add(reol);
        return reol;
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
