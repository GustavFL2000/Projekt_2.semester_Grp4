package storage;

import model.Destillering;
import model.Fad;
import model.Produkt;
import model.Påfyldning;

import java.util.ArrayList;
import java.util.List;

public class Storage implements IStorage{
    private List<Destillering> destilleringer = new ArrayList<>();
    private List<Produkt> produkter = new ArrayList<>();
    private List<Fad> fade = new ArrayList<>();

    @Override
    public void addDestillering(Destillering destillering){
        if(!destilleringer.contains(destillering)){
            destilleringer.add(destillering);
        }
    }

    @Override
    public List<Destillering> getDestilleringer() {
        return new ArrayList<>(destilleringer);
    }


    @Override
    public void addProdukt(Produkt produkt) {
        if(!produkter.contains(produkt)){
            produkter.add(produkt);
        }
    }

    @Override
    public List<Produkt> getProdukter() {
        return new ArrayList<>(produkter);
    }

    @Override
    public void addFad(Fad fad) {
        if(!fade.contains(fad)){
            fade.add(fad);
        }
    }

    @Override
    public List<Fad> getFade() {
        return new ArrayList<>(fade);
    }


}
