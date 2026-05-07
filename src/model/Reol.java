package model;

import java.util.ArrayList;

public class Reol {
    private int reolNr;
    //private ArrayList hylde??

    //TODO tilføj accosisationen med fad

    // composition: --> 1 lager
    Lager lager;

    public Reol(int reolNr, Lager lager) {
        this.reolNr = reolNr;
        this.lager = lager;
    }
}
