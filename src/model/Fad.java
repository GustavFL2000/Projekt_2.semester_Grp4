package model;

import java.util.ArrayList;

public class Fad {
    private int fadNr;
    private String land;
    private String type;
    private double størrelse;
    private String tidligereIndhold;
    private String leverandør;
    private boolean fyldt;

    //linkattributter
    private ArrayList<Påfyldning> påfyldninger = new ArrayList<>();

    public Fad(int fadNr, String land, String type, double størrelse, String tidligereIndhold, String leverandør) {
        this.fadNr = fadNr;
        this.land = land;
        this.type = type;
        this.størrelse = størrelse;
        this.tidligereIndhold = tidligereIndhold;
        this.leverandør = leverandør;
        this.fyldt = false;
    }

    public ArrayList<Påfyldning> getPåfyldninger() {
        return new ArrayList<>(påfyldninger);
    }

     void addPåfyldning (Påfyldning påfyldning) {
        if (!påfyldninger.contains(påfyldning) && påfyldning != null){
            påfyldninger.add(påfyldning);
            fyldt = true;
        }
    }


    @Override
    public String toString() {
        return String.valueOf(this.fadNr);
    }
}
