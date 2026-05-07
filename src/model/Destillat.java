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
    private ArrayList<WhiskySammensætning> whiskySammensætninger = new ArrayList<>();

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

    public Påfyldning createPåfyldning(double mængde, LocalDate dato, Fad fad) {
        if (mængde <= 0) {
            throw new IllegalArgumentException("Mængde skal være større end 0");
        }
        if (mængde > getRestMængde()) {
            throw new IllegalArgumentException("Der er ikke nok destillat tilbage");
        }
        if (fad == null) {
            throw new IllegalArgumentException("Fad må ikke være null");
        }
        if (!fad.harPladsTil(mængde)) {
            throw new IllegalArgumentException("Der er ikke nok plads i fadet");
        }
        if (dato == null){
            throw new IllegalArgumentException("Dato må ikke være null");
        }
        return new Påfyldning(mængde, dato,this, fad);
    }

    public double getPåfyldtMængde (){
        double sum = 0;
        for (Påfyldning påfyldning : påfyldninger) {
            sum += påfyldning.getMængde();
        }
        return sum;
    }

    public double getRestMængde () {
        return mængde - getPåfyldtMængde();
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

    // Returnerer den samlede mængde fra destillatet, der allerede er brugt i produkter
    public double getBrugtTilProdukter() {
        double sum = 0;
        for (WhiskySammensætning whiskySammensætning : whiskySammensætninger) {
            sum += whiskySammensætning.getMængdeFraDestillat();
        }
        return sum;
    }

    // Returnerer hvor mange liter der er tilbage af destillatet til produkter
    public double getTilgængeligMængdeTilProdukt() {
        return getPåfyldtMængde() - getBrugtTilProdukter();
    }

    // Returnerer true hvis destillatet har nok tilbage til den angivne mængde
    public boolean harNokTilProdukt(double mængde) {
        return mængde > 0 && mængde <= getTilgængeligMængdeTilProdukt();
    }

    @Override
    public String toString() {
        return destilatNavn;
    }
}
