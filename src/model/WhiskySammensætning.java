package model;

public class WhiskySammensætning {
    private double mængdeFraFad;

    //link attributter
    private Produkt produkt;
    private Fad fad;

    WhiskySammensætning(double mængdeFraFad, Produkt produkt, Fad fad) {
        if (produkt == null) {
            throw new IllegalArgumentException("Produkt må ikke være null");
        }
        if (fad == null) {
            throw new IllegalArgumentException("Fad må ikke være null");
        }
        if (mængdeFraFad <= 0) {
            throw new IllegalArgumentException("Mængde skal være større end 0");
        }

        this.mængdeFraFad = mængdeFraFad;
        this.produkt = produkt;
        this.fad = fad;

        produkt.addWhiskySammensætning(this);
        fad.addWhiskySammensætning(this);
    }

    public double getMængdeFraFad() {
        return mængdeFraFad;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public Fad getFad() {
        return fad;
    }
}
