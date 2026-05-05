package model;

import java.time.LocalDate;

public class Påfyldning {
    LocalDate dato;
    double mængde;
    double alkoholProcent;
    private Destillering destillering;

    public Påfyldning(LocalDate dato, double mængde, double alkoholProcent, Destillering destillering) {
        this.dato = dato;
        this.mængde = mængde;
        this.alkoholProcent = alkoholProcent;
        this.destillering = destillering;
    }

    public Destillering getDestillering(){
        return destillering;
    }

}
