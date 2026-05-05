package storage;

import model.Destillering;

import java.util.List;

public interface IStorage {
    void addDestillering(Destillering destillering);
    List<Destillering> getDestilleringer();
}
