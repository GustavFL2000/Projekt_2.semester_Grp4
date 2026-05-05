package storage;

import model.Destillering;

import java.util.ArrayList;
import java.util.List;

public class Storage implements IStorage{
    private List<Destillering> destilleringer = new ArrayList<>();

    @Override
    public void addDestillering(Destillering destillering){
        if(!destilleringer.contains(destillering)){
            destilleringer.add(destillering);
        }
    }

    @Override
    public List<Destillering> getDestilleringer() {
        return destilleringer;
    }
}
