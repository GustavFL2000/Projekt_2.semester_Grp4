package model;

public class WhiskySammensætning {
    private double mængdeFraDestillat;

    //link attributter
    private Produkt produkt;
    private Destillat destillat;

    WhiskySammensætning(double mængdeFraDestillat, Produkt produkt, Destillat destillat) {
        if (produkt == null) {
            throw new IllegalArgumentException("Produkt må ikke være null");
        }
        if (destillat == null) {
            throw new IllegalArgumentException("Destillat må ikke være null");
        }
        if (mængdeFraDestillat <= 0) {
            throw new IllegalArgumentException("Mængde skal være større end 0");
        }

        this.mængdeFraDestillat = mængdeFraDestillat;
        this.produkt = produkt;
        this.destillat = destillat;

        produkt.addWhiskySammensætning(this);
        destillat.addWhiskySammensætning(this);
    }

    public double getMængdeFraDestillat() {
        return mængdeFraDestillat;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public Destillat getDestillat() {
        return destillat;
    }
}
