package storage;

import model.*;

import java.util.List;

public interface IStorage {
    void addDestillering(Destillering destillering);
    List<Destillering> getDestilleringer();

    void addProdukt(Produkt produkt);
    List<Produkt> getProdukter();

    void addFad(Fad fad);
    List<Fad> getFade();

    void addLager(Lager lager);
    List<Lager> getLagerListe();

    void addMaltbatch(Maltbatch maltbatch);
    List<Maltbatch> getMaltbatch();

    void addLeverandør(Leverandør leverandør);

    List<Leverandør> getLeverandør();
}
