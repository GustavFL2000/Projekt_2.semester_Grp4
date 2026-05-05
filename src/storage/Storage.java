package storage;

import model.Destillering;
import model.Påfyldning;

import java.util.ArrayList;
import java.util.List;

public class Storage implements IStorage{
    private List<Destillering> destilleringer = new ArrayList<>();
    private List<Påfyldning> påfyldninger = new ArrayList<>();

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
    public void addPåfyldning(Påfyldning påfyldning) {
        if (!påfyldninger.contains(påfyldning)){
            påfyldninger.add(påfyldning);
        }
    }

    @Override
    public List<Påfyldning> getPåfyldninger() {
        return new ArrayList<>(påfyldninger);
    }
}
