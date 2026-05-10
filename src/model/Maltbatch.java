package model;

public class Maltbatch {
    private int batchNr;
    private Kornsort kornsort;

    public Maltbatch(int batchNr, Kornsort kornsort) {
        if (batchNr <= 0 ){
            throw new IllegalArgumentException("BatchNr skal værre større end 0");
        }
        if (kornsort == null){
            throw new IllegalArgumentException("Du skal vælge en kornsort");
        }
        this.batchNr = batchNr;
        this.kornsort = kornsort;
    }

    public int getBatchNr() {
        return batchNr;
    }

    public Kornsort getKornsort() {
        return kornsort;
    }


}