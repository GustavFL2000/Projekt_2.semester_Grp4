package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fad {

    private int fadNr;
    private String land;
    private String træSort;
    private double størrelse;
    private String tidligereIndhold;

    //linkattributter
    private List<Påfyldning> påfyldninger = new ArrayList<>();
    private Leverandør leverandør;
    private Reol reol;

    public Fad(int fadNr, String land, double størrelse,
               String tidligereIndhold, Leverandør leverandør) {

        this.fadNr = fadNr;
        this.land = land;
        this.træSort = "Egetræ";
        this.størrelse = størrelse;
        this.tidligereIndhold = tidligereIndhold;

        setLeverandør(leverandør);
    }

    public List<Påfyldning> getPåfyldninger() {
        return new ArrayList<>(påfyldninger);
    }

    void addPåfyldning(Påfyldning påfyldning) {
        if (påfyldning != null && !påfyldninger.contains(påfyldning)) {
            påfyldninger.add(påfyldning);
        }
    }

    public double getPåfyldtMængde() {
        double sum = 0;

        for (Påfyldning påfyldning : påfyldninger) {
            sum += påfyldning.getMængde();
        }

        return sum;
    }

    public double getLedigKapacitet() {
        return størrelse - getPåfyldtMængde();
    }

    public boolean harPladsTil(double mængde) {
        return mængde > 0 && mængde <= getLedigKapacitet();
    }

    public boolean erKlarTilAftapning(LocalDate dagsDato) {

        if (dagsDato == null) {
            throw new IllegalArgumentException("Dato må ikke være null");
        }

        if (påfyldninger.isEmpty()) {
            throw new IllegalArgumentException("Ingen påfyldninger");
        }

        LocalDate sidsteDato = påfyldninger.getLast().getDato();

        return !sidsteDato.isAfter(dagsDato.minusYears(3));
    }

    public void setLeverandør(Leverandør leverandør) {

        if (this.leverandør != leverandør) {
            this.leverandør = leverandør;

            if (leverandør != null) {
                leverandør.addFad(this);
            }
        }
    }

    public Leverandør getLeverandør() {
        return leverandør;
    }

    public void setReol(Reol reol) {
        this.reol = reol;
    }

    public Reol getReol() {
        return reol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.fadNr);
    }
}