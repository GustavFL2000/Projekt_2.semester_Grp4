package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillat {
    private String destilatNavn;
    private LocalDate dato;
    private double mængde;
    private double alkoholProcent;
    // link attributter
    private Destillering destillering;
    private ArrayList<Påfyldning> påfyldninger = new ArrayList<>();

    public Destillat(String destilatNavn,LocalDate dato, double mængde, double alkoholProcent, Destillering destillering) {
        this.dato = dato;
        this.mængde = mængde;
        this.alkoholProcent = alkoholProcent;
        this.destillering = destillering;
        this.destilatNavn = destilatNavn;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    // påfyldning metoder
    public ArrayList<Påfyldning> getPåfyldninger() {
        return new ArrayList<>(påfyldninger);
    }

     void addPåfyldning(Påfyldning påfyldning) {
        if (!påfyldninger.contains(påfyldning) && påfyldning != null) {
            påfyldninger.add(påfyldning);
        }
    }

    public Påfyldning createPåfyldning(double mængde, Fad fad) {
        return new Påfyldning(mængde, this, fad);
    }

    @Override
    public String toString() {
        return destilatNavn;
    }
}
