package model;

import java.util.ArrayList;
import java.util.List;

public class Leverandør {

    private String navn;
    private String land;
    private String kontaktInfo;

    //Fad attributter
    private List<Fad> fade = new ArrayList<>();

    public Leverandør(String navn, String land, String kontaktInfo) {
        if (navn == null){
            throw new IllegalArgumentException("Du skal indtaste et navn");
        }
        if (land == null){
            throw new IllegalArgumentException("Du skal indtaste et land");
        }
        if (kontaktInfo == null){
            throw new IllegalArgumentException("Du skal indtaste kontakt oplysninger");
        }
        this.navn = navn;
        this.land = land;
        this.kontaktInfo = kontaktInfo;
    }

    public List<Fad> getFade() {
        return new ArrayList<>(fade);
    }

    public void addFad (Fad fad) {
        if (!fade.contains(fad)) {
            fade.add(fad);
            fad.setLeverandør(this);
        }
    }

    @Override
    public String toString() {
        return navn + ", tlf: " + kontaktInfo;
    }
}
