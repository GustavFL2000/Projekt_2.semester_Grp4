package model;

public class Påfyldning {
    private double mængde;

    //link attributter
    private Destillat destillat;
    private Fad fad;

    public Påfyldning(double mængde, Destillat destillat, Fad fad) {
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
}
