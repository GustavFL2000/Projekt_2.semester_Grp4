package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private String destilatNavn;
    private LocalDate dato;
    private double mængde;
    private double alkoholProcent;
    // link attributter
    private List<Påfyldning> påfyldninger = new ArrayList<>();
    private List<WhiskySammensætning> whiskySammensætninger = new ArrayList<>();
    private List<DestilleringsMængde> destilleringsMængder = new ArrayList<>();

    public Destillat(String destilatNavn,LocalDate dato, double mængde, double alkoholProcent) {
        if (destilatNavn == null || destilatNavn.isBlank()){
            throw new IllegalArgumentException("Destillatnavn skal udfyldes");
        }
        if (dato == null){
            throw new IllegalArgumentException("Dato skal udfyldes");
        }
        if (mængde <= 0){
            throw new IllegalArgumentException("Mængde skal være større end 0");
        }
        if (alkoholProcent <= 0 || alkoholProcent > 100){
            throw new IllegalArgumentException("Alkoholprocent skal være mellem 0 og 100");
        }
        this.dato = dato;
        this.mængde = mængde;
        this.alkoholProcent = alkoholProcent;
        this.destilatNavn = destilatNavn;
    }

    // påfyldning metoder
    public List<Påfyldning> getPåfyldninger() {
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
        if (dato == null){
            throw new IllegalArgumentException("Dato skal udfyldes");
        }
        if (fad == null) {
            throw new IllegalArgumentException("Fad skal vælges");
        }
        if (mængde > getRestMængde()) {
            throw new IllegalArgumentException("Der er ikke nok destillat tilbage");
        }
        if (!fad.harPladsTil(mængde)) {
            throw new IllegalArgumentException("Der er ikke nok plads i fadet");
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
    public List<WhiskySammensætning> getWhiskySammensætninger() {
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
        return mængde - getBrugtTilProdukter();
    }

    // Returnerer true hvis destillatet har nok tilbage til den angivne mængde
    public boolean harNokTilProdukt(double mængde) {
        return mængde > 0 &&
                mængde <= getTilgængeligMængdeTilProdukt();
    }
    //Destilleringmængde metoder


    public List<DestilleringsMængde> getDestilleringsMængder() {
        return new ArrayList<>(destilleringsMængder);
    }

     void addDestilleringsMængde (DestilleringsMængde destilleringsMængde){
        if (!destilleringsMængder.contains(destilleringsMængde)){
            destilleringsMængder.add(destilleringsMængde);
        }
    }

    @Override
    public String toString() {
        return destilatNavn;
    }
}
