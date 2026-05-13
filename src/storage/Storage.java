package storage;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Storage implements IStorage{
    private List<Destillering> destilleringer = new ArrayList<>();
    private List<Produkt> produkter = new ArrayList<>();
    private List<Fad> fade = new ArrayList<>();
    private List<Lager> lagerListe = new ArrayList<>();
    private List<Maltbatch> maltbatches = new ArrayList<>();

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

    @Override
    public void addLager(Lager lager) {
        if(!lagerListe.contains(lager)){
            lagerListe.add(lager);
        }
    }

    @Override
    public List<Lager> getLagerListe() {
        return new ArrayList<>(lagerListe);
    }

    @Override
    public void addMaltbatch(Maltbatch maltbatch) {
        if (!maltbatches.contains(maltbatch)){
            maltbatches.add(maltbatch);
        }
    }

    @Override
    public List<Maltbatch> getMaltbatch() {
        return new ArrayList<>(maltbatches);
    }


}
