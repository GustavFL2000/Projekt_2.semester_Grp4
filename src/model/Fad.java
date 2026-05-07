package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fad {
    private int fadNr;
    private String land;
    private String træSort;
    private double størrelse;
    private String tidligereIndhold;
    private String leverandør;

    //linkattributter
    private ArrayList<Påfyldning> påfyldninger = new ArrayList<>();
    private ArrayList<WhiskySammensætning> whiskySammensætninger = new ArrayList<>();

    public Fad(int fadNr, String land, double størrelse, String tidligereIndhold, String leverandør) {
        this.fadNr = fadNr;
        this.land = land;
        this.træSort = "Egetræ";
        this.størrelse = størrelse;
        this.tidligereIndhold = tidligereIndhold;
        this.leverandør = leverandør;
    }

    //Påfyldning metoder
    public ArrayList<Påfyldning> getPåfyldninger() {
        return new ArrayList<>(påfyldninger);
    }

    void addPåfyldning(Påfyldning påfyldning) {
        if (påfyldning != null && !påfyldninger.contains(påfyldning)) {
            påfyldninger.add(påfyldning);
        }
    }

    // Returnerer den samlede mængde destillat der er påfyldt fadet
    public double getPåfyldtMængde() {
        double sum = 0;
        for (Påfyldning påfyldning : påfyldninger) {
            sum += påfyldning.getMængde();
        }
        return sum;
    }

    // Returnerer hvor mange liter der stadig er plads til i fadet
    public double getLedigKapacitet() {
        return størrelse - getPåfyldtMængde();
    }

    // Returnerer true hvis fadet har plads til den angivne mængde
    public boolean harPladsTil(double mængde) {
        return mængde > 0 && mængde <= getLedigKapacitet();
    }

    // Whiskysammensætning metoder
    public ArrayList<WhiskySammensætning> getWhiskySammensætninger() {
        return new ArrayList<>(whiskySammensætninger);
    }

    void addWhiskySammensætning(WhiskySammensætning whiskySammensætning) {
        if (!whiskySammensætninger.contains(whiskySammensætning)) {
            whiskySammensætninger.add(whiskySammensætning);
        }
    }

    // Returnerer den samlede mængde fra fadet, der allerede er brugt i produkter
    public double getBrugtTilProdukter() {
        double sum = 0;
        for (WhiskySammensætning whiskySammensætning : whiskySammensætninger) {
            sum += whiskySammensætning.getMængdeFraFad();
        }
        return sum;
    }

    // Returnerer hvor mange liter der er tilbage på fadet
    public double getTilgængeligMængdeTilProdukt() {
        return getPåfyldtMængde() - getBrugtTilProdukter();
    }

    // Returnerer true hvis fadet har nok indhold tilbage til den angivne mængde
    public boolean harNokTilProdukt(double mængde) {
        return mængde > 0 && mængde <= getTilgængeligMængdeTilProdukt();
    }

    // Returnerer true hvis den seneste påfyldning har lagret i mindst 3 år fra den angivne dato
    public boolean erKlarTilAftapning(LocalDate dagsDato) {
        if (dagsDato == null) {
            throw new RuntimeException("Dato må ikke være null");
        }
        if (påfyldninger.isEmpty()) {
            throw new RuntimeException("Ingen påfyldninger");
        }
            LocalDate sidsteDato = påfyldninger.getLast().getDato();

            return !sidsteDato.isAfter(dagsDato.minusYears(3));

    }


    @Override
    public String toString() {
        return String.valueOf(this.fadNr);
    }
}