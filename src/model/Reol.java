package model;

import java.util.ArrayList;
import java.util.List;

public class Reol {
    private int reolNr;
    private Fad[] hylde1 = new Fad[3];
    private Fad[] hylde2 = new Fad[3];
    private Fad[] hylde3 = new Fad[3];

    //tilføj accosisationen med fad
    private List<Fad> fade = new ArrayList<>();

    // composition: --> 1 lager
    Lager lager;

    public Reol(int reolNr, Lager lager) {
        if (reolNr <= 0)
            throw new IllegalArgumentException("Nummer skal være større end 0");
        if (lager == null) {
            throw new IllegalArgumentException("Der skal være et lager valgt");
        }
        this.reolNr = reolNr;
        this.lager = lager;

    }
}
