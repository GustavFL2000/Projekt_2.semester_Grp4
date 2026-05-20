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

    public Produkt(int produktNr, double vandMængde, double alkoholProcent, String beskrivelse, KvalitetsStempel kvalitetsStempel) {
        if (produktNr <= 0) {
            throw new IllegalArgumentException("Produktnummer skal være større end 0");
        }

        if (vandMængde < 0) {
            throw new IllegalArgumentException("Vandmængde må ikke være negativ");
        }

        if (alkoholProcent <= 0 || alkoholProcent > 100) {
            throw new IllegalArgumentException("Alkoholprocent skal være større end 0 og mindre end 100");
        }

        if (beskrivelse == null) {
            throw new IllegalArgumentException("Beskrivelse må ikke være null");
        }

        if (kvalitetsStempel == null) {
            throw new IllegalArgumentException("Kvalitetsstempel skal vælges");
        }
        this.produktNr = produktNr;
        this.vandMængde = vandMængde;
        this.vandOprindelse = "Begravet dal under destilleriet";
        this.alkoholProcent = alkoholProcent;
        this.beskrivelse = beskrivelse;
        this.kvalitetsStempel = kvalitetsStempel;
    }

    //Whiskysammensætning metoder
    public List<WhiskySammensætning> getWhiskySammensætninger() {
        return new ArrayList<>(whiskySammensætninger);
    }

    void addWhiskySammensætning(WhiskySammensætning whiskySammensætning) {
        if (!whiskySammensætninger.contains(whiskySammensætning)) {
            whiskySammensætninger.add(whiskySammensætning);
        }
    }


    public WhiskySammensætning createWhiskySammensætning(double mængdeFraDestillat, Destillat destillat) {
        if (destillat == null) {
            throw new IllegalArgumentException("Destillat må ikke være tom");
        }
        if (mængdeFraDestillat <= 0) {
            throw new IllegalArgumentException("Mængde skal være større end 0");
        }
        if (!destillat.harNokTilProdukt(mængdeFraDestillat, LocalDate.now())) {
            throw new IllegalArgumentException("Der er ikke nok tilgængeligt destillat");
        }

        return new WhiskySammensætning(mængdeFraDestillat, this, destillat);
    }

    // Beregner samlet mængde whisky fra alle destillater (liter)
    public double getWhiskySammensætniger() {
        double sum = 0;

        for (WhiskySammensætning whiskySammensætning : whiskySammensætninger) {
            sum += whiskySammensætning.getMængdeFraDestillat();
        }
        return sum;
    }

    // Beregner hvor meget der er brugt til flasker (ml til liter)
    public double getBrugtTilFlasker() {
        double sum = 0;

        for (Flaske flaske : flasker) {
            sum += flaske.getStørrelseMl() / 1000.0;
        }
        return sum;
    }

    // Beregner hvor meget whisky der er tilbage af produktet
    public double getRestMængde() {
        return getWhiskySammensætniger() - getBrugtTilFlasker() + vandMængde;
    }

    //Flaske metoder
    public List<Flaske> getFlasker() {
        return new ArrayList<>(flasker);
    }

    void addFlaske(Flaske flaske) {
        if (!flasker.contains(flaske)) {
            flasker.add(flaske);
        }
    }

    public Flaske createFlaske(int størrelse) {
        if (størrelse <= 0) {
            throw new IllegalArgumentException("Størrelsen på flasken skal værre større end 0");
        }
        if ((størrelse / 1000.0) > getRestMængde()) {
            throw new IllegalArgumentException("Der er ikke nok whisky");
        }
        int flaskeNr = flasker.size() + 1;
        return new Flaske(flaskeNr, størrelse, this);
    }

    public Flaske createFlaskeMedNr(int flaskeNr, int størrelse) {

        if (størrelse <= 0) {
            throw new IllegalArgumentException("Størrelsen skal være større end 0");
        }

        if ((størrelse / 1000.0) > getRestMængde()) {
            throw new IllegalArgumentException("Der er ikke nok whisky");
        }

        return new Flaske(flaskeNr, størrelse, this);
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
        return "Produkt " + produktNr + " | rest: " + getRestMængde() + " L";
    }
}
