package model;

public class Flaske {
    private int flaskeNr;
    private int størrelse;

    // link attribut
    private Produkt produkt; //Lavet af et produkt

    Flaske(int flaskeNr, int størrelse, Produkt produkt) {
        if (produkt == null) throw new IllegalArgumentException("Produkt skal vælges");
        if (størrelse <= 0) throw new IllegalArgumentException("Størrelse skal være større end 0");
        this.flaskeNr = flaskeNr;
        this.størrelse = størrelse;
        this.produkt = produkt;

        produkt.addFlaske(this);
    }

    public int getFlaskeNr() {
        return flaskeNr;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    @Override
    public String toString() {
        return "Flaske{" +
                "flaskeNr=" + flaskeNr +
                ", størrelse=" + størrelse +
                ", produkt=" + produkt +
                '}';
    }
}
