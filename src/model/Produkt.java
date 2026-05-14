package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Produkt {
    private int produktNr;
    private double vandMængde;
    private String vandOprindelse;
    private double alkoholProcent;
    private String beskrivelse;
    private KvalitetsStempel kvalitetsStempel; //Har en enum, med de forkslelige stempler
    // link attributter
    private List<WhiskySammensætning> whiskySammensætninger = new ArrayList<>();
    private List<Flaske> flasker = new ArrayList<>();

    public Produkt(int produktNr, double vandMængde, String vandOprindelse, double alkoholProcent, String beskrivelse, KvalitetsStempel kvalitetsStempel) {
        if (produktNr <= 0) {
            throw new IllegalArgumentException(
                    "Produktnummer skal være større end 0");
        }

        if (vandMængde < 0) {
            throw new IllegalArgumentException(
                    "Vandmængde må ikke være negativ");
        }

        if (vandOprindelse == null) {
            throw new IllegalArgumentException(
                    "Vandoprindelse må ikke være null");
        }

        if (alkoholProcent <= 0 || alkoholProcent > 100) {
            throw new IllegalArgumentException(
                    "Alkoholprocent skal være større end 0 og mindre end 100");
        }

        if (beskrivelse == null) {
            throw new IllegalArgumentException(
                    "Beskrivelse må ikke være null");
        }

        if (kvalitetsStempel == null) {
            throw new IllegalArgumentException(
                    "Kvalitetsstempel må ikke være null");
        }
        this.produktNr = produktNr;
        this.vandMængde = vandMængde;
        this.vandOprindelse = vandOprindelse;
        this.alkoholProcent = alkoholProcent;
        this.beskrivelse = beskrivelse;
        this.kvalitetsStempel = kvalitetsStempel;
    }

    //Whiskysammensætning metoder
    public List<WhiskySammensætning> getWhiskySammensætninger() {
        return new ArrayList<>(whiskySammensætninger);
    }

    void addWhiskySammensætning (WhiskySammensætning whiskySammensætning){
        if (!whiskySammensætninger.contains(whiskySammensætning)){
            whiskySammensætninger.add(whiskySammensætning);
        }
    }


    public WhiskySammensætning createWhiskySammensætning (double mængdeFraDestillat, Destillat destillat){
        if (destillat == null) {
            throw new IllegalArgumentException("Destillat må ikke være null");
        }
        if (mængdeFraDestillat <= 0 ) {
            throw new IllegalArgumentException("Mængde skal være større end 0");
        }
        if (!destillat.harNokTilProdukt(mængdeFraDestillat,LocalDate.now())) {
            throw new IllegalArgumentException("Der er ikke nok whisky i fadet");
        }

        return new WhiskySammensætning(mængdeFraDestillat, this, destillat);
    }

    //Flaske metoder


    public List<Flaske> getFlasker() {
        return new ArrayList<>(flasker);
    }

    void addFlaske (Flaske flaske){
        if (!flasker.contains(flaske)){
            flasker.add(flaske);
        }
    }

    public Flaske createFlaske(int størrelse){
        if (størrelse <= 0) {
            throw new IllegalArgumentException("Størrelsen på flasken skal værre større end 0");
        }
        int flaskeNr = flasker.size() + 1;
        return new Flaske(flaskeNr, størrelse, this);
    }

    public List<Flaske> createFlasker(int størrelse, int antalFlasker){
        if (størrelse <= 0) {
            throw new IllegalArgumentException("Størrelsen på flasken skal værre større end 0");
        }
        if (antalFlasker <= 0) {
            throw new IllegalArgumentException("Antal flasker skal være større end 0");
        }
        List<Flaske> oprettedeFlasker = new ArrayList<>();
        for (int i = 0; i < antalFlasker; i++) {
            int flaskeNr = flasker.size() + 1;
            Flaske flaske =  new Flaske(flaskeNr, størrelse, this);
            oprettedeFlasker.add(flaske);
        }
        return oprettedeFlasker;
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

    @Override
    public String toString() {
        return "Produkt{" +
                "produktNr=" + produktNr +
                ", vandMængde=" + vandMængde +
                ", vandOprindelse='" + vandOprindelse + '\'' +
                ", alkoholProcent=" + alkoholProcent +
                ", beskrivelse='" + beskrivelse + '\'' +
                ", kvalitetsStempel=" + kvalitetsStempel +
                '}';
    }
}
