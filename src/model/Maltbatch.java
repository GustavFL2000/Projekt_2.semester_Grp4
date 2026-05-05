package model;

public class Maltbatch {
    private int batchNr;
    private Kornsort kornsort;

    public Maltbatch(int batchNr, Kornsort kornsort) {
        this.batchNr = batchNr;
        this.kornsort = kornsort;
    }

    public Kornsort getKornsort() {
        return kornsort;
    }
}