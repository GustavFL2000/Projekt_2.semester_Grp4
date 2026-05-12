package model;

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

        // Fjern gammel relation
        Reol oldReol = fad.getReol();

        if (oldReol != null) {

            for (int h = 0; h < oldReol.hylder.length; h++) {

                for (int p = 0; p < oldReol.hylder[h].length; p++) {

                    if (oldReol.hylder[h][p] == fad) {
                        oldReol.hylder[h][p] = null;
                    }
                }
            }
        }

        hylder[hylde][plads] = fad;

        fad.setReol(this);
    }

    public void fjernFad(int hylde, int plads) {

        if (hylde < 0 || hylde >= hylder.length) {
            throw new IllegalArgumentException("Ugyldigt hyldenummer");
        }

        if (plads < 0 || plads >= hylder[hylde].length) {
            throw new IllegalArgumentException("Ugyldigt pladsnummer");
        }

        Fad fad = hylder[hylde][plads];

        if (fad != null) {
            fad.setReol(null);
        }

        hylder[hylde][plads] = null;
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

    public int getReolNr() {
        return reolNr;
    }

    public Lager getLager() {
        return lager;
    }
}