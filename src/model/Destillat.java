package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private String destilatNavn;
    private LocalDate dato;
    private double alkoholProcent;
    // link attributter
    private List<Påfyldning> påfyldninger = new ArrayList<>();
    private List<WhiskySammensætning> whiskySammensætninger = new ArrayList<>();
    private List<DestilleringsMængde> destilleringsMængder = new ArrayList<>();

    public Destillat(String destilatNavn, LocalDate dato, double alkoholProcent) {
        if (destilatNavn == null || destilatNavn.isBlank()) {
            throw new IllegalArgumentException("Destillatnavn skal udfyldes");
        }
        if (dato == null) {
            throw new IllegalArgumentException("Dato skal udfyldes");
        }
        if (alkoholProcent <= 0 || alkoholProcent > 100) {
            throw new IllegalArgumentException("Alkoholprocent skal være mellem 0 og 100");
        }
        this.dato = dato;
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
        if (dato == null) {
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
        return new Påfyldning(mængde, dato, this, fad);
    }

    public double getPåfyldtMængde() {
        double sum = 0;
        for (Påfyldning påfyldning : påfyldninger) {
            sum += påfyldning.getMængde();
        }
        return sum;
    }

    public double getRestMængde() {
        return getDestilleringsmængder() - getPåfyldtMængde();
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

    // Returnerer hvor mange liter af den påfyldte destillatet
    // på fad der er tilbage af til produkter
    public double getTilgængeligMængdeTilProdukt() {
        return getPåfyldtMængde() - getBrugtTilProdukter();
    }


    //TODO slet

    // Returnerer true hvis destillatet har nok tilbage til den angivne mængde
//    public boolean harNokTilProdukt(double mængde) {
//        return mængde > 0 &&
//                mængde <= getTilgængeligMængdeTilProdukt();
//    }


//    public boolean erKlarTilProdukt(LocalDate dagsDato) {
//        for (Påfyldning påfyldning : påfyldninger) {
//            if (!påfyldning.getFad().erKlarTilAftapning(dagsDato)) {
//                return false;
//            }
//        }
//        return !påfyldninger.isEmpty();
//    }


    //Destilleringmængde metoder
    public List<DestilleringsMængde> getDestilleringsMængder() {
        return new ArrayList<>(destilleringsMængder);
    }

    void addDestilleringsMængde(DestilleringsMængde destilleringsMængde) {
        if (!destilleringsMængder.contains(destilleringsMængde)) {
            destilleringsMængder.add(destilleringsMængde);
        }
    }

    public double getDestilleringsmængder() {
        double sum = 0;
        for (DestilleringsMængde destilleringsMængde : destilleringsMængder) {
            sum += destilleringsMængde.getMængde();
        }
        return sum;
    }

    //    For hver påfyldning:
//    Hvis fadet er gammelt nok:
//    så må denne mængde bruges
    public double getKlarMængde(LocalDate dagsDato) {

        if (dagsDato == null) {
            throw new IllegalArgumentException("Dato må ikke være null");
        }

        double sum = 0;

        for (Påfyldning påfyldning : påfyldninger) {

            Fad fad = påfyldning.getFad();

            if (fad.erKlarTilAftapning(dagsDato)) {
                sum += påfyldning.getMængde();
            }
        }

        return sum;
    }

    public double getTilgængeligKlarMængde(LocalDate dagsDato) {
        return getKlarMængde(dagsDato) - getBrugtTilProdukter();
    }

    public boolean harNokTilProdukt(double mængde, LocalDate dagsDato) {
        if (mængde <= 0) {
            return false;
        }
        return mængde <= getTilgængeligKlarMængde(dagsDato);
    }

    @Override
    public String toString() {
        return destilatNavn +
                " | Rest: " + getRestMængde() +
                " L | Klar: " +
                getTilgængeligKlarMængde(LocalDate.now()) +
                " L";
    }
}
