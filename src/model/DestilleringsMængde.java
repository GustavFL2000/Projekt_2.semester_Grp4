package model;

public class DestilleringsMængde {
    private double mængde;

    //link attributter
    private Destillering destillering;
    private Destillat destillat;

     DestilleringsMængde(double mængde, Destillering destillering, Destillat destillat) {
        //TODO tilføj exceptions

        this.mængde = mængde;
        this.destillering = destillering;
        this.destillat = destillat;

        destillering.addDestilleringsMængde(this);
        destillat.addDestilleringsMængde(this);
    }
}
