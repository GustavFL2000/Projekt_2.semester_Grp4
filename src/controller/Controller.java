package controller;

import model.*;
import storage.IStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    IStorage storage;

    public Controller(IStorage storage) {
        this.storage = storage;
    }

    public Destillering createDestillering(int destilleringsID, LocalDate startDato, LocalDate slutDato, double alkoholProcent, boolean rygematriale, String kommentar, double væskeMængde, Maltbatch maltbatch) {
        Destillering destillering = new Destillering(destilleringsID, startDato, slutDato, alkoholProcent, rygematriale, kommentar, væskeMængde, maltbatch);
        storage.addDestillering(destillering);
        return destillering;
    }

    public List<Destillering> getDestilleringer() {
        return storage.getDestilleringer();
    }

    public Produkt createProdukt(int produktNr, double vandMængde, String vandOprindelse, double alkoholProcent, String beskrivelse, KvalitetsStempel kvalitetsStempel) {
        Produkt produkt = new Produkt(produktNr, vandMængde, vandOprindelse, alkoholProcent, beskrivelse, kvalitetsStempel);
        storage.addProdukt(produkt);
        return produkt;
    }

    public Fad createFad(int fadNr, String land, double størrelse, String tidligereIndhold, Leverandør leverandør) {
        Fad fad = new Fad(fadNr, land, størrelse, tidligereIndhold, leverandør);
        storage.addFad(fad);
        return fad;
    }

    public List<Fad> getFade() {
        return storage.getFade();
    }

    public Destillat createDestillat(String destilatNavn, LocalDate dato, double mængde, double alkoholProcent) {
        Destillat destillat = new Destillat(destilatNavn, dato, mængde, alkoholProcent);

        return destillat;
    }

    public Lager createLager(int lagerNr, String lagerNavn, String adresse) {
        Lager lager = new Lager(lagerNr, lagerNavn, adresse);
        return lager;
    }

    public List<Fad> søgEfterFade(Integer fadNr, Lager lager, String tidligereIndhold, Integer alder) {
        List<Fad> alleFade = getFade();
        List<Fad> fundneFade = new ArrayList<>();

        for (Fad fad : alleFade) {
            boolean matcher = true;
            if (fadNr != null && fad.getFadNr() != fadNr) {
                matcher = false;
            }
            if (lager != null ) {
                if (fad.getReol() != null && fad.getReol().getLager() != lager){
                    matcher = false;
                }
            }
            if (tidligereIndhold != null && !tidligereIndhold.isBlank()) {
                if (!tidligereIndhold.equalsIgnoreCase(fad.getTidligereIndhold())) {
                    matcher = false;
                }
            }
            if (alder != null ){
                if (fad.getPåfyldninger().isEmpty()){
                    matcher = false;
                } else {
                    LocalDate sidsteDato = fad.getPåfyldninger().getLast().getDato();
                    LocalDate grænseDato = LocalDate.now().minusYears(alder);
                    if (sidsteDato.isAfter(grænseDato)) {
                        matcher = false;
                    }
                }
            }

            if (matcher) {
                fundneFade.add(fad);
            }
        }
        return fundneFade;
    }
}
