package model;

import java.util.ArrayList;

public class Fad {
    private int fadNr;
    private String land;
    private String type;
    private double størrelse;
    private String tidligereIndhold;
    private String leverandør;

    //linkattributter
    private ArrayList<Påfyldning> påfyldninger = new ArrayList<>();

    public Fad(int fadNr, String land, String type, double størrelse, String tidligereIndhold, String leverandør) {
        this.fadNr = fadNr;
        this.land = land;
        this.type = type;
        this.størrelse = størrelse;
        this.tidligereIndhold = tidligereIndhold;
        this.leverandør = leverandør;
    }

    public ArrayList<Påfyldning> getPåfyldninger() {
        return new ArrayList<>(påfyldninger);
    }

     void addPåfyldning (Påfyldning påfyldning) {
        if (!påfyldninger.contains(påfyldning) && påfyldning != null){
            påfyldninger.add(påfyldning);
        }
     }

    public double getSamletMængde () {
        double sum = 0;
        for (Påfyldning påfyldning : påfyldninger) {
            sum += påfyldning.getMængde();
        }
        return sum;
    }

    public double getRestKapacitet() {
        return størrelse - getSamletMængde();
    }

    public boolean harPladsTil(double mængde) {
        return mængde > 0 && mængde <= getRestKapacitet();
    }


    @Override
    public String toString() {
        return String.valueOf(this.fadNr);
    }
}
