package model;

public class Påfyldning {
    private double mængde;

    //link attributter
    private Destillat destillat;
    private Fad fad;

    Påfyldning(double mængde, Destillat destillat, Fad fad) {
        if (destillat == null) {
            throw new IllegalArgumentException("Destillat må ikke være null");
        }
        if (fad == null) {
            throw new IllegalArgumentException("Fad må ikke være null");
        }
        if (mængde <= 0) {
            throw new IllegalArgumentException("Mængde skal være større end 0");
        }
        this.mængde = mængde;
        this.destillat = destillat;
        this.fad = fad;

        destillat.addPåfyldning(this);
        fad.addPåfyldning(this);
    }

    @Override
    public String toString() {
        return "Påfyldning{" +
                "mængde=" + mængde +
                ", destillat=" + destillat +
                ", fad=" + fad +
                '}';
    }

    public double getMængde() {
        return mængde;
    }

    public Destillat getDestillat() {
        return destillat;
    }

    public Fad getFad() {
        return fad;
    }
}
