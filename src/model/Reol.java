package model;

import java.util.ArrayList;
import java.util.List;

public class Reol {

    private int reolNr;

    // Association med Fad: 0..1 pr plads, max 9 fade
    private Fad[][] hylder = new Fad[3][3];

    // Composition --> 1 Lager
    private Lager lager;

    public Reol(int reolNr, Lager lager) {

        if (reolNr <= 0) {
            throw new IllegalArgumentException("Nummer skal være større end 0");
        }

        if (lager == null) {
            throw new IllegalArgumentException("Der skal være et lager valgt");
        }

        this.reolNr = reolNr;
        this.lager = lager;
    }

    public int getLedigePladser() {

        int count = 0;

        for (int h = 0; h < hylder.length; h++) {

            for (int p = 0; p < hylder[h].length; p++) {

                if (hylder[h][p] == null) {
                    count++;
                }
            }
        }

        return count;
    }

    public void placerFad(Fad fad, int hylde, int plads) {

        if (fad == null) {
            throw new IllegalArgumentException("Fad må ikke være null");
        }

        if (hylde < 0 || hylde >= hylder.length) {
            throw new IllegalArgumentException("Ugyldigt hyldenummer");
        }

        if (plads < 0 || plads >= hylder[hylde].length) {
            throw new IllegalArgumentException("Ugyldigt pladsnummer");
        }

        if (hylder[hylde][plads] != null) {
            throw new IllegalArgumentException("Pladsen er optaget");
        }

        // Fjern fadet fra gammel reol hvis det allerede er placeret
        Reol oldReol = fad.getReol();

        if (oldReol != null) {
            oldReol.fjernFad(fad);
        }

        // Placer fadet på ny plads
        hylder[hylde][plads] = fad;

        // Opdater dobbeltrettet association
        fad.setReol(this);
    }


    public void fjernFad(Fad fad) {

        if (fad == null) {
            throw new IllegalArgumentException("Fad må ikke være null");
        }

        for (int h = 0; h < hylder.length; h++) {

            for (int p = 0; p < hylder[h].length; p++) {

                if (hylder[h][p] == fad) {

                    hylder[h][p] = null;
                    fad.setReol(null);

                    return;
                }
            }
        }
    }

    public void flytFad(
            Fad fad,
            Reol nyReol,
            int nyHylde,
            int nyPlads) {

        if (fad == null) {
            throw new IllegalArgumentException("Fad må ikke være null");
        }

        if (nyReol == null) {
            throw new IllegalArgumentException("Ny reol må ikke være null");
        }

        // Placer på ny placering
        nyReol.placerFad(fad, nyHylde, nyPlads);
    }

    public Fad getFad(int hylde, int plads) {

        if (hylde < 0 || hylde >= hylder.length) {
            throw new IllegalArgumentException("Ugyldigt hyldenummer");
        }

        if (plads < 0 || plads >= hylder[hylde].length) {
            throw new IllegalArgumentException("Ugyldigt pladsnummer");
        }

        return hylder[hylde][plads];
    }

    public String getFadPlacering (Fad fad){
        for (int h = 0; h < hylder.length; h++) {

            for (int p = 0; p < hylder[h].length; p++) {

                if (hylder[h][p] == fad) {
                    return  "FadNr: " + fad.getFadNr()
                            + ", Lager: " + lager.getLagerNavn()
                            + ", Reol: " + reolNr
                            + ", Hylde: " + (h + 1)
                            + ", Plads: " + (p + 1);
                }
            }
        }
        return "Fadet står ikke på denne reol";
    }

    public List<Fad> getAlleFade() {

        List<Fad> fade = new ArrayList<>();

        for (int h = 0; h < hylder.length; h++) {

            for (int p = 0; p < hylder[h].length; p++) {

                if (hylder[h][p] != null) {
                    fade.add(hylder[h][p]);
                }
            }
        }

        return fade;
    }

    public int getReolNr() {
        return reolNr;
    }

    public Lager getLager() {
        return lager;
    }
}