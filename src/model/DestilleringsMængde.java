package model;

public class DestilleringsMængde {
    private double mængde;

    //link attributter
    private Destillering destillering;
    private Destillat destillat;

     DestilleringsMængde(double mængde, Destillering destillering, Destillat destillat) {
         if (destillering == null) {
             throw new IllegalArgumentException("Destillering må ikke være null");
         }
         if (destillat == null) {
             throw new IllegalArgumentException("Destillat må ikke være null");
         }
         if (mængde <= 0) {
             throw new IllegalArgumentException("Mængde skal være større end 0");
         }

        this.mængde = mængde;
        this.destillering = destillering;
        this.destillat = destillat;

        destillering.addDestilleringsMængde(this);
        destillat.addDestilleringsMængde(this);
    }

    public double getMængde() {
        return mængde;
    }

    @Override
    public String toString() {
        return "DestilleringsMængde{" +
                "mængde=" + mængde +
                ", destillering=" + destillering +
                ", destillat=" + destillat +
                '}';
    }
}
