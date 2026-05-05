package model;

import java.util.ArrayList;

public class Produkt {
    private int produktNr;
    private double vandMængde;
    private String vandOprindelse;
    private double alkoholProcent;
    private String beskrivelse;
    private KvalitetsStempel kvalitetsStempel; //Har en enum, med de forkslelige stempler
    // link attributter
    private ArrayList<WhiskySammensætning> whiskySammensætninger = new ArrayList<>();

    public Produkt(int produktNr, double vandMængde, String vandOprindelse, double alkoholProcent, String beskrivelse, KvalitetsStempel kvalitetsStempel) {
        this.produktNr = produktNr;
        this.vandMængde = vandMængde;
        this.vandOprindelse = vandOprindelse;
        this.alkoholProcent = alkoholProcent;
        this.beskrivelse = beskrivelse;
        this.kvalitetsStempel = kvalitetsStempel;
    }

    public ArrayList<WhiskySammensætning> getWhiskySammensætninger() {
        return new ArrayList<>(whiskySammensætninger);
    }

    void addWhiskySammensætning (WhiskySammensætning whiskySammensætning){
        if (!whiskySammensætninger.contains(whiskySammensætning)){
            whiskySammensætninger.add(whiskySammensætning);
        }
    }

    public WhiskySammensætning createWhiskySammensætning (double mængde, Fad fad){
        if (fad == null) {
            throw new IllegalArgumentException("Fad må ikke være null");
        }
        if (mængde <= 0 ) {
            throw new IllegalArgumentException("Mængde skal være større end 0");
        }
        if (!fad.harNokTilProdukt(mængde)) {
            throw new IllegalArgumentException("Der er ikke nok whisky i fadet");
        }
        return new WhiskySammensætning(mængde, this, fad);
    }

    public int getProduktNr() {
        return produktNr;
    }

    public double getVandMængde() {
        return vandMængde;
    }

    public String getVandOprindelse() {
        return vandOprindelse;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public KvalitetsStempel getKvalitetsStempel() {
        return kvalitetsStempel;
    }
}
