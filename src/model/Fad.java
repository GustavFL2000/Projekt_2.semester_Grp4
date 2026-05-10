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

    //TODO tilføj validering
    public Fad(int fadNr, String land, double størrelse, String tidligereIndhold, Leverandør leverandør) {
        this.fadNr = fadNr;
        this.land = land;
        this.træSort = "Egetræ";
        this.størrelse = størrelse;
        this.tidligereIndhold = tidligereIndhold;
        setLeverandør(leverandør);
    }

    //Påfyldning metoder
    public List<Påfyldning> getPåfyldninger() {
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

    // Returnerer true hvis den seneste påfyldning har lagret i mindst 3 år fra den angivne dato
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

    //Leverandør metoder
    public void setLeverandør (Leverandør leverandør){
        if (this.leverandør != leverandør){
            this.leverandør=leverandør;
            if (leverandør!=null){
                leverandør.addFad(this);
            }
        }
    }

    public Leverandør getLeverandør() {
        return leverandør;
    }

    @Override
    public String toString() {
        return String.valueOf(this.fadNr);
    }
}