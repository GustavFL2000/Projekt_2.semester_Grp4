package storage;

import model.Destillering;
import model.Fad;
import model.Produkt;
import model.Påfyldning;

import java.util.List;

public interface IStorage {
    void addDestillering(Destillering destillering);
    List<Destillering> getDestilleringer();

    void addProdukt(Produkt produkt);
    List<Produkt> getProdukt();
}
