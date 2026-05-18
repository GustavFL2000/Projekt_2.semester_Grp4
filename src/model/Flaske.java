package model;

public class Flaske {
    private int flaskeNr;
    private int størrelseMl;

    // link attribut
    private Produkt produkt; //Lavet af et produkt

    Flaske(int flaskeNr, int størrelse, Produkt produkt) {
        if (produkt == null) throw new IllegalArgumentException("Produkt skal vælges");
        if (størrelse <= 0) throw new IllegalArgumentException("Størrelse skal være større end 0");
        this.flaskeNr = flaskeNr;
        this.størrelseMl = størrelse;
        this.produkt = produkt;
    //todo Registreringsdato, antal flasker måske?
        produkt.addFlaske(this);
    }

    public int getFlaskeNr() {
        return flaskeNr;
    }

    public int getStørrelseMl() {
        return størrelseMl;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    @Override
    public String toString() {
        return "Flaske{" +
                "flaskeNr=" + flaskeNr +
                ", størrelse=" + størrelseMl +
                ", produkt=" + produkt +
                '}';
    }
}
