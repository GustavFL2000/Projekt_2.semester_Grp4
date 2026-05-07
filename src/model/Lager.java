package model;

import java.util.ArrayList;
import java.util.List;

public class Lager {
    private String lagerNavn;
    private String adresse;

    //Komposition --> 0..* Reol
    List<Reol> reoler = new ArrayList<>();

    public List<Reol> getReoler() {
        return new ArrayList<>(reoler);
    }

    public Reol createReol (int reolNr){
        Reol reol = new Reol(reolNr, this);
        reoler.add(reol);
        return reol;
    }
}
